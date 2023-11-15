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




  private void validateShowtimeTimes(Showtime showtime) {
    LocalDateTime startTime = showtime.getStartTime();
    LocalDateTime endTime = showtime.getEndTime();
    if (startTime.isAfter(endTime)) {
      throw new RuntimeException("상영 시작 시간은 종료 시간보다 이전이어야 합니다.");
    }
  }
}
