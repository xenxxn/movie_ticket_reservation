package com.ticket.reservation.domain.showtime.dto;

import com.ticket.reservation.domain.showtime.entity.Showtime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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

  public static ShowtimeDto fromEntity(Showtime showtime){
    return ShowtimeDto.builder()
        .id(showtime.getId())
        .movieId(showtime.getMovie().getId())
        .theaterId(showtime.getTheater().getId())
        .startTime(showtime.getStartTime())
        .endTime(showtime.getEndTime())
        .build();
  }

  public static List<ShowtimeDto> toResponseList(List<Showtime> showtimeList) {
    return showtimeList.stream()
        .map(ShowtimeDto::fromEntity)
        .collect(Collectors.toList());
  }
}
