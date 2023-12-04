package cn.edu.csu.compkey.service.impl;

import cn.edu.csu.compkey.mapper.CompkeyMapper;
import cn.edu.csu.compkey.service.CompkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompkeyServiceImpl implements CompkeyService {
    @Autowired
    private CompkeyMapper compkeyMapper;
}
