package edu.nf.lol.user.service.impl;

import edu.nf.lol.user.dao.CollectDao;
import edu.nf.lol.user.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @date 2020/3/22
 */
@Service("collectService")
@Transactional
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;
    @Override
    public void addCollect(Integer uid, Integer pid) {
        collectDao.addCollect(uid,pid);
    }
}