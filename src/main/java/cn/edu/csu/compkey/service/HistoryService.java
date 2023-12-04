package cn.edu.csu.compkey.service;

import cn.edu.csu.compkey.entity.History;

import java.util.List;

public interface HistoryService {
    List<History> getAllHistory();
    void deleteHistoryById(int id);
    List<History> getHistoryByuserId(int id);



}
