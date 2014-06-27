package com.qatang.cms.service.user;

import com.qatang.cms.entity.user.User;

import java.util.List;

/**
 * Created by qatang on 14-6-12.
 */
public interface UserService {
    public User getByUsername(String username);

    public User get(Long id);

    public List<User> getList();

    public User save(User user);

    public User update(User user);

    public void delete(Long id);
}
