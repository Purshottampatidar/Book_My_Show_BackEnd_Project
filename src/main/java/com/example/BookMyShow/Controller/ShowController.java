package com.example.BookMyShow.Controller;

import com.example.BookMyShow.RequestDtos.AddShowRequest;
import com.example.BookMyShow.RequestDtos.AddShowSeatRequest;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody AddShowRequest showRequest) {
        String response = showService.addShow(showRequest);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping("/createShowSeat")
    public ResponseEntity<String> createShowSeat(@RequestBody AddShowSeatRequest showSeatRequest){
        String response = showService.createShowSeat(showSeatRequest);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
