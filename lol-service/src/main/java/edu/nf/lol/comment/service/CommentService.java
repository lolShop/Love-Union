package edu.nf.lol.comment.service;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.comment.entity.Comment;

import java.util.List;


/**
 * @author Administrator
 * @date 2020/4/1
 */

public interface CommentService {
    Integer publishComment(Comment comment);
    PageInfo<Comment> listComment(Integer pageNum,Integer pageSize,Integer pid);
}
