package cn.edu.csu.compkey.service;

import cn.edu.csu.compkey.entity.dto.LoginResp;

public interface UserService {
    LoginResp checkAccount(String username, String password);

    LoginResp register(String username, String password);
}
