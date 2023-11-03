package com.ticket.reservation.domain.theater.dto;


import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterEditInput {
  private Long id;
  private String name;
  private String location;
  private LocalTime openTime;
  private LocalTime closeTime;
}
