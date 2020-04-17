package edu.nf.lol.comment.controller;

import com.aliyun.oss.OSSClient;
import com.github.pagehelper.PageInfo;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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


    @PostMapping("/publishComment")
    public ResponseVO publishComment(Comment comment, CommentPhoto commentPhoto,HttpSession session){
        List<String> list = (List<String>) session.getAttribute("file");
        commentService.publishComment(comment);
        if(commentPhoto.getPhoto()!=null){
            for(String fileName : list){
                commentPhoto.setPhoto(fileName);
                commentPhoto.setComId(comment.getComId());
                commentPhotoService.publishPhotoComment(commentPhoto);
            }
        }
        return success("发表成功！");
    }
    @PostMapping("/ajax_upload")
    public void upload(@RequestParam("files") MultipartFile [] files,HttpSession session) throws IOException {
        System.out.println(files.length);
        List<String> fileNames = new ArrayList<String>();
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4Fqirc9BaQk4gbMa6V7L";
        String accessKeySecret = "4SjxW2VMnNvkws4glh3p7o7FFoYEts";
        String bucketName = "cffile";
        OSSClient ossClient =new OSSClient(endpoint,accessKeyId,accessKeySecret);
        for(int i=0;i<files.length;i++){
            String fileName  =files[i].getOriginalFilename() ;
            String suffixName =fileName.substring(fileName.lastIndexOf("."));
            String finalFileName =System.currentTimeMillis()+""+suffixName;
            String objName ="image/"+finalFileName;
            fileNames.add(finalFileName);
            File file2 = File.createTempFile(UUID.randomUUID().toString(), suffixName);
            files[i].transferTo(file2);
            ossClient.putObject(bucketName,objName,file2);
            file2.deleteOnExit();
        }
        ossClient.shutdown();
        session.setAttribute("file",fileNames);


    }
    @PostMapping("/listComment")
    public ResponseVO<PageInfo<Comment>> listComment(Integer pageNum,Integer pageSize,Integer pid){
        PageInfo<Comment> pageInfo = commentService.listComment(pageNum,pageSize,pid);
        for(Comment c:pageInfo.getList()){
            List<CommentPhoto> list = commentPhotoService.findCommentPhoto(c.getComId());
            c.setCommentPhotos(list);
        }
        return success(pageInfo);
    }

    @PostMapping("/fs")
    public ResponseVO fs(Integer pid){
        Double fs = commentService.fs(pid);
        return success(fs);
    }

    @GetMapping("/ordereval")
    public ResponseVO findComment(Integer comId){
        Comment comment = commentService.findComment(comId);
        List<CommentPhoto> list = commentPhotoService.findCommentPhoto(comId);
        comment.setCommentPhotos(list);
        return success(comment);
    }


}