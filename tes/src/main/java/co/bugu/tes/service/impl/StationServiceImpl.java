package co.bugu.tes.service.impl;


import co.bugu.framework.core.service.impl.BaseServiceImpl;
import co.bugu.tes.model.Station;
import co.bugu.tes.service.IStationService;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.dao.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl extends BaseServiceImpl<Station> implements IStationService {
//    @Autowired
//    BaseDao baseDao;
//
//    @Override
//    public int save(Station station) {
//        return baseDao.insert("tes.station.insert", station);
//    }
//
//    @Override
//    public int updateById(Station station) {
//        return baseDao.update("tes.station.updateById", station);
//    }
//
//    @Override
//    public int saveOrUpdate(Station station) {
//        if(station.getId() == null){
//            return baseDao.insert("tes.station.insert", station);
//        }else{
//            return baseDao.update("tes.station.updateById", station);
//        }
//    }
//
//    @Override
//    public int delete(Station station) {
//        return baseDao.delete("tes.station.deleteById", station);
//    }
//
//    @Override
//    public Station findById(Integer id) {
//        return baseDao.selectOne("tes.station.selectById", id);
//    }
//
//    @Override
//    public List<Station> findAllByObject(Station station) {
//        return baseDao.selectList("tes.station.listByObject", station);
//    }
//
//    @Override
//    public PageInfo listByObject(Station station, PageInfo<Station> pageInfo) throws Exception {
//        return baseDao.listByObject("tes.station.listByObject", station, pageInfo);
//    }
}
