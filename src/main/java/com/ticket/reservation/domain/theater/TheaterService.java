package com.ticket.reservation.domain.theater;

import com.ticket.reservation.domain.theater.dto.TheaterInput;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TheaterService {
  private final TheaterRepository theaterRepository;

  public List<Theater> searchTheaterByName(String name) {
    List<Theater> searchTheaterResults = theaterRepository.findByNameContaining(name);
    if (searchTheaterResults.isEmpty()) {
      throw new RuntimeException("찾으시는 영화관이 없습니다.");
    }
    return searchTheaterResults;
  }

  @Transactional
  public Theater addTheater(TheaterInput theaterInput) {
    Theater theater = TheaterInput.toEntity(theaterInput);
    return theaterRepository.save(theater);
  }
}
