package edu.nf.lol.UserController;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.BaseController;
import edu.nf.lol.user.service.AdminUserListService;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserAdminController extends BaseController {
    @Autowired
    private AdminUserListService service;
    /**
     * 查询用户列表
     * @param
     * @return
     */
    @GetMapping("/adminUser")
    public ResponseVO productDetail(Integer pageNum, Integer pageSize){
        PageInfo<User> pageInfo = service.adminUserList(pageNum, pageSize);
        return success(pageInfo);
    }
}

