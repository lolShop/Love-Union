package edu.nf.lol.user.service.impl;

import edu.nf.lol.exception.LolException;
import edu.nf.lol.user.dao.UserDao;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao dao;

    @Override
    public User findUser(User user) {
        try {
            User us = dao.getUserByPhone(user);
            if(us.getUserPhone().equals(user.getUserPhone()) && us.getPassword().equals(user.getPassword())){
                log.info("用户消息:"+user.getUserName()+"已登陆");
                return us;
            }else{
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        throw new LolException("用户名或密码错误!");
    }

    /**
     * 注册验证
     * @param user
     * @return
     */
    @Override
    public User userRegisterCheck(User user) {
        try {
            User u = dao.getUserByPhone(user);
            if(u != null){
                return u;
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        throw new LolException("此用户已存在!");
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
            throw new RuntimeException("查询失败");
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
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * 修改密码
     * @param user
     */
    @Override
    public void updateUserPwd(User user) {
        dao.updateUserPwd(user);
    }
}
