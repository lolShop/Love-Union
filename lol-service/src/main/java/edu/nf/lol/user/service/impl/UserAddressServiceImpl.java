package edu.nf.lol.user.service.impl;

import edu.nf.lol.user.dao.UserAddressDao;
import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;
import edu.nf.lol.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/17
 */
@Service("userAddressService")
@Transactional
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressDao userAddressDao;

    /**
     * 查询收货地址
     * @param user
     * @return
     */
    @Override
    public List<Address> queryAddress(User user) {
        return userAddressDao.queryAddress(user);
    }

    /**
     * 新增收货地址
     * @param address
     */
    @Override
    public void addAddress(Address address) {
        userAddressDao.addAddress(address);
    }

    /**
     * 修改收货地址
     * @param address
     */
    @Override
    public void updateAddress(Address address) {
        userAddressDao.updateAddress(address);
    }

    /**
     * 删除收获地址
     * @param address
     */
    @Override
    public void delAddress(Address address) {
        userAddressDao.delAddress(address);
    }

    /**
     * 设置默认收货地址
     * 先将所有的收货地址设置为非默认收货地址，然后将指定的收货地址设置为默认收货地址
     * @param address
     */
    @Override
    public void setDefaultAddress(Address address) {
        userAddressDao.updateAllStatus(address);
        userAddressDao.updateStatus(address);
    }
}
