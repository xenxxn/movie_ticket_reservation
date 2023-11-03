package com.ticket.reservation.domain.movie;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieEditInput;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public ResponseEntity<Movie> movieSearchInfo(@RequestParam String title) {
    Movie movie = movieService.searchMovie(title);
    return ResponseEntity.ok(movie);
  }

  @GetMapping("/list")
  public ResponseEntity<List> movieSearchList(@RequestParam String word){
    List<Movie> list = movieService.searchMovieList(word);
    return ResponseEntity.ok(list);
  }

  @PostMapping()
  public MovieDto addMovie(@RequestBody MovieInput movieInput) {
    Movie movie = movieService.addMovie(movieInput);
    return MovieDto.fromEntity(movie);
  }

  @PostMapping("/modification")
  public MovieDto editMovie(@RequestBody MovieEditInput movieEditInput) {
    return movieService.editMovie(movieEditInput);
  }

}
