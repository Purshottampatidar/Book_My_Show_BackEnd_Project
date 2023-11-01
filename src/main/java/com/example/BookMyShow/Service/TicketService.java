package com.example.BookMyShow.Service;

import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repository.*;
import com.example.BookMyShow.RequestDtos.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private JavaMailSender mailSender;
    public String bookTickets(BookTicketRequest ticketRequest){
          Show show=findRighShow(ticketRequest);

          //find the list of show seats;
        List<ShowSeat> showSeatList=show.getShowSeatList();
        int totalPrice=0;
        for(ShowSeat showSeat : showSeatList){
            if(ticketRequest.getRequestedSeatNo().contains(showSeat.getSeatNo())){
                showSeat.setAvailable(false);
                totalPrice+=showSeat.getCost();
            }
        }
        User user=userRepository.findById(ticketRequest.getUserId()).get();

        Ticket ticket=Ticket.builder()
                .movieName(show.getMovie().getMovieName())
                .theaterAddress(show.getTheater().getAddress())
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .bookedSeats(ticketRequest.getRequestedSeatNo().toString())
                .totalPrice(totalPrice)
                .show(show)
                .user(user)
                .build();

        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);

        ticketRepository.save(ticket);

        //sending mail to user for registering on book my show
        SimpleMailMessage mailMessage=new SimpleMailMessage();

        String body="Hi "+user.getName()+" !"+
                "You have Successfully Booked Ticket with BookMyShow. "
                +"Movie Name "+ticket.getMovieName()+"/n"
                +" Theater Name "+ show.getTheater().getTheaterName()+"/n"
                +"Theater Address "+show.getTheater().getAddress()+"/n"
                +" SeatNo "+ticket.getBookedSeats()+"/n"
                +" Show Data "+ticket.getShowDate()+"/n"
                +" show Time"+ticket.getShowTime();
        mailMessage.setFrom("springacciojob@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Ticket Booked Successfully!");
        mailMessage.setText(body);
        mailSender.send(mailMessage);


        return "Ticket Booked Successfully";
    }

    public Show findRighShow(BookTicketRequest ticketRequest){
        Theater theater = theaterRepository.findById(ticketRequest.getTheaterId()).get();
        Movie movie=movieRepository.findMovieByMovieName(ticketRequest.getMovieName());

        Show show=showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(ticketRequest.getShowDate(),ticketRequest.getShowTime(),movie,theater);
        return show;
    }
}
