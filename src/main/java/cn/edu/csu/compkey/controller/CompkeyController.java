package cn.edu.csu.compkey.controller;

import cn.edu.csu.compkey.common.response.CommonResponse;
import cn.edu.csu.compkey.common.utils.RedisUtils;
import cn.edu.csu.compkey.entity.dto.CompeteWord;
import cn.edu.csu.compkey.entity.dto.HotWord;
import cn.edu.csu.compkey.entity.dto.ScoreDTO;
import cn.edu.csu.compkey.service.CompkeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CompkeyController {
    @Autowired
    private CompkeyService compkeyService;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${CompKey.cache-timeout}")
    private Long cacheTimeout;

    @GetMapping("/compkey")
    public CommonResponse getCompkey(@RequestParam String seed) {
        compkeyService.count(seed);  // 异步统计热词

        List<CompeteWord> res;
        if (redisUtils.hasKey(seed)) {
            res = redisUtils.getCacheObject(seed);
        } else {
            Map<String, Double> compkeys = compkeyService.find(seed);  // 查找
            Map<String, Double> averageRate = compkeyService.getScore(seed);  // 查询平均分
            res = compkeys.entrySet().stream()
                    .map(entry -> new CompeteWord(entry.getKey(), entry.getValue(), averageRate.getOrDefault(entry.getKey(), 1.0)))
                    .collect(Collectors.toList());
            redisUtils.setCacheObject(seed, res, cacheTimeout, TimeUnit.MINUTES);
        }
        return CommonResponse.success(res);
    }

    @PostMapping("/score")
    public CommonResponse postScore(@RequestBody ScoreDTO scoreDTO) {
        compkeyService.postScore(scoreDTO);
        return CommonResponse.success();
    }

    @GetMapping("/hotword")
    public CommonResponse getHotword() {
        Map<String, Integer> hotwords = compkeyService.getHotword();
        List<HotWord> res = hotwords.entrySet().stream()
                .map(entry -> new HotWord(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return CommonResponse.success(res);
    }
}
