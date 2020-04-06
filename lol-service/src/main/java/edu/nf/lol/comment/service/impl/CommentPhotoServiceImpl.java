package edu.nf.lol.comment.service.impl;

import edu.nf.lol.comment.dao.CommentPhotoDao;
import edu.nf.lol.comment.entity.CommentPhoto;
import edu.nf.lol.comment.service.CommentPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/4/1
 */
@Service("commentPhotoService")
@Transactional
public class CommentPhotoServiceImpl implements CommentPhotoService {

    @Autowired
    private CommentPhotoDao commentPhotoDao;

    @Override
    public void publishPhotoComment(CommentPhoto commentPhoto) {
        commentPhotoDao.publishCommentPhoto(commentPhoto);
    }

    @Override
    public List<CommentPhoto> findCommentPhoto(Integer comId) {
        return commentPhotoDao.findCommentPhoto(comId);
    }
}