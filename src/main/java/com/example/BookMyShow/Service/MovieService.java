package com.example.BookMyShow.Service;

import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.RequestDtos.AddMovieRequest;
import com.example.BookMyShow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(AddMovieRequest movieRequest) throws Exception {

        //converting movie request to movie entity;
        Movie movie= MovieTransformer.convertMovieRequestToMovie(movieRequest);


        movieRepository.save(movie);
        return "movie Save successfully";
    }
}
