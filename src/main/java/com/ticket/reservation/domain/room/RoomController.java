package com.ticket.reservation.domain.room;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
  private final RoomService roomService;

  @PostMapping("/create")
  public ResponseEntity<?> createRoom(@RequestBody Room room) {
    Room makeRoom = roomService.createRoom(room);
    return ResponseEntity.ok(makeRoom);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteRoom(@RequestBody Room room) {
    roomService.deleteRoom(room);
    return ResponseEntity.ok("Room deleted successfully");
  }
}
