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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeEditInput {
  private Long id;
  private Long movieId;
  private Long roomId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static Showtime toEntity(ShowtimeEditInput showtimeEditInput) {
    Movie movie = Movie.builder()
        .id(showtimeEditInput.getMovieId())
        .build();
    Room room = Room.builder()
        .id(showtimeEditInput.getRoomId())
        .build();

    return Showtime.builder()
        .id(showtimeEditInput.getId())
        .movie(movie)
        .room(room)
        .startTime(showtimeEditInput.getStartTime())
        .endTime(showtimeEditInput.getEndTime())
        .build();
  }

}
