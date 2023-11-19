package com.ticket.reservation.domain.theater.service;

import com.ticket.reservation.domain.exception.CustomException;
import com.ticket.reservation.domain.exception.ErrorCode;
import com.ticket.reservation.domain.theater.dto.TheaterDto;
import com.ticket.reservation.domain.theater.dto.TheaterEditInput;
import com.ticket.reservation.domain.theater.dto.TheaterInput;
import com.ticket.reservation.domain.theater.dto.TheaterOutput;
import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.repository.TheaterRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TheaterService {

  private final TheaterRepository theaterRepository;

  @Transactional
  public Theater addTheater(TheaterInput theaterInput) {
    Theater theater = Theater.toEntityFromInput(theaterInput);
    return theaterRepository.save(theater);
  }

  public List<TheaterOutput> searchTheatersByName(String name) {
    List<TheaterDto> theaterDtos = theaterRepository.findByNameContaining(name);
    if (theaterDtos.isEmpty()) {
      throw new CustomException(ErrorCode.NOT_EXISTS_THEATER);
    }
    return TheaterOutput.toResponse(theaterDtos);
  }

  public TheaterOutput searchSpecificTheaterByName(String name) {
    Theater theater = theaterRepository.findByName(name);
    if (theater == null) {
      throw new CustomException(ErrorCode.NOT_EXISTS_THEATER);
    }
    TheaterDto theaterDto = TheaterDto.fromEntity(theater);
    return TheaterOutput.toResponse(theaterDto);
  }


  @Transactional
  public TheaterDto editTheater(TheaterEditInput theaterEditInput) {
    Theater theater = validateTheater(theaterEditInput.getId());

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

  @Transactional
  public void deleteTheater(Long theaterId) {
    Theater theater = validateTheater(theaterId);
    theaterRepository.delete(theater);
  }

  public Theater validateTheater(Long theaterId) {
    return theaterRepository.findById(theaterId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_THEATER));
  }
}
