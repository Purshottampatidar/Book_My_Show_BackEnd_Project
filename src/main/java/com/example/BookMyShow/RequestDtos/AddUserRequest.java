package com.example.BookMyShow.RequestDtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
    private String name;

    private String mobNo;

    @Column(unique = true)
    private String email;

    private  int age;
}
