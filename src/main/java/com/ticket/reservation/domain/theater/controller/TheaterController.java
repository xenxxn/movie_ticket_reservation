package com.ticket.reservation.domain.theater.controller;

import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.service.TheaterService;
import com.ticket.reservation.domain.theater.dto.TheaterDto;
import com.ticket.reservation.domain.theater.dto.TheaterEditInput;
import com.ticket.reservation.domain.theater.dto.TheaterInput;
import com.ticket.reservation.domain.theater.dto.TheaterOutput;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    List<TheaterOutput> theaterOutputList = theaterService.searchTheaterByName(searchWord);
    return ResponseEntity.ok(theaterOutputList);
  }

  @PostMapping
  public TheaterDto addTheater(@RequestBody TheaterInput theaterInput) {
    Theater theater = theaterService.addTheater(theaterInput);
    return TheaterDto.fromEntity(theater);
  }

  @PutMapping("/modification")
  public TheaterDto editTheater(@RequestBody TheaterEditInput theaterEditInput) {
    return theaterService.editTheater(theaterEditInput);
  }

  @GetMapping("/{searchWord}")
  public TheaterOutput searchTheater(@PathVariable String searchWord) {
    return theaterService.searchTheater(searchWord);
  }
}
