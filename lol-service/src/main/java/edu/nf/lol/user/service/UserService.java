package edu.nf.lol.user.service;

import edu.nf.lol.user.entity.User;

/**
 * @author zhangch
 * @date 2020/3/12
 */
public interface UserService {

    /**
     *用户验证登陆
     * @param user
     * @return
     */
    User findUser(User user);

    /**
     * 注册
     * @param user
     */
    void userRegister(User user);

    /**
     *  注册验证
     * @param user
     * @return
     */
    User userRegisterCheck(User user);

    User queryUserInfo(User user);

    void updateUserPhoto(User user);

    void updateUserPwd(User user);
}
