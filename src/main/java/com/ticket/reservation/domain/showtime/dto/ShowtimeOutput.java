package com.ticket.reservation.domain.showtime.dto;

import com.ticket.reservation.domain.movie.dto.MovieOutput;
import com.ticket.reservation.domain.movie.entity.Movie;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.theater.dto.TheaterOutput;
import com.ticket.reservation.domain.theater.entity.Theater;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeOutput {
  private Long showtimeId;
  private Long movieId;
  private Long roomId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public static Showtime toEntity(ShowtimeOutput showtimeOutput) {
    return Showtime.builder()
        .id(showtimeOutput.getShowtimeId())
        .movie(Movie.builder()
            .id(showtimeOutput.getMovieId())
            .build())
        .room(Room.builder()
            .id(showtimeOutput.getRoomId())
            .build())
        .startTime(showtimeOutput.getStartTime())
        .endTime(showtimeOutput.getEndTime())
        .build();
  }

  public static ShowtimeOutput toResponse(ShowtimeDto showtimeDto) {
    return ShowtimeOutput.builder()
        .showtimeId(showtimeDto.getId())
        .movieId(showtimeDto.getMovieId())
        .roomId(showtimeDto.getRoomId())
        .startTime(showtimeDto.getStartTime())
        .endTime(showtimeDto.getEndTime())
        .build();
  }

  public static List<ShowtimeOutput> toResponseList(List<ShowtimeDto> showtimeDtos) {
    return showtimeDtos.stream()
        .map(ShowtimeOutput::toResponse)
        .collect(Collectors.toList());
  }

}