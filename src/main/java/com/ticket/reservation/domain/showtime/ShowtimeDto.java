package com.ticket.reservation.domain.showtime;

import com.ticket.reservation.domain.movie.Movie;
import com.ticket.reservation.domain.theater.Theater;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ShowtimeDto {
  private Long id;
  private Long movieId;
  private Long theaterId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

//  public void addShowtime(Movie movie, Theater theater) {
//    this.movie = movie;
//    this.theater = theater;
//  }

  public static ShowtimeDto fromEntity(Showtime showtime){
    return ShowtimeDto.builder()
        .id(showtime.getId())
        .movieId(showtime.getMovieId())
        .theaterId(showtime.getTheaterId())
        .startTime(showtime.getStartTime())
        .endTime(showtime.getEndTime())
        .build();
  }
}
