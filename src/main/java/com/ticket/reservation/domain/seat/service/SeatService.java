package com.ticket.reservation.domain.seat.service;

import com.ticket.reservation.global.exception.CustomException;
import com.ticket.reservation.global.exception.ErrorCode;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.room.repository.RoomRepository;
import com.ticket.reservation.domain.seat.dto.SeatDto;
import com.ticket.reservation.domain.seat.dto.SeatEditInput;
import com.ticket.reservation.domain.seat.dto.SeatInput;
import com.ticket.reservation.domain.seat.dto.SeatOutput;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.repository.SeatRepository;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

  private final SeatRepository seatRepository;
  private final RoomRepository roomRepository;
  private final ShowtimeRepository showtimeRepository;

  @Transactional
  public Seat addSeat(SeatInput seatInput) {
    Seat seat = Seat.toEntityFromInput(seatInput);
    if (isExistsSeat(seat)) {
      throw new CustomException(ErrorCode.ALREADY_EXISTS_SEAT);
    }
    boolean isExistsRoom = roomRepository.existsById(seat.getRoom().getId());
    if (!isExistsRoom) {
      throw new CustomException(ErrorCode.NOT_EXISTS_ROOM);
    }
    return seatRepository.save(seat);
  }


  public boolean isExistsSeat(Seat seat) {
    return seatRepository.existsByRowAndNumber(seat.getRow(), seat.getNumber());
  }

  @Transactional
  public void deleteSpecificSeat(Long roomId, Long seatId) {
    Room room = validateRoom(roomId);
    Seat seat = validateSeat(seatId);
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

  @Transactional
  public List<SeatOutput> searchSeatList(Long roomId) {
    Room room = validateRoom(roomId);
    List<Seat> seats = seatRepository.findSeatByRoomOrderByRow(room);
    List<SeatDto> seatDtos = SeatDto.toResponseList(seats);
    return SeatOutput.toResponseList(seatDtos);
  }

  @Transactional
  public SeatDto editSeat(Long seatId, SeatEditInput seatEditInput) {
    Seat seat = validateSeat(seatId);
    seat.updateSeat(seatEditInput);
    seatRepository.save(seat);
    return SeatDto.fromEntity(seat);
  }

  public Room validateRoom(Long roomId) {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ROOM));
    if (room.getSeats().isEmpty()) {
      throw new CustomException(ErrorCode.NOT_EXISTS_SEAT);
    }
    return room;
  }

  public Seat validateSeat(Long seatId) {
    return seatRepository.findById(seatId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_SEAT));
  }
}
