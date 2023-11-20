package com.ticket.reservation.domain.movie.controller;

import com.ticket.reservation.domain.movie.service.MovieService;
import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieEditInput;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import com.ticket.reservation.domain.movie.dto.MovieOutput;
import com.ticket.reservation.domain.movie.entity.Movie;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService movieService;


  @GetMapping("/{title}")
  public ResponseEntity<MovieOutput> movieSearchInfo(@PathVariable String title) {
    MovieOutput movie = movieService.searchMovie(title);
    return ResponseEntity.ok(movie);
  }

  @GetMapping("/list/{title}")
  public List<MovieOutput> movieSearchList(@PathVariable String title) {
    return movieService.searchMovieList(title);
  }

  @PostMapping
  public ResponseEntity<MovieDto> addMovie(@RequestBody MovieInput movieInput) {
    Movie movie = movieService.addMovie(movieInput);
    return ResponseEntity.ok(MovieDto.fromEntity(movie));
  }

  @PutMapping("/modification/{id}")
  public ResponseEntity<MovieDto> editMovie(@PathVariable("id") Long id,
      @RequestBody MovieEditInput movieEditInput) {
    return ResponseEntity.ok(movieService.editMovie(id, movieEditInput));
  }

  @DeleteMapping("/{movieId}")
  public ResponseEntity<String> deleteMovie(@PathVariable Long movieId) {
    movieService.deleteMovie(movieId);
    return ResponseEntity.ok("영화가 삭제되었습니다.");
  }

}
