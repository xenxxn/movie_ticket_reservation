package com.ticket.reservation.domain.showtime.dto;

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

}
