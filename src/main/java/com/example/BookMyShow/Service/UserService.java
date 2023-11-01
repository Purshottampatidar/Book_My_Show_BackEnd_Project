package com.example.BookMyShow.Service;

import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.RequestDtos.AddUserRequest;
import com.example.BookMyShow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String addUser(AddUserRequest userRequest){



         User user = UserTransformer.UserRequestToUserEntity(userRequest);
         userRepository.save(user);

         //sending mail to user for registering on book my show
         SimpleMailMessage mailMessage=new SimpleMailMessage();

         String body="Hi "+user.getName()+" !"+
                "You have Successfully registered with BookMyShow. "+
                "Please login to book my show";
         mailMessage.setFrom("springacciojob@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Welcome to BookMyShow!");
        mailMessage.setText(body);
        mailSender.send(mailMessage);


        return "User add Successfully";
    }
}
