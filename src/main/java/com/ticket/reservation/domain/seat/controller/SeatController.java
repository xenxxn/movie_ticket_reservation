package com.ticket.reservation.domain.seat.controller;

import com.ticket.reservation.domain.seat.service.SeatService;
import com.ticket.reservation.domain.seat.dto.SeatDto;
import com.ticket.reservation.domain.seat.dto.SeatEditInput;
import com.ticket.reservation.domain.seat.dto.SeatInput;
import com.ticket.reservation.domain.seat.dto.SeatOutput;
import com.ticket.reservation.domain.seat.entity.Seat;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/seats")
public class SeatController {
  private final SeatService seatService;

  @PostMapping("/{seatId}")
  public ResponseEntity<SeatDto> createSeat(@RequestBody SeatInput seatInput, @PathVariable Long seatId) {
    Seat makeSeat = seatService.createSeat(seatInput);
    SeatDto seatDto = SeatDto.fromEntity(makeSeat);
    return ResponseEntity.ok(seatDto);
  }

  @DeleteMapping("/{roomId}/{seatId}")
  public ResponseEntity<String> deleteSpecificSeat(@PathVariable Long roomId, @PathVariable Long seatId) {
    seatService.deleteSpecificSeat(roomId, seatId);
    return ResponseEntity.ok("해당 좌석이 삭제되었습니다.");
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity<String> deleteAllSeats(@PathVariable Long roomId) {
    seatService.deleteAllSeats(roomId);
    return ResponseEntity.ok("모든 좌석이 삭제되었습니다.");
  }

  @GetMapping("/list/{roomId}")
  public ResponseEntity<List<SeatOutput>> searchSeatList(@PathVariable Long roomId) {
    return ResponseEntity.ok(seatService.searchSeatList(roomId));
  }

  @PutMapping("/modification/{seatId}")
  public ResponseEntity<SeatDto> editSeat(@PathVariable Long seatId, @RequestBody SeatEditInput seatEditInput) {
    return ResponseEntity.ok(seatService.editSeat(seatId, seatEditInput));
  }
}
