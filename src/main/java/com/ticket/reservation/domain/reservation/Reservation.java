//package com.ticket.reservation.domain.reservation;
//
//import com.ticket.reservation.domain.member.Member;
//import com.ticket.reservation.domain.movie.entity.Movie;
//import com.ticket.reservation.domain.showtime.entity.Showtime;
//
//import javax.persistence.*;
//
//@Entity
//public class Reservation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    private Member member;
//
//    @ManyToOne
//    private Showtime showtime;
//
//    @ManyToOne
//    private Movie movie;
//}
