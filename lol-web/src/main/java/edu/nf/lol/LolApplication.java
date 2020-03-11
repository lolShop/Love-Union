package edu.nf.lol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Crazy
 * @date 2020/3/2
 */
@SpringBootApplication
@MapperScan({"edu.nf.lol.product.dao"})
public class LolApplication {
    public static void main(String[] args) {
        SpringApplication.run(LolApplication.class,args);
    }

}