package edu.nf.lol.user.controller;

import edu.nf.lol.BaseController;
import edu.nf.lol.user.service.CollectService;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/3/22
 */
@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CollectController extends BaseController {
    @Autowired
    private CollectService collectService;

    @GetMapping("/favCollect")
    public ResponseVO addCollect(Integer uid,Integer pid){
        collectService.addCollect(uid,pid);
        return success("收藏成功!");
    }

}