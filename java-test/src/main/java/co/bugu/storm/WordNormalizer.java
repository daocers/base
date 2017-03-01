package co.bugu.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/3/1.
 */
public class WordNormalizer implements IRichBolt {
    private OutputCollector collector;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    /**
     * 这是bolt中最重要的方法，每当接受一个tuple时，此方法便被调用
     * 这个方法的作用就是把文本文件的每一行切分成一个个单词，并把这些单词发射出去，（给下一个bolt处理）
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        String sentence = tuple.getString(0);
        String[] words = sentence.split(" ");
        for(String word: words){
            word = word.trim();
            if(!word.isEmpty()){
                word = word.toLowerCase();
                List a = new ArrayList<>();
                a.add(tuple);
                collector.emit(a, new Values(word));
            }
        }
        //确认成功处理一个tuple
        collector.ack(tuple);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
