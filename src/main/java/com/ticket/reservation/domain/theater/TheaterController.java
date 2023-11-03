package com.ticket.reservation.domain.theater;

import com.ticket.reservation.domain.theater.dto.TheaterDto;
import com.ticket.reservation.domain.theater.dto.TheaterEditInput;
import com.ticket.reservation.domain.theater.dto.TheaterInput;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping()
  public TheaterDto addTheater(@RequestBody TheaterInput theaterInput) {
    Theater theater = theaterService.addTheater(theaterInput);
    return TheaterDto.fromEntity(theater);
  }

  @PostMapping("/modification")
  public TheaterDto editTheater(@RequestBody TheaterEditInput theaterEditInput) {
    return theaterService.editTheater(theaterEditInput);
  }
}
