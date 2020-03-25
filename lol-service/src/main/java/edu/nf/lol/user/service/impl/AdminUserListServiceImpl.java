package edu.nf.lol.user.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.user.dao.AdminUserListDao;
import edu.nf.lol.user.service.AdminUserListService;
import edu.nf.lol.exception.LolException;
import edu.nf.lol.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("adminUserListService")
@Transactional
public class AdminUserListServiceImpl implements AdminUserListService {
    @Autowired
    private AdminUserListDao adminUserListDao;

    public PageInfo<User> adminUserList(Integer pageNum, Integer pageSize) {
        try {
            List<User> UserList= (List<User>) adminUserListDao.adminUserList(pageNum, pageSize);
            return new PageInfo<>(UserList);
        }catch (RuntimeException e){
            throw  new LolException("查询失败,请稍后重试");
        }
    }
    public List<User> delAdminUser() {
        try {
            List<User> UserList=adminUserListDao.delAdminUser();
            return  UserList;
        }catch (RuntimeException e){
            throw  new LolException("删除失败,请稍后重试");
        }
    }

}

