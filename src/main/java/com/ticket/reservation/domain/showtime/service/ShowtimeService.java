package com.ticket.reservation.domain.showtime.service;

import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.movie.repository.MovieRepository;
import com.ticket.reservation.domain.showtime.dto.ShowtimeDto;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
  private final ShowtimeRepository showtimeRepository;
  private final MovieRepository movieRepository;
  private final TheaterRepository theaterRepository;

  public ShowtimeDto createShowtime(Showtime showtime) {
    Movie movie = movieRepository.findById(showtime.getMovieId())
        .orElseThrow(() -> new RuntimeException("해당 영화가 존재하지 않습니다."));

    Theater theater = theaterRepository.findById(showtime.getTheaterId())
        .orElseThrow(() -> new RuntimeException("해당 상영관이 존재하지 않습니다."));

    ShowtimeDto showtimeDto = ShowtimeDto.fromEntity(showtime);
    showtimeRepository.save(showtime);
    return showtimeDto;
  }

}
