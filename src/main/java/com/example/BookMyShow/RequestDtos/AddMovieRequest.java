package com.example.BookMyShow.RequestDtos;

import com.example.BookMyShow.Enums.Genre;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddMovieRequest {
    private String movieName;
    private double rating;
    private Genre genre;
    private LocalDate releaseDate;
}
