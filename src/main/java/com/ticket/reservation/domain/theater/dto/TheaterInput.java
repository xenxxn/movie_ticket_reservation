package com.ticket.reservation.domain.theater.dto;

import com.ticket.reservation.domain.theater.entity.Theater;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterInput {
  private String name;
  private String location;
  private LocalTime openTime;
  private LocalTime closeTime;

  public static Theater toEntity(TheaterInput theaterInput) {
    return Theater.builder()
        .name(theaterInput.getName())
        .location(theaterInput.getLocation())
        .openTime(theaterInput.getOpenTime())
        .closeTime(theaterInput.getCloseTime())
        .build();
  }
}
