package com.ticket.reservation.domain.showtime.dto;

import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.theater.entity.Theater;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeInput {
  private Long movieId;
  private Long theaterId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static Showtime toEntity(ShowtimeInput showtimeInput) {
    Movie movie = Movie.builder().id(showtimeInput.getMovieId()).build();
    Theater theater = Theater.builder().id(showtimeInput.getTheaterId()).build();

    return Showtime.builder()
        .startTime(showtimeInput.getStartTime())
        .endTime(showtimeInput.getEndTime())
        .movie(movie)
        .theater(theater)
        .build();
  }
}
