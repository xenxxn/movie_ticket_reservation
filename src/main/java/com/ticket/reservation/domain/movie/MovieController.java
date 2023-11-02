package com.ticket.reservation.domain.movie;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
  private final MovieService movieService;

  @GetMapping("/info")
  public ResponseEntity movieSearchInfo(@RequestParam String title) {
    Movie movie = movieService.searchMovie(title);
    return ResponseEntity.ok(movie);
  }

  @GetMapping("/list")
  public ResponseEntity movieSearchList(@RequestParam String word){
    List<Movie> list = movieService.searchMovieList(word);
    return ResponseEntity.ok(list);
  }


}
