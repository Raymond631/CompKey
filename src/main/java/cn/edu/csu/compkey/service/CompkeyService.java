package cn.edu.csu.compkey.service;

import cn.edu.csu.compkey.entity.dto.ScoreDTO;

import java.util.Map;

public interface CompkeyService {
    Map<String, Double> find(String seed);

    void count(String seed);

    Map<String, Integer> getHotword();

    void postScore(ScoreDTO scoreDTO);

    Map<String, Double> getScore(String seed);
}
