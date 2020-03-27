package edu.nf.lol.user.dao;

import edu.nf.lol.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author zhangch
 * @date 2020/3/12
 * 跟用户信息相关的操作
 */
@Repository
public interface UserDao {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User userLogin(User user);

    /**
     * 用户注册
     * @param user
     * @return
     */
    void userRegister(User user);

    /**
     * 根据userId查询用户信息
     * @param user
     * @return
     */
    User queryUserInfo(User user);

    /**
     * 修改头像
     * @param user
     */
    void updateUserPhoto(User user);

    /**
     * 修改用户密码
     * @param user
     */
    void updateUserPwd(User user);
}
