package com.ticket.reservation.domain.seat;

import com.ticket.reservation.domain.room.Room;
import com.ticket.reservation.domain.room.RoomRepository;
import com.ticket.reservation.domain.seat.dto.SeatDto;
import com.ticket.reservation.domain.seat.dto.SeatInput;
import com.ticket.reservation.domain.seat.dto.SeatOutput;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
  private final SeatRepository seatRepository;
  private final RoomRepository roomRepository;

  @Transactional
  public Seat createSeat(SeatInput seatInput) {
    Seat seat = SeatInput.toEntity(seatInput);
    if (isExistsSeat(seat)) {
      throw new RuntimeException("이미 존재하는 좌석입니다.");
    }
    boolean isExistsRoom = roomRepository.existsById(seat.getRoomId());
    if (!isExistsRoom) {
      throw new NoResultException("존재하지 않는 상영관입니다.");
    }
    return seatRepository.save(seat);
  }


  public boolean isExistsSeat(Seat seat) {
    return seatRepository.existsByRowAndNumber(seat.getRow(), seat.getNumber());
  }

  @Transactional
  public void deleteSeat(Seat seat) {
    Seat findSeat = seatRepository.findById(seat.getId())
        .orElseThrow(() -> new NoResultException("존재하지 않는 좌석입니다."));
    Room room = roomRepository.findById(findSeat.getRoomId())
        .orElseThrow(() -> new NoResultException("존재하지 않는 상영관입니다."));
    if (room != null) {
      findSeat.removeSeat(room);
      seatRepository.delete(findSeat);
    } else {
      throw new NoResultException("존재하지 않는 상영관입니다.");
    }
  }

  public List<SeatOutput> searchSeatList(Long roomId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new NoResultException("존재하지 않는 상영관입니다."));
    List<Seat> seats = seatRepository.findSeatByRoomOrderByRow(room);
    List<SeatDto> seatDtos = SeatDto.toResponseList(seats);
    return SeatOutput.toResponseList(seatDtos);
  }


}
