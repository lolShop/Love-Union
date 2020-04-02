package edu.nf.lol.comment.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.lol.comment.dao.CommentDao;
import edu.nf.lol.comment.entity.Comment;
import edu.nf.lol.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/4/1
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public Integer publishComment(Comment comment) {
        return commentDao.publishComment(comment);
    }

    @Override
    public PageInfo<Comment> listComment(Integer pageNum,Integer pageSize,Integer pid) {
        List<Comment> list = commentDao.listComment(pageNum,pageSize,pid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Double fs(Integer pid) {
        return commentDao.fs(pid);
    }


}