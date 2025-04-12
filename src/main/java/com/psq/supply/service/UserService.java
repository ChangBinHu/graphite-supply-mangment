package com.psq.supply.service;

import com.psq.supply.entity.User;
import com.psq.supply.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author psq
 * @description
 * @create 2025-04-02 20:11
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean createUser(User user) {
        validateParam(user);

        boolean userNameExist = getAllUser()
                .stream()
                .anyMatch(userDb -> user.getUsername().equals(userDb.getUsername()));
        if (userNameExist) {
            throw new RuntimeException("用户名字已经存在");
        }
        try{
            userRepository.save(user) ;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void validateParam(User user) {
        if (Objects.isNull(user) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            throw new RuntimeException("用户名字和用户密码都不能为空");
        }
    }


    private List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return CollectionUtils.isEmpty(userList) ? Collections.EMPTY_LIST : userList;
    }

    public boolean login(String userName, String  password) {
        validateParam(new User().setUsername(userName).setPassword(password));

        boolean validUserNameAndPassword = getAllUser()
                .stream()
                .anyMatch(userDb -> userDb.getUsername().equals(userName) && userDb.getPassword().equals(password));
        return validUserNameAndPassword;
    }
}
