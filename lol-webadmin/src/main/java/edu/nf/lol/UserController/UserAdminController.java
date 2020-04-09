package edu.nf.lol.UserController;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.BaseController;
import edu.nf.lol.product.entity.Product;
import edu.nf.lol.user.service.AdminUserListService;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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
    @GetMapping("/ListUser")
    public ResponseVO ListAdminUser(Integer pageNum, Integer pageSize){
        PageInfo<User> pageInfo = service.adminUserList(pageNum, pageSize);
        return success(pageInfo);
    }
    /**
     * 通过id显示在模态框中
     */
    @GetMapping("/getUserId")
    public ResponseVO getAdminUserId(User user){
        User users = service.getAdminUserId(user);
        return success(users);
    }

    /**
     * 删
     */
    @PostMapping("/delUser")
    public ResponseVO adminDelUser(@Valid int uid){
        service.delAdminUser(uid);
        return  success(0,"删除成功");
    }

    /**
     * 改
     */
    @GetMapping("/updateUser")
    public ResponseVO adminUpdateUser(@Valid User user){
        service.updateUser(user);
        return  success("修改成功");
    }
    /**
     * 增
     */
    @PostMapping("/addUser")
    public ResponseVO adminAddUser(@Valid User user){
        service.addUserList(user);
        return  success("添加成功");
    }
}

