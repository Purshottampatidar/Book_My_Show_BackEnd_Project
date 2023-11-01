package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.City;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theater")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;

    private String theaterName;
    private String address;

    @Enumerated(value= EnumType.STRING)
    private City city;

    @OneToMany(mappedBy = "theater",cascade =CascadeType.ALL)
    List<TheaterSeat> theaterSeatList=new ArrayList<TheaterSeat>();

    @OneToMany(mappedBy = "theater" ,cascade = CascadeType.ALL)
    List<Show> showList=new ArrayList<Show>();

}
