package com.ticket.reservation.domain.movie;

import com.ticket.reservation.domain.movie.dto.MovieDto;
import com.ticket.reservation.domain.movie.dto.MovieEditInput;
import com.ticket.reservation.domain.movie.dto.MovieInput;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional
    public MovieDto addMovie(MovieInput movieInput) {
        Movie movie = MovieInput.toEntity(movieInput);
        movie = movieRepository.save(movie);
        return MovieDto.fromEntity(movie);
    }

    public Movie searchMovie(String title) {
        return movieRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("검색 결과가 없습니다."));
    }

    public List<Movie> searchMovieList(String title) {
        List<Movie> searchResults = movieRepository.findByTitleContaining(title);
        if (searchResults.isEmpty()) {
            throw new RuntimeException("검색 결과가 없습니다2.");
        }
        return searchResults;
    }

    @Transactional
    public MovieDto editMovie(MovieEditInput movieEditInput) {
        Movie movie = movieRepository.findById(movieEditInput.getId())
            .orElseThrow(() -> new RuntimeException("해당 영화는 존재하지 않습니다."));

        Movie editMovie = Movie.builder()
            .id(movie.getId())
            .title(movieEditInput.getTitle())
            .country(movieEditInput.getCountry())
            .genre(movieEditInput.getGenre())
            .information(movieEditInput.getInformation())
            .grade(movieEditInput.getGrade())
            .runningTime(movieEditInput.getRunningTime())
            .releaseDate(movieEditInput.getReleaseDate())
            .endDate(movieEditInput.getEndDate())
            .build();
        Movie saved = movieRepository.save(editMovie);
        return MovieDto.fromEntity(saved);
    }
}
