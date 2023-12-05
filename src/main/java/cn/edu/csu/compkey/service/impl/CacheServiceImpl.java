package cn.edu.csu.compkey.service.impl;

import cn.edu.csu.compkey.entity.Cache;
import cn.edu.csu.compkey.entity.vo.Score;
import cn.edu.csu.compkey.mapper.CacheMapper;
import cn.edu.csu.compkey.service.CacheService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public List<Cache> getAvgScore() {
        QueryWrapper<Cache> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("seed",
        "recom1", "recom2", "recom3", "recom4", "recom5",
        "recom6", "recom7", "recom8", "recom9", "recom10",
                "score1","score2","score3","score4","score5","score6","score7","score8", "score9","score10");
        return cacheMapper.selectList(queryWrapper);
    }
}
