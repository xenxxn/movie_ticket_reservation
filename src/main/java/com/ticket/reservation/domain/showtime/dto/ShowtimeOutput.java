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

  private String movieTitle;
  private String movieDirector;
  private String roomName;
  private LocalDateTime startTime;
  private LocalDateTime endTime;


  public static ShowtimeOutput toResponse(ShowtimeDto showtimeDto) {
    return ShowtimeOutput.builder()
        .movieTitle(showtimeDto.getMovieTitle())
        .movieDirector(showtimeDto.getMovieDirector())
        .roomName(showtimeDto.getRoomName())
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