package edu.nf.lol.service.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Crazy
 * @date 2020/3/10
 */
@SpringBootApplication(scanBasePackages = "edu.nf.lol")
@MapperScan({"edu.nf.lol.product.dao", "edu.nf.lol.order.dao", "edu.nf.lol.*.dao", "edu.nf.lol.shopCart.dao"})
public class SpringBootInitializer {
}