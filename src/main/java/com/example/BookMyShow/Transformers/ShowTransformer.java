package com.example.BookMyShow.Transformers;

import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.RequestDtos.AddShowRequest;

public class ShowTransformer {
    public static Show ConvertShowRequestToShowEntity(AddShowRequest showRequest) {
        Show showObj=Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .build();
        return showObj;
    }
}
