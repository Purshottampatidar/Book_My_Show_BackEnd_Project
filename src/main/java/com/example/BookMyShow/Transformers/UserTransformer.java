package com.example.BookMyShow.Transformers;

import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.RequestDtos.AddUserRequest;

public class UserTransformer {

    public static User UserRequestToUserEntity(AddUserRequest userRequest){

        //        User userObj=new User();
        //        userObj.setName(userRequest.getName());
       //         userObj.setEmail(userRequest.getEmail());
       //         userObj.setAge(userRequest.getAge());
       //         userObj.setMobNo(userRequest.getMobNo());


        User userObj=User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .mobNo(userRequest.getMobNo())
                .build();

        return userObj;
    }
}
