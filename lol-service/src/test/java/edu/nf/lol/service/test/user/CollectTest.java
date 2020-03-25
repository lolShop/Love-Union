package edu.nf.lol.service.test.user;

import edu.nf.lol.user.service.CollectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Administrator
 * @date 2020/3/22
 */
@SpringBootTest
public class CollectTest {
    @Autowired
    private CollectService collectService;

    @Test
    public void addCollect(){
        collectService.addCollect(1000,1003);
    }

}