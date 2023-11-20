package com.ticket.reservation.domain.theater.controller;

import com.ticket.reservation.domain.theater.dto.TheaterDto;
import com.ticket.reservation.domain.theater.dto.TheaterEditInput;
import com.ticket.reservation.domain.theater.dto.TheaterInput;
import com.ticket.reservation.domain.theater.dto.TheaterOutput;
import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.service.TheaterService;
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
@RequestMapping("/theaters")
public class TheaterController {

  private final TheaterService theaterService;

  @GetMapping("/list/{searchWord}")
  public ResponseEntity<List<TheaterOutput>> searchTheaterList(@PathVariable String searchWord) {
    List<TheaterOutput> theaterOutputList = theaterService.searchTheatersByName(searchWord);
    return ResponseEntity.ok(theaterOutputList);
  }

  @PostMapping
  public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterInput theaterInput) {
    Theater theater = theaterService.addTheater(theaterInput);
    TheaterDto theaterDto = TheaterDto.fromEntity(theater);
    return ResponseEntity.ok(theaterDto);
  }

  @PutMapping("/modification/{theaterId}")
  public ResponseEntity<TheaterDto> editTheater(@PathVariable Long theaterId, @RequestBody TheaterEditInput theaterEditInput) {
    TheaterDto theaterDto = theaterService.editTheater(theaterEditInput);
    return ResponseEntity.ok(theaterDto);
  }

  @GetMapping("/{searchWord}")
  public ResponseEntity<TheaterOutput> searchTheater(@PathVariable String searchWord) {
    TheaterOutput theaterOutput = theaterService.searchSpecificTheaterByName(searchWord);
    return ResponseEntity.ok(theaterOutput);
  }

  @DeleteMapping("/{theaterId}")
  public ResponseEntity<String> deleteTheater(@PathVariable Long theaterId) {
    theaterService.deleteTheater(theaterId);
    return ResponseEntity.ok("영화관이 삭제되었습니다.");
  }
}
