package co.bugu.tes.service;


import co.bugu.tes.model.Station;
import co.bugu.framework.core.dao.PageInfo;

import java.util.List;

public interface IStationService {
    int save(Station station);

    int updateById(Station station);

    int saveOrUpdate(Station station);

    int delete(Station station);

    Station findById(Integer id);

    List<Station> findAllByObject(Station station);

    PageInfo listByObject(Station station, PageInfo<Station> pageInfo) throws Exception;

}
