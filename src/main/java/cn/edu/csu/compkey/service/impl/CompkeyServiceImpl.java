package cn.edu.csu.compkey.service.impl;

import cn.edu.csu.compkey.common.exception.ServiceException;
import cn.edu.csu.compkey.common.utils.RedisUtils;
import cn.edu.csu.compkey.entity.Score;
import cn.edu.csu.compkey.entity.dto.ScoreDTO;
import cn.edu.csu.compkey.mapper.CompkeyMapper;
import cn.edu.csu.compkey.service.CompkeyService;
import cn.edu.csu.compkey.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompkeyServiceImpl implements CompkeyService {
    private static List<List<Integer>> data = new ArrayList<>();
    private static Map<String, Integer> encodeMap = new HashMap<>();
    private static Map<Integer, String> decodeMap = new HashMap<>();

    @Value("${CompKey.compkey-num}")
    private int compkeyNum;

    @Value("${CompKey.count-key}")
    private String countKey;

    @Value("${CompKey.hotword-num}")
    private Long hotwordNum;

    @Autowired
    private DataService dataService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CompkeyMapper compkeyMapper;

    @PostConstruct
    public void initialize() {
        data = dataService.readEncodeData("src/main/resources/data/EncodeData.txt");
        decodeMap = dataService.readDecodeMap("src/main/resources/data/DecodeMap.txt");
        encodeMap = dataService.readEncodeMap("src/main/resources/data/EncodeMap.txt");
    }

    @Override
    public Map<String, Double> find(String seed) {
        long stime = System.currentTimeMillis();

        // 获取种子编码
        Integer encodeSeed = encodeMap.get(seed);
        // 中介关键词
        Map<Integer, Integer> midKeys = dataService.getMidKeys(data, encodeSeed);
        // 竞争关键词
        Map<Integer, Double> compKeys = dataService.getCompKeys(data, midKeys, encodeSeed);
        // 解码
        Map<String, Double> convertedMap = compKeys.entrySet().stream().collect(
                Collectors.toMap(
                        entry -> decodeMap.get(entry.getKey()), // 获取映射后的键
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // 如果存在重复键，保留旧值
                        LinkedHashMap::new // 保持顺序的LinkedHashMap
                ));
        // 竞争度和用户评分相乘
        Map<String, Double> scores = getScore(seed);
        Map<String, Double> multiScore = convertedMap.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() * scores.getOrDefault(entry.getKey(), 1.0)
                ));
        // 排序输出
        Map<String, Double> result = multiScore.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(compkeyNum)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        long etime = System.currentTimeMillis();
        log.info(String.format("种子关键词：%s\n竞争关键词：%s\n执行时长：%d毫秒", seed, result, (etime - stime)));
        return result;
    }

    @Override
    public void postScore(ScoreDTO scoreDTO) {
        for (Score score : scoreDTO.getScores()) {
            score.setSeedKey(scoreDTO.getSeedKey());
            compkeyMapper.saveScore(score);
        }
    }

    @Override
    public Map<String, Double> getScore(String seed) {
        return compkeyMapper.getScore(seed).stream().collect(Collectors.toMap(Score::getCompKey, Score::getScore));
    }

    @Override
    @Async
    public void count(String seed) {
        Map<String, Integer> hotwords;
        if (redisUtils.hasKey(countKey)) {
            hotwords = redisUtils.getCacheMap(countKey);
        } else {
            hotwords = new HashMap<>();
        }
        hotwords.put(seed, hotwords.getOrDefault(seed, 0) + 1);
        redisUtils.setCacheMap(countKey, hotwords);
    }

    @Override
    public Map<String, Integer> getHotword() {
        if (redisUtils.hasKey(countKey)) {
            Map<String, Integer> hotwords = redisUtils.getCacheMap(countKey);
            return hotwords.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(hotwordNum)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        } else {
            throw new ServiceException("还没有搜索记录");
        }
    }


}
