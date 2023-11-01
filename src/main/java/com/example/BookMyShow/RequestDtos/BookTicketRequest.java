package com.example.BookMyShow.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTicketRequest {
    private String movieName;
    private Integer theaterId;
    private LocalDate showDate;
    private LocalTime showTime;
    private List<String> requestedSeatNo;
    private Integer userId;
}
