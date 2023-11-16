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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeEditInput {
  private Long id;
  private Long movieId;
  private Long theaterId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static Showtime toEntity(ShowtimeEditInput showtimeEditInput) {
    Movie movie = Movie.builder()
        .id(showtimeEditInput.getMovieId())
        .build();
    Theater theater = Theater.builder()
        .id(showtimeEditInput.getTheaterId())
        .build();

    return Showtime.builder()
        .id(showtimeEditInput.getId())
        .movie(movie)
        .theater(theater)
        .startTime(showtimeEditInput.getStartTime())
        .endTime(showtimeEditInput.getEndTime())
        .build();
  }

}
