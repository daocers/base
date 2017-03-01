package co.bugu.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/3/1.
 */
public class WordCounter implements IRichBolt {
    Integer id;
    String name;
    Map<String, Integer> counters;
    private OutputCollector collector;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counters = new HashMap<>();
        this.collector = outputCollector;
        this.name = topologyContext.getThisComponentId();
        this.id = topologyContext.getThisTaskId();
    }

    @Override
    public void execute(Tuple tuple) {
        String str = tuple.getString(0);
        if(!counters.containsKey(str)){
            counters.put(str, 1);
        }else{
            Integer c = counters.get(str) + 1;
            counters.put(str, c);
        }

        collector.ack(tuple);
    }

    /**
     * topology执行完毕的清理工作， 比如关闭连接，释放资源等操作都会写在这里
     * 这里只是一个demo，用来打印我们的计数器
     */
    @Override
    public void cleanup() {
        System.out.println("-- word counter [" + name + "-" + "] --");
        for(Map.Entry<String, Integer> entry: counters.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        counters.clear();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
