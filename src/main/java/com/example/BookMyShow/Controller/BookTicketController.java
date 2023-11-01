package com.example.BookMyShow.Controller;

import com.example.BookMyShow.RequestDtos.BookTicketRequest;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class BookTicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/bookTicket")
    public ResponseEntity<String> bookTicket(@RequestBody BookTicketRequest ticketRequest){
        String response=ticketService.bookTickets(ticketRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
