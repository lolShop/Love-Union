package edu.nf.lol.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author zhangch
 * @date 2020/3/5
 * 用户信息
 */
@Data
public class User {
    private Integer userId;
    private String userPhone;
    private String password;
    private String userName;
    private String photo;
    private Boolean sex;
    //从数据库取
    @JsonFormat( pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String phone;
    private String email;

}