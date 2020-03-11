package edu.nf.lol.comment.entity;

import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.user.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @date 2020/3/9
 */
@Data
public class Comment {
    private Integer comId;
    private Integer description;
    private Integer delivery;
    private Integer service;
    private String content;
    private Date comDate;
    private Integer likeNum;
    private Integer parentId;
    private  User user;
    private OrderDetails orderDetails;
}