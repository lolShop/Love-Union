package edu.nf.lol.user.dao;

import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/17
 * 用户的收货地址相关操作
 */
public interface UserAddressDao {

    /**
     * 查询收货地址
     * @param user
     * @return
     */
    List<Address> queryAddress(User user);

    /**
     * 新增收货地址
     * @param address
     */
    void addAddress(Address address);

    /**
     * 修改收货地址
     * @param address
     */
    void updateAddress(Address address);

    /**
     * 删除收获地址
     * @param address
     */
    void delAddress(Address address);

    /**
     * 将该用户所有的收货地址都改为非默认收货地址
     * @param address
     */
    void updateAllStatus(Address address);

    /**
     * 将指定的收货地址改成收货地址
     * @param address
     */
    void updateStatus(Address address);

}
