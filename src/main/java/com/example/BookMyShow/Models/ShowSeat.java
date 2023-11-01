package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Show_Seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;

    private String seatNo;

    @Enumerated(value=EnumType.STRING)
    private SeatType seatType;

    private Integer cost;
    private boolean isAvailable;
    private boolean isFoodAttached;


    @ManyToOne
    @JoinColumn
    private Show show;
}
