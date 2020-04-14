package edu.nf.lol.user.controller;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.BaseController;

import edu.nf.lol.user.entity.Collect;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserCollectService;
import edu.nf.lol.vo.ResponseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @author zhangch
 * @date 2020/3/23
 */
@RestController
@RequestMapping("/user/collect")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCollectController extends BaseController {

    @Autowired
    private UserCollectService service;

    /**
     * 查询收藏
     * @Param("pageNum")Integer pageNum, @Param("pageSize") Integer pageSize
     * @return
     */
    @GetMapping("/query_collect")
    public ResponseVO queryCollect(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, HttpSession session){
        User user = (User)session.getAttribute("user");
        PageInfo<Collect> allCollect = service.queryCollect(user, pageNum, pageSize);
        return success(allCollect);
    }

    /**
     * 取消收藏
     * @param collect
     * @return
     */
    @PostMapping("/cancel_collect")
    public ResponseVO cancelCollect(Collect collect){
        service.deleteCollect(collect);
        return success("取消收藏成功");
    }

    /**
     * 收藏商品
     * @param collect
     * @return
     */
    @PostMapping
    public ResponseVO addCollect(Collect collect){
        service.addCollect(collect.getUser(), collect.getProduct());
        return success("收藏成功");
    }
}
