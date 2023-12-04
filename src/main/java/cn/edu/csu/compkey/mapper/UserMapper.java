package cn.edu.csu.compkey.mapper;

import cn.edu.csu.compkey.entity.AuthLocal;
import cn.edu.csu.compkey.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface UserMapper {
    AuthLocal selectPasswordByUsername(@Param("username") String username);

    int selectRoleIdByAccount(@Param("account") int account);

    void createUser(User user);

    List<String> selectAllUsername();

    void insertAuthLocal(AuthLocal auth);
}
