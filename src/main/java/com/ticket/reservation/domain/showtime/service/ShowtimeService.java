package com.ticket.reservation.domain.showtime.service;

import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.movie.repository.MovieRepository;
import com.ticket.reservation.domain.showtime.dto.ShowtimeDto;
import com.ticket.reservation.domain.showtime.dto.ShowtimeInput;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.repository.TheaterRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
  private final ShowtimeRepository showtimeRepository;
  private final MovieRepository movieRepository;
  private final TheaterRepository theaterRepository;

  @Transactional
  public Showtime addShowtime(ShowtimeInput showtimeInput) {
    Showtime showtime = ShowtimeInput.toEntity(showtimeInput);
    validateShowtimeTimes(showtime);
    boolean isExistsTheater = theaterRepository.existsById(showtime.getTheater().getId());
    boolean isExistsMovie = movieRepository.existsById(showtime.getMovie().getId());

    if (!isExistsTheater) {
      throw new NoResultException("해당 영화관이 존재하지 않습니다.");
    }
    if (!isExistsMovie) {
      throw new NoResultException("해당 영화가 존재하지 않습니다.");
    }
    return showtimeRepository.save(showtime);
  }

  @Transactional
  public void deleteSpecificShowtime(Long movieId, Long theaterId, Long showtimeId) {
    Movie movie = validateMovie(movieId);
    Theater theater = validateTheater(theaterId);
    Showtime showtime = showtimeRepository.findById(showtimeId)
        .orElseThrow(() -> new NoResultException("해당 상영회차는 존재하지 않습니다."));

    movie.getShowtimeList().remove(showtime);
    theater.getShowtimeList().remove(showtime);
    movieRepository.save(movie);
    theaterRepository.save(theater);
  }

  @Transactional
  public void deleteAllShowtime(Long movieId, Long theaterId) {
    Movie movie = validateMovie(movieId);
    Theater theater = validateTheater(theaterId);

    List<Showtime> showtimeList = showtimeRepository.findAllByMovieIdAndTheaterId(movieId, theaterId);

    for (Showtime showtime : showtimeList) {
      showtime.setShowtime(null, null);
      showtimeRepository.save(showtime);
    }

    movie.getShowtimeList().clear();
    theater.getShowtimeList().clear();
    movieRepository.save(movie);
    theaterRepository.save(theater);
  }

  private void validateShowtimeTimes(Showtime showtime) {
    LocalDateTime startTime = showtime.getStartTime();
    LocalDateTime endTime = showtime.getEndTime();
    if (startTime.isAfter(endTime)) {
      throw new RuntimeException("상영 시작 시간은 종료 시간보다 이전이어야 합니다.");
    }
  }

  public Movie validateMovie(Long movieId) {
    Movie movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new NoResultException("해당 영화는 존재하지 않습니다."));
    if (movie.getShowtimeList().isEmpty()) {
      throw new NoResultException("존재하지 않는 상영회차입니다.");
    }
    return movie;
  }

  public Theater validateTheater(Long theaterId) {
    Theater theater = theaterRepository.findById(theaterId)
        .orElseThrow(() -> new NoResultException("해당 영화관은 존재하지 않습니다."));
    if (theater.getShowtimeList().isEmpty()) {
      throw new NoResultException("존재하지 않는 상영회차입니다.");
    }
    return theater;
  }
}
