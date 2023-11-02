package com.ticket.reservation.domain.room;

import com.ticket.reservation.domain.theater.Theater;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "ROOM")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    @Column(name = "NAME")
    private String name;

    public Long getTheaterId() {
        return theater.getId();
    }

    public void addRoom(Theater theater) {
        this.theater = theater;
    }

    public void removeRoom(Theater theater) {
        this.theater = theater;
    }
}
