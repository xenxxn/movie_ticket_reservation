package com.ticket.reservation.domain.showtime.dto;

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