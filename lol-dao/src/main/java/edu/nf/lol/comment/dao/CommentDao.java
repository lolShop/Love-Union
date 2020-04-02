package edu.nf.lol.comment.dao;

import edu.nf.lol.comment.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/4/1
 */
public interface CommentDao {
    /**
     * 发表评论
     * @param comment
     * @return
     */
    Integer publishComment(Comment comment);

    /**
     * 根据商品id查询商品所有评论
     * @param pid
     * @return
     */
    List<Comment> listComment(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,Integer pid);

    double fs(Integer pid);
}
