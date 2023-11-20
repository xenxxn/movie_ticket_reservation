package com.ticket.reservation.domain.reservation.controller;

import com.ticket.reservation.domain.reservation.dto.ReservationDto;
import com.ticket.reservation.domain.reservation.dto.ReservationInput;
import com.ticket.reservation.domain.reservation.dto.ReservationOutput;
import com.ticket.reservation.domain.reservation.entity.Reservation;
import com.ticket.reservation.domain.reservation.service.ReservationService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationDto> makeReservation(
      @RequestBody ReservationInput reservationInput) {
    Reservation reservation = reservationService.makeReservation(reservationInput);
    ReservationDto reservationDto = ReservationDto.fromEntity(reservation);
    return ResponseEntity.ok(reservationDto);
  }

  @DeleteMapping("/{seatId}/{showtimeId}/{reservationId}")
  public ResponseEntity<String> deleteReservation(@PathVariable Long seatId,
      @PathVariable Long showtimeId, @PathVariable Long reservationId) {
    reservationService.deleteReservation(seatId, showtimeId, reservationId);
    return ResponseEntity.ok("해당 예약이 삭제되었습니다.");
  }

  @GetMapping("/list/{showtimeId}")
  public ResponseEntity<List<ReservationOutput>> searchReservationsByShowtime(
      @PathVariable Long showtimeId) {
    return ResponseEntity.ok(reservationService.searchReservationsByShowtime(showtimeId));
  }

  @GetMapping("/{reservationId}")
  public ResponseEntity<ReservationOutput> searchSpecificReservation(
      @PathVariable Long reservationId) {
    return ResponseEntity.ok(reservationService.searchSpecificReservation(reservationId));
  }
}
