package cn.edu.csu.compkey.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody {
    private String username;
    private String password;
    private String code;
    private String codeID;
}
