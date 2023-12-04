package cn.edu.csu.compkey.controller;

import cn.edu.csu.compkey.common.response.CommonResponse;
import cn.edu.csu.compkey.entity.Cache;
import cn.edu.csu.compkey.entity.History;
import cn.edu.csu.compkey.entity.Timecost;
import cn.edu.csu.compkey.service.CacheService;
import cn.edu.csu.compkey.service.HistoryService;
import cn.edu.csu.compkey.service.TimecostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimecostController {
    @Autowired
    TimecostService timecostService;
    @Autowired
    HistoryService historyService;
    @Autowired
    CacheService cacheService;

    @GetMapping("/time")
    public CommonResponse timestatistics(){
        List<Timecost> timecosts=timecostService.getAllTimecost();
        return CommonResponse.success("获取成功",timecosts);
    }
    @GetMapping("/history")
    public CommonResponse historyManage(){
        List<History> histories=historyService.getAllHistory();
        return CommonResponse.success("获取成功",histories);
    }
    @GetMapping("/history/{id}")
    public CommonResponse getHistoryByuserid(@PathVariable int id){
        List<History> histories=historyService.getHistoryByuserId(id);
        return CommonResponse.success("获取成功",histories);
    }
    @DeleteMapping("/history")
    public CommonResponse historyDel(@RequestParam int serchid){
        historyService.deleteHistoryById(serchid);
        return CommonResponse.success("删除成功");
    }
    @GetMapping("/score")
    public CommonResponse getAllScore(){
        List<Cache> caches=cacheService.getAllCache();
        return CommonResponse.success("获取成功",caches);
    }
    @DeleteMapping("/score")
    public CommonResponse cacheDel(@RequestParam String seed){
        cacheService.deleteCacheBySeed(seed);
        return CommonResponse.success("删除成功");
    }
}
