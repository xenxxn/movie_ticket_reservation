package com.ticket.reservation.domain.showtime.dto;

import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.showtime.entity.Showtime;
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
  private Long roomId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static Showtime toEntity(ShowtimeInput showtimeInput) {
    Movie movie = Movie.builder().id(showtimeInput.getMovieId()).build();
    Room room = Room.builder().id(showtimeInput.getRoomId()).build();

    return Showtime.builder()
        .startTime(showtimeInput.getStartTime())
        .endTime(showtimeInput.getEndTime())
        .movie(movie)
        .room(room)
        .build();
  }
}
