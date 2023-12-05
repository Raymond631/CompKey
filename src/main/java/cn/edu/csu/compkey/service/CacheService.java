package cn.edu.csu.compkey.service;

import cn.edu.csu.compkey.entity.Cache;
import cn.edu.csu.compkey.entity.vo.Score;

import java.util.List;

public interface CacheService {
    List<Cache> getAllCache();
    void deleteCacheBySeed(String seed);

    List<Cache> getAvgScore();
}
