package com.ticket.reservation.domain.seat;

import com.ticket.reservation.domain.room.Room;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEAT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Column(name = "ROW")
    private String row;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "RESERVED")
    @Enumerated(EnumType.STRING)
    private SeatStatus status; //RESERVED, UNRESERVED
}
