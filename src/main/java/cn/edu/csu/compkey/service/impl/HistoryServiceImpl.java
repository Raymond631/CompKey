package cn.edu.csu.compkey.service.impl;

import cn.edu.csu.compkey.entity.History;
import cn.edu.csu.compkey.mapper.HistoryMapper;
import cn.edu.csu.compkey.service.HistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryMapper historyMapper;

    @Override
    public List<History> getAllHistory() {
        List<History> histories=historyMapper.selectList(null);
        return histories;
    }

    @Override
    public void deleteHistoryById(int id) {
        historyMapper.deleteById(id);
    }

    @Override
    public List<History> getHistoryByuserId(int id) {
        QueryWrapper<History> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",id);
        List<History> histories=historyMapper.selectList(queryWrapper);
        return histories;
    }
}
