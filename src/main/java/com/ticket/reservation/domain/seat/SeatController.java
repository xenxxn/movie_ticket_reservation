package com.ticket.reservation.domain.seat;

import com.ticket.reservation.domain.seat.dto.SeatDto;
import com.ticket.reservation.domain.seat.dto.SeatInput;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/seats")
public class SeatController {
  private final SeatService seatService;

  @PostMapping("/{seatId}")
  public SeatDto createSeat(@RequestBody SeatInput seatInput, @PathVariable Long seatId) {
    Seat makeSeat = seatService.createSeat(seatInput);
    return SeatDto.fromEntity(makeSeat);
  }

  @DeleteMapping("/{seatId}")
  public ResponseEntity<String> deleteSeat(@PathVariable Long seatId) {
    seatService.deleteSeat(seatId);
    return ResponseEntity.ok("Seat deleted successfully");
  }

}
