package com.ticket.reservation.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatStatus {
  RESERVED, UNRESERVED
}
