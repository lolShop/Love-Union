package edu.nf.lol.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.nf.lol.order.entity.OrderDetails;
import edu.nf.lol.product.entity.Product;
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
    private double description;
    private Integer delivery;
    private Integer service;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    private Date comDate;
    private Integer likeNum;
    private Integer parentId;
    private  User user;
    private OrderDetails orderDetails;
    private Integer userId;
    private Integer detailsId;
    private CommentPhoto commentPhoto;
    private Product product;
}