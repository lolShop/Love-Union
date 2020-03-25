package edu.nf.lol.service.test.user;

import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/17
 */
@SpringBootTest
public class UserAddressTest {

    @Autowired
    private UserAddressService userAddressService;

    @Test
    void testQueryAddress(){
        Address address = new Address();
        User user = new User();
        user.setUserId(1000);
        List<Address> list = userAddressService.queryAddress(user);
        list.forEach(a -> System.out.println(a.getTakeAddress()));
    }

    @Test
    void testAddAddress(){
        Address address = new Address();
        User user = new User();
        user.setUserId(1000);
        address.setUser(user);
        address.setTakeName("测试");
        address.setTakePhone("13479990276");
        address.setTakeAddress("江西|赣州|南康区 唐江中学");
        address.setPostcode("341400");
        userAddressService.addAddress(address);
    }
    //设置默认收货地址：1.全部改成非默认，然后指定的改成默认，2.找到默认，改成非默认，然后指定的改成默认

    @Test
    void testUpdateAddress(){
        Address address = new Address();
        User user = new User();
        user.setUserId(1000);
        address.setUser(user);
        address.setAddressId(1005);
        address.setTakeName("测试修改2");
        address.setTakePhone("13479990222");
        address.setTakeAddress("江西|赣州|南康区 唐江中学修改");
        address.setPostcode("341422");
        userAddressService.updateAddress(address);
    }

    @Test
    void testDelAddress(){
        Address address = new Address();
        address.setAddressId(1005);
        userAddressService.delAddress(address);
    }

    @Test
    void testSetDefaultAddress(){
        Address address = new Address();
        User user = new User();
        user.setUserId(1000);
        address.setAddressId(1001);
        address.setUser(user);
        userAddressService.setDefaultAddress(address);
    }
}
