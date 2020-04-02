package edu.nf.lol.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
    @JsonFormat( pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;
    private String phone;
    private String email;

}