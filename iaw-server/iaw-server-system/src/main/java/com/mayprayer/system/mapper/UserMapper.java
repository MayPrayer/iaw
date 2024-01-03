package com.mayprayer.system.mapper;

import org.springframework.security.core.userdetails.User;

public interface UserMapper {


    User loadUserByUserCode(String username);


}
