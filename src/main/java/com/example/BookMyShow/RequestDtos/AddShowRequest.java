package com.example.BookMyShow.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddShowRequest {
    private LocalDate showDate;
    private LocalTime showTime;

    private String movieName;
    private Integer theaterId;
}

