package edu.nf.lol.comment.controller;

import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import edu.nf.lol.BaseController;
import edu.nf.lol.comment.entity.Comment;
import edu.nf.lol.comment.entity.CommentPhoto;
import edu.nf.lol.comment.service.CommentPhotoService;
import edu.nf.lol.comment.service.CommentService;
import edu.nf.lol.util.UUIDUtils;
import edu.nf.lol.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2020/4/1
 */
@RestController
@RequestMapping("comment")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentPhotoService commentPhotoService;

    private String name;

    @PostMapping("/publishComment")
    public ResponseVO publishComment(Comment comment, CommentPhoto commentPhoto){
        commentService.publishComment(comment);
        if(commentPhoto.getPhoto()!=null){
            commentPhoto.setPhoto(name);
            String fileName =commentPhoto.getPhoto();
            commentPhoto.setComId(comment.getComId());
            commentPhotoService.publishPhotoComment(commentPhoto);
        }
        return success("发表成功！");
    }
    @PostMapping("/ajax_upload")
    public void upload(MultipartFile file) throws IOException {
        String Name = file.getOriginalFilename();
        String hz = Name.substring(Name.length()-4);
        String fileName = UUIDUtils.createUUID()+hz;
        name =fileName;
        Path path = FileSystems.getDefault().getPath("F:\\s3s152\\two-year-project\\Love-Union\\lol-web\\src\\main\\resources\\static\\image\\comment", fileName);
//        String uploadPath=request.getServletContext().getRealPath(File.separator+"static"+File.separator+"image"+File.separator+"comment");
//        Path path = FileSystems.getDefault().getPath(uploadPath, fileName);
        file.transferTo(path);
    }
    @PostMapping("/listComment")
    public ResponseVO<PageInfo<Comment>> listComment(Integer pageNum,Integer pageSize,Integer pid){
        PageInfo<Comment> pageInfo = commentService.listComment(pageNum,pageSize,pid);
        return success(pageInfo);
    }


}