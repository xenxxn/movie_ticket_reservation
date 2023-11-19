package com.ticket.reservation.domain.room.service;

import com.ticket.reservation.domain.exception.CustomException;
import com.ticket.reservation.domain.exception.ErrorCode;
import com.ticket.reservation.domain.room.dto.RoomDto;
import com.ticket.reservation.domain.room.dto.RoomEditInput;
import com.ticket.reservation.domain.room.dto.RoomInput;
import com.ticket.reservation.domain.room.dto.RoomOutput;
import com.ticket.reservation.domain.room.entity.Room;
import com.ticket.reservation.domain.room.repository.RoomRepository;
import com.ticket.reservation.domain.seat.entity.Seat;
import com.ticket.reservation.domain.seat.repository.SeatRepository;
import com.ticket.reservation.domain.showtime.entity.Showtime;
import com.ticket.reservation.domain.showtime.repository.ShowtimeRepository;
import com.ticket.reservation.domain.theater.entity.Theater;
import com.ticket.reservation.domain.theater.repository.TheaterRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final TheaterRepository theaterRepository;
  private final ShowtimeRepository showtimeRepository;
  private final SeatRepository seatRepository;

  @Transactional
  public Room addRoom(RoomInput roomInput) {
    Room room = Room.toEntityFromInput(roomInput);
    Theater theater = validateTheater(roomInput.getTheaterId());
    if (theater == null) {
      throw new CustomException(ErrorCode.NOT_EXISTS_THEATER);
    }
    return roomRepository.save(room);
  }

  @Transactional
  public void deleteSpecificRoom(Long theaterId, Long roomId) {
    Theater theater = validateTheater(theaterId);
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ROOM));
    theater.getRooms().remove(room);
    theaterRepository.save(theater);
  }

  @Transactional
  public void deleteAllRooms(Long theaterId) {
    Theater theater = validateTheater(theaterId);
    List<Room> rooms = roomRepository.findRoomByTheaterId(theaterId);

    for (Room room : rooms) {
      room.setRoom(null);
      roomRepository.save(room);
    }

    theater.getRooms().clear();
    theaterRepository.save(theater);
  }


  @Transactional
  public RoomDto editRoom(RoomEditInput roomEditInput) {
    Room room = Room.toEntityFromEditInput(roomEditInput);
    Theater theater = validateTheater(room.getTheater().getId());
    if (theater == null) {
      throw new CustomException(ErrorCode.NOT_EXISTS_THEATER);
    }
    List<Showtime> showtimeList = searchShowtimeList(room.getId());
    for (Showtime showtime : showtimeList) {
      room.getShowtimeList().clear();
      showtime.setRoom(room);
      showtimeRepository.save(showtime);
    }
    List<Seat> seats = searchSeats(room.getId());
    for (Seat seat : seats) {
      room.getSeats().clear();
      seat.setRoom(room);
      seatRepository.save(seat);
    }

    roomRepository.save(room);
    return RoomDto.fromEntity(room);
  }

  public List<RoomOutput> searchRoomList(Long theaterId) {
    validateTheater(theaterId);
    List<Room> rooms = roomRepository.findRoomByTheaterId(theaterId);
    List<RoomDto> roomDtos = RoomDto.toResponseList(rooms);
    return RoomOutput.toResponseList(roomDtos);
  }

  public Theater validateTheater(Long theaterId) {
    return theaterRepository.findById(theaterId)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_THEATER));
  }

  public List<Showtime> searchShowtimeList(Long roomId) {
    return showtimeRepository.findShowtimeByRoomId(roomId);
  }

  public List<Seat> searchSeats(Long roomId) {
    return seatRepository.findAllByRoomId(roomId);
  }

}
