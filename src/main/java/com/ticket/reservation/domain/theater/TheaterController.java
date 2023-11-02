package com.ticket.reservation.domain.theater;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/theater")
public class TheaterController {
  private final TheaterService theaterService;

  @GetMapping("/list")
  public ResponseEntity searchTheaterByName(String name) {
    List<Theater> list = theaterService.searchTheaterByName(name);
    return ResponseEntity.ok(list);
  }
}
