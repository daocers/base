package co.bugu.data.service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by user on 2017/2/23.
 */
public interface IDataService {
    void add(List<List<String>> assetData, List<List<String>> productData) throws Exception;
}
