package co.bugu.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created by user on 2017/3/1.
 */
public class StormTest {
    public  static void main(String[] args) throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReader());
        builder.setBolt("word-normailizer", new WordNormalizer())
                .shuffleGrouping("word-reader");
        builder.setBolt("word-counter", new WordCounter(), 2)
                .fieldsGrouping("word-normailizer", new Fields("word"));

        //配置
        Config conf = new Config();
        conf.put("wordsFile", "d:/test.txt");
        conf.setDebug(false);

        //提交topology
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        //创建一个本地模式cluster
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-started-Topology", conf, builder.createTopology());
        Thread.sleep(2000);
        cluster.shutdown();
    }
}
