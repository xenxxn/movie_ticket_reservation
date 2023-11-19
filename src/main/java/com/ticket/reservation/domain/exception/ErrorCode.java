package com.ticket.reservation.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  ALREADY_EXISTS_SEAT(400, "이미 존재하는 좌석입니다.", HttpStatus.BAD_REQUEST),
  ALREADY_RESERVED_SEAT_OR_UNAVAILABLE(400, "이미 예약된 좌석이거나 예약이 불가능한 상태입니다.", HttpStatus.BAD_REQUEST),
  NOT_EXISTS_THEATER(400, "존재하지 않는 영화관입니다.", HttpStatus.BAD_REQUEST),
  NOT_EXISTS_ROOM(400, "존재하지 않는 상영관입니다.", HttpStatus.BAD_REQUEST),
  NOT_EXISTS_MOVIE(400, "존재하지 않는 영화입니다.", HttpStatus.BAD_REQUEST),
  NOT_EXISTS_SEAT(400, "존재하지 않는 좌석입니다.", HttpStatus.BAD_REQUEST),
  NOT_EXISTS_SHOWTIME(400, "존재하지 않는 상영회차입니다.", HttpStatus.BAD_REQUEST),
  NOT_EXISTS_RESERVATION(400, "존재하지 않는 예약입니다.", HttpStatus.BAD_REQUEST),
  INVALID_SHOWTIME_TIMINGS(400, "상영 시작 시간은 종료 시간보다 이전이어야 합니다.", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

  private final int errorCode;
  private final String message;
  private final HttpStatus httpStatus;
}
