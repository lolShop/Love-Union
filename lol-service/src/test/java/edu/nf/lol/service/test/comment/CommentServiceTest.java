package edu.nf.lol.service.test.comment;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.comment.entity.Comment;
import edu.nf.lol.comment.entity.CommentPhoto;
import edu.nf.lol.comment.service.CommentPhotoService;
import edu.nf.lol.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/4/1
 */
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentPhotoService commentPhotoService;

    @Test
    public void testPublishComment(){
        Comment comment = new Comment();
        comment.setUserId(1000);
        comment.setDetailsId(1000);
        comment.setDescription(4.5);
        comment.setContent("我是博迪地");
        commentService.publishComment(comment);
        System.out.println("comId:"+comment.getComId());
        CommentPhoto commentPhoto = new CommentPhoto();
        commentPhoto.setComId(comment.getComId());
        commentPhoto.setPhoto("t.png");
        commentPhotoService.publishPhotoComment(commentPhoto);
    }
    @Test
    public void testListComment(){
        PageInfo<Comment> pageInfo = commentService.listComment(1,10,1);
        for (Comment c:pageInfo.getList()) {
            System.out.println(c.getContent()+"   "+c.getDescription()+"  "+c.getCommentPhoto().getPhoto()+"   "+
                c.getComDate()+"   "+c.getUser().getUserName()+"   "+c.getProduct().getProductId()+"   "+c.getUser().getPhoto()
            );
        }
    }
}