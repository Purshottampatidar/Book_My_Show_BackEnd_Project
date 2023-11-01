package com.example.BookMyShow.RequestDtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddShowSeatRequest {
    private Integer showId;
    private Integer priceOfClassicSeat;
    private Integer priceOfPremiumSeat;
}
