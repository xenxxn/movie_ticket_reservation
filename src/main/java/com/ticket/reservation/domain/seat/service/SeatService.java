package com.ticket.reservation.domain.seat.service;

import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.room.repository.RoomRepository;
import com.ticket.reservation.domain.seat.dto.SeatDto;
import com.ticket.reservation.domain.seat.dto.SeatEditInput;
import com.ticket.reservation.domain.seat.dto.SeatInput;
import com.ticket.reservation.domain.seat.dto.SeatOutput;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.repository.SeatRepository;
import java.util.List;
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
    boolean isExistsRoom = roomRepository.existsById(seat.getRoom().getId());
    if (!isExistsRoom) {
      throw new NoResultException("존재하지 않는 상영관입니다.");
    }
    return seatRepository.save(seat);
  }


  public boolean isExistsSeat(Seat seat) {
    return seatRepository.existsByRowAndNumber(seat.getRow(), seat.getNumber());
  }

  @Transactional
  public void deleteSpecificSeat(Long roomId, Long seatId) {
    Room room = validateRoom(roomId);
    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new NoResultException("존재하지 않는 좌석입니다."));

    room.getSeats().remove(seat);
    roomRepository.save(room);
  }

  @Transactional
  public void deleteAllSeats(Long roomId) {
    Room room = validateRoom(roomId);
    List<Seat> seats = seatRepository.findAllByRoomId(roomId);

    for (Seat seat : seats) {
      seat.setSeat(null);
      seatRepository.save(seat);
    }

    room.getSeats().clear();
    roomRepository.save(room);
  }

  public List<SeatOutput> searchSeatList(Long roomId) {
    Room room = validateRoom(roomId);
    List<Seat> seats = seatRepository.findSeatByRoomOrderByRow(room);
    List<SeatDto> seatDtos = SeatDto.toResponseList(seats);
    return SeatOutput.toResponseList(seatDtos);
  }

  @Transactional
  public SeatDto editSeat(Long seatId, SeatEditInput seatEditInput) {
    Seat seat = seatRepository.findById(seatId)
        .orElseThrow(() -> new NoResultException("존재하지 않는 좌석입니다."));
    seat.updateSeat(seatEditInput);
    seatRepository.save(seat);
    return SeatDto.fromEntity(seat);
  }

  public Room validateRoom(Long roomId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new NoResultException("해당 상영관을 찾을 수 없습니다."));
    if (room.getSeats().isEmpty()) {
      throw new NoResultException("존재하지 않는 좌석입니다.");
    }
    return room;
  }
}
