package edu.nf.lol.user.service;

import edu.nf.lol.user.entity.User;

/**
 * @author zhangch
 * @date 2020/3/12
 */
public interface UserService {

    User userLogin(User user);

    void userRegister(User user);

    User queryUserInfo(User user);

    void updateUserPhoto(User user);

    void updateUserPwd(User user);
}
