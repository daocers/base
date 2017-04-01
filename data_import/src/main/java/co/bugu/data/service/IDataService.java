package co.bugu.data.service;

import co.bugu.data.model.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/2/23.
 */
public interface IDataService {
    void add(List<List<String>> assetData, List<List<String>> productData, Map<String, Integer> type, Map<String, Integer> cType) throws Exception;

    void addTongji();

    void addRelation(List<List<String>> productData);

    void addBatch(List<List<String>> assetDate, List<List<String>> productData) throws Exception;
}
