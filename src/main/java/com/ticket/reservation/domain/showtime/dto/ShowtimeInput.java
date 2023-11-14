package com.ticket.reservation.domain.showtime.dto;

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

  private LocalDateTime startTime;
  private LocalDateTime endTime;


  public static Showtime toEntity(ShowtimeInput showtimeInput) {
    return Showtime.builder()
        .startTime(showtimeInput.getStartTime())
        .endTime(showtimeInput.getEndTime())
        .build();
  }
}
