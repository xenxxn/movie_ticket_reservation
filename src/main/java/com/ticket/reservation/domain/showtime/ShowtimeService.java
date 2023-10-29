package com.ticket.reservation.domain.showtime;

import com.ticket.reservation.domain.movie.Movie;
import com.ticket.reservation.domain.movie.MovieRepository;
import com.ticket.reservation.domain.theater.Theater;
import com.ticket.reservation.domain.theater.TheaterRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
  private final ShowtimeRepository showtimeRepository;
  private final MovieRepository movieRepository;
  private final TheaterRepository theaterRepository;

  public Showtime createShowtime(Showtime showtime) {
    Optional<Movie> movie = movieRepository.findById(showtime.getMovie().getId());
    Optional<Theater> theater = theaterRepository.findById(showtime.getTheater().getId());
    if (theater.isPresent()) {
      Theater theaterResult = theater.get();
      if (movie.isPresent()) {
        Movie movieResult = movie.get();
        showtime.addShowtime(movieResult, theaterResult);
        return showtimeRepository.save(showtime);
      }
    } else {
      throw new RuntimeException("존재하지 않는 영화 및 영화관 입니다.");
    }
    return showtime;
  }
}
