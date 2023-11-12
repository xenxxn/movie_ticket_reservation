package com.ticket.reservation.domain.theater;

import com.ticket.reservation.domain.theater.dto.TheaterDto;
import com.ticket.reservation.domain.theater.dto.TheaterEditInput;
import com.ticket.reservation.domain.theater.dto.TheaterInput;
import java.util.List;
import javax.persistence.NoResultException;
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
      throw new NoResultException("찾으시는 영화관이 없습니다.");
    }
    return searchTheaterResults;
  }

  @Transactional
  public Theater addTheater(TheaterInput theaterInput) {
    Theater theater = TheaterInput.toEntity(theaterInput);
    return theaterRepository.save(theater);
  }

  @Transactional
  public TheaterDto editTheater(TheaterEditInput theaterEditInput) {
    Theater theater = theaterRepository.findById(theaterEditInput.getId())
        .orElseThrow(() -> new NoResultException("해당 영화관은 존재하지 않습니다."));

    Theater editTheater = Theater.builder()
        .id(theater.getId())
        .name(theaterEditInput.getName())
        .location(theaterEditInput.getLocation())
        .openTime(theaterEditInput.getOpenTime())
        .closeTime(theaterEditInput.getCloseTime())
        .build();

    Theater saved = theaterRepository.save(editTheater);
    return TheaterDto.fromEntity(saved);
  }
}
