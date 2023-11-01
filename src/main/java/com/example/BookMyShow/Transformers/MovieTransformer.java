package com.example.BookMyShow.Transformers;

import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.RequestDtos.AddMovieRequest;

public class MovieTransformer {
    public static Movie convertMovieRequestToMovie(AddMovieRequest movieRequest){
        Movie movieObj=Movie.builder()
                .movieName(movieRequest.getMovieName())
                .genre(movieRequest.getGenre())
                .rating(movieRequest.getRating())
                .releaseDate(movieRequest.getReleaseDate())
                .build();
        return movieObj;

    }
}
