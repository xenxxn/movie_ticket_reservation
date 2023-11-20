package com.ticket.reservation.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  private final int errorCode;
  private final String message;
}
