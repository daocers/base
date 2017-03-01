package co.bugu.storm;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by user on 2017/3/1.
 */
public class WordReader implements IRichSpout{
    private static final long serialVersionUID = 1l;
    private SpoutOutputCollector collector;
    private FileReader fileReader;
    private boolean completed = false;

    public boolean isDistributed(){
        return false;
    }

    /**
     *
     * @param conf  创建topology 的配置
     * @param topologyContext   所有的topology数据
     * @param spoutOutputCollector  用来把spout的数据发送给bolt
     */
    @Override
    public void open(Map conf, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        try {
            //获取创建topology时需要读取的文件路径
            this.fileReader = new FileReader(conf.get("wordsFile").toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //初始化发射器
        this.collector = collector;
    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    /**
     * 这是spout最主要的方法，在这里我们读取文本文件，并把它的每一行发射出去（给bolt）
     * 这个方法会不断被调用，为了降低它对cpu的消耗，当任务完成，让它sleep一下
     */
    @Override
    public void nextTuple() {
        if(completed){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        String str;
        BufferedReader reader = new BufferedReader(fileReader);
        try {
            while((str = reader.readLine()) != null){
                /**
                 * 发射每一行，values是一个arraylist的实现
                 *
                 */
                this.collector.emit(new Values(str), str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            completed = true;
        }


    }

    @Override
    public void ack(Object o) {
        System.out.println("OK: " + o);
    }

    @Override
    public void fail(Object o) {
        System.out.println("FAIL: " + o);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
