package cn.edu.csu.compkey.controller;

import cn.edu.csu.compkey.common.response.CommonResponse;
import cn.edu.csu.compkey.service.CompkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompkeyController {
    @Autowired
    private CompkeyService compkeyService;

    @GetMapping()
    public CommonResponse search(@RequestParam String seed) {
        return CommonResponse.success();
    }
}
