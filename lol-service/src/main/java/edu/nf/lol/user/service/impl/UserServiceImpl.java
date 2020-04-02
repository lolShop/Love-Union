package edu.nf.lol.user.service.impl;

import edu.nf.lol.user.dao.UserDao;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangch
 * @date 2020/3/12
 */
@Service("userService")
@Transactional()
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        try {
            return dao.userLogin(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录失败");
        }
    }

    /**
     * 用户注册
     * @param user
     */
    @Override
    public void userRegister(User user) {
        try {
            dao.userRegister(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("注册失败");
        }
    }

    /**
     * 查询用户的基本信息
     * @param user
     * @return
     */
    @Override
    public User queryUserInfo(User user) {
        try {
            return dao.queryUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败");
        }
    }

    @Override
    public void updateInfo(User user) {
        try{
            dao.updateInfo(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * 修改头像
     * @param user
     */
    @Override
    public void updateUserPhoto(User user) {
        try {
            dao.updateUserPhoto(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * 修改密码
     * @param user
     */
    @Override
    public void updateUserPwd(User user) {
        try {
            dao.updateUserPwd(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }
}
