package com.example.BookMyShow.Service;

import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.ShowSeatRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.RequestDtos.AddShowRequest;
import com.example.BookMyShow.RequestDtos.AddShowSeatRequest;
import com.example.BookMyShow.Transformers.ShowTransformer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    public String addShow(AddShowRequest showRequest) {

        Show show= ShowTransformer.ConvertShowRequestToShowEntity(showRequest);

        //getting movie by movieName;
        Movie movie=movieRepository.findMovieByMovieName(showRequest.getMovieName());

        //getting theater by theaterId;
        Optional<Theater> optionalTheater=theaterRepository.findById(showRequest.getTheaterId());
        Theater theater=optionalTheater.get();

        //setting FK values
        show.setMovie(movie);
        show.setTheater(theater);

        //setting the bidirectional values
        movie.getShowList().add(show);
        theater.getShowList().add(show);

        //saving the child
        showRepository.save(show);
        return "show has been added successfully";

    }

    public String createShowSeat(AddShowSeatRequest showSeatRequest){

        Show show = showRepository.findById(showSeatRequest.getShowId()).get();

        Theater theater=show.getTheater();

        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        List<ShowSeat> showSeatList=new ArrayList<>();

        for(TheaterSeat theaterSeat : theaterSeatList){
             ShowSeat showSeat = ShowSeat.builder()
                     .seatNo(theaterSeat.getSeatNo())
                     .seatType(theaterSeat.getSeatType())
                     .isAvailable(true)
                     .isFoodAttached(false)
                     .show(show)
                     .build();

             if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                 showSeat.setCost(showSeatRequest.getPriceOfClassicSeat());
             }else{
                 showSeat.setCost(showSeatRequest.getPriceOfPremiumSeat());
             }

             showSeatList.add(showSeat);
        }
        show.setShowSeatList(showSeatList);

        showSeatRepository.saveAll(showSeatList);
        return "show seats added successfully";
    }
}
