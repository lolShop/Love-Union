package edu.nf.lol.comment.dao;

import edu.nf.lol.comment.entity.CommentPhoto;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/4/1
 */
public interface CommentPhotoDao {
    void publishCommentPhoto(CommentPhoto commentPhoto);
    List<CommentPhoto> findCommentPhoto(Integer comId);
}
