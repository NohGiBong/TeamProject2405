package com.busanit.controller;

import com.busanit.entity.Movie;
import com.busanit.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

//    @GetMapping("/movies/test")
//    public List<Movie> getMovies() {
//        return movieService.getAllMovie();
//    }
}
