package cn.edu.csu.compkey.service.impl;

import cn.edu.csu.compkey.entity.Cache;
import cn.edu.csu.compkey.mapper.CacheMapper;
import cn.edu.csu.compkey.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    CacheMapper cacheMapper;

    @Override
    public List<Cache> getAllCache() {
        List<Cache> cacheList=cacheMapper.selectList(null);
        return cacheList;
    }

    @Override
    public void deleteCacheBySeed(String seed) {
        cacheMapper.deleteById(seed);
    }
}
