package cn.edu.csu.compkey.service.impl;

import cn.edu.csu.compkey.entity.Timecost;
import cn.edu.csu.compkey.mapper.TimecostMapper;
import cn.edu.csu.compkey.service.TimecostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimecostServiceImpl implements TimecostService {
    @Autowired
    TimecostMapper timecostMapper;

    @Override
    public List<Timecost> getAllTimecost() {
        List<Timecost> timecosts=timecostMapper.selectList(null);
        return timecosts;
    }
}
