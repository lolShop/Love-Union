package edu.nf.lol.user.service;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.user.entity.User;

import java.util.List;

public interface AdminUserListService {
    PageInfo<User> adminUserList(Integer pageNum, Integer pageSize);
    List<User> delAdminUser();
}
