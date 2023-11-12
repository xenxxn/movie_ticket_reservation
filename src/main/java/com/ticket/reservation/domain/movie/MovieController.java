package com.ticket.reservation.domain.movie;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieEditInput;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import com.ticket.reservation.domain.movie.dto.MovieOutput;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
  private final MovieService movieService;

  @GetMapping
  public ResponseEntity<MovieOutput> movieSearchInfo(@RequestParam String searchWord) {
    MovieOutput movie = movieService.searchMovie(searchWord);
    return ResponseEntity.ok(movie);
  }

  @GetMapping("/{title}")
  public ResponseEntity<List<MovieOutput>> movieSearchList(@PathVariable String title){
    List<MovieOutput> list = movieService.searchMovieList(title);
    return ResponseEntity.ok(list);
  }

  @PostMapping
  public MovieDto addMovie(@RequestBody MovieInput movieInput) {
    Movie movie = movieService.addMovie(movieInput);
    return MovieDto.fromEntity(movie);
  }

  @PutMapping ("/modification")
  public MovieDto editMovie(@RequestBody MovieEditInput movieEditInput) {
    return movieService.editMovie(movieEditInput);
  }

}
