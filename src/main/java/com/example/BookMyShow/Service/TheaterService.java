package com.example.BookMyShow.Service;

import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.Models.TheaterSeat;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.RequestDtos.AddTheaterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;
    public String addTheater(AddTheaterRequest theaterRequest){

        //create the theater Entity
        Theater theater=Theater.builder()
                .theaterName(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .city(theaterRequest.getCity())
                .build();

        //create the theater seat entity;

        createTheaterSeat(theater,theaterRequest);
        return "theater and its theaterSeats is added to Db";
    }

    public void createTheaterSeat(Theater theater,AddTheaterRequest theaterRequest) {
        int noOfClassicSeats=theaterRequest.getNoOfClassicSeats();
        int noOfPremiumSeats=theaterRequest.getNoOfPremiumSeats();
        int noOfSeatsPreRow=theaterRequest.getNoOfSeatsPerRow();

        List<TheaterSeat> theaterSeatList=new ArrayList<TheaterSeat>();

        //for classic seats;
        int row=0;
        char ch='A';
        for(int i=1;i<=noOfClassicSeats;i++){
            if(i%noOfSeatsPreRow==1){
                row++;
                ch = 'A';
            }
            String seatNo=row+" "+ch;
            ch=(char)(ch+1);


            TheaterSeat theaterSeat= TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();


            theaterSeatList.add(theaterSeat);
        }

        //for premium seats
        ch='A';
        for(int i=1;i<=noOfPremiumSeats;i++){
            if(i%noOfSeatsPreRow==1){
                row++;
                ch = 'A';
            }
            String seatNo=row+" "+ch;
            ch=(char)(ch+1);


            TheaterSeat theaterSeat= TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();


            theaterSeatList.add(theaterSeat);
        }

        //adding theaterSeatList to theater
        theater.setTheaterSeatList(theaterSeatList);

        //save the theater;
        theaterRepository.save(theater);

    }
}
