package co.bugu.data.service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/2/23.
 */
public interface IDataService {
    void add(List<List<String>> assetData, List<List<String>> productData, Map<String, Integer> type, Map<String, Integer> cType) throws Exception;

    void addTongji();
}
