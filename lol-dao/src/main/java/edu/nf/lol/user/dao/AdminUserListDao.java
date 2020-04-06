package edu.nf.lol.user.dao;

import edu.nf.lol.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserListDao {
    /**
     * 查询用户列表
     */
    List<User> adminUserList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    /**
     * 删除用户
     */
    List<User> delAdminUser();
}