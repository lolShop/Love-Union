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
     * 用户注册
     * @param user
     * zachery
     * @return
     */
    void userRegister(User user);


    /**
     *根据id获取用户信息,用于登陆
     * @param user
     * zachery
     * @return
     */
    User getUserByPhone(User user);
    /**
     * 用户登录
     * @param user
     * @return
     */
    User userLogin(User user);



    /**
     * 根据userId查询用户信息
     * @param user
     * @return
     */
    User queryUserInfo(User user);

    /**
     * 修改个人资料
     * @param user
     */
    void updateInfo(User user);

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
