package edu.nf.lol.comment.service;

import edu.nf.lol.comment.entity.CommentPhoto;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/4/1
 */
public interface CommentPhotoService {
    void publishPhotoComment(CommentPhoto commentPhoto);
    List<CommentPhoto> findCommentPhoto(Integer comId);
}
