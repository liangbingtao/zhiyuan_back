package com.sanelee.zhiyuan.Mapper;

import com.sanelee.zhiyuan.Model.User;
import com.sanelee.zhiyuan.Model.UserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserExtMapper {

    User findByToken(String token);

    void saveUser(User user);

    User selectUserInfoByUsername(String username);

    User findByUserNameAndUserPhone(String username, String userPhone);

    User selectByPhone(String userphone);

    User getScoreByPhone(String userphone);

    User loginquery(String username, String pwd);

    void updateUser(User user);
}