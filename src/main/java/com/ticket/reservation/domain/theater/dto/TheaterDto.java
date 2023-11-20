package com.ticket.reservation.domain.theater.dto;

import com.ticket.reservation.domain.theater.entity.Theater;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TheaterDto {

  private Long id;

  private String name;

  private String location;

  private LocalTime openTime;

  private LocalTime closeTime;

  public static TheaterDto fromEntity(Theater theater) {
    return TheaterDto.builder()
        .id(theater.getId())
        .name(theater.getName())
        .location(theater.getLocation())
        .openTime(theater.getOpenTime())
        .closeTime(theater.getCloseTime())
        .build();
  }
}
