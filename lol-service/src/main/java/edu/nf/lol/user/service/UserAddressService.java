package edu.nf.lol.user.service;

import edu.nf.lol.user.entity.Address;
import edu.nf.lol.user.entity.User;

import java.util.List;

/**
 * @author zhangch
 * @date 2020/3/17
 */
public interface UserAddressService {

    List<Address> queryAddress(User user);

    void addAddress(Address address);

    void updateAddress(Address address);

    void delAddress(Address address);

    void setDefaultAddress(Address address);
}
