package com.mh.msw.mapper;

import com.mh.msw.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int addUser(User user) ;
//    int deleteBookByid(Integer id);haha
    int updateUserByid(User user) ;
    User getUserByid(Integer id) ;
    List<User> getAllUsers() ;
}
