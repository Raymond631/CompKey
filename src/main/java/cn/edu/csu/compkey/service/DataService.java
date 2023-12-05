package cn.edu.csu.compkey.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataService {
    void preprocessing() throws IOException;

    List<List<Integer>> readEncodeData(String filePath);

    Map<String, Integer> readEncodeMap(String filePath);

    Map<Integer, String> readDecodeMap(String filePath);

    Map<Integer, Integer> getMidKeys(List<List<Integer>> data, Integer seed);

    Map<Integer, Double> getCompKeys(List<List<Integer>> data, Map<Integer, Integer> midKeys, Integer seed);

}
