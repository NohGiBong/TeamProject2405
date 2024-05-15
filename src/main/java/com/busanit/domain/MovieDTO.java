package com.busanit.domain;

import com.busanit.entity.movie.Genre;
import com.busanit.entity.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

    private Long id;
    private String title;
    private String poster;
    private String overview;
    private String runtime;
    @JsonProperty("release_date")
    private String releaseDate;
    private String certifications;
    private String stillCut;
    private String video;
    private String posterPath;
    private String backdropPath;
    private String popularity;
    @JsonProperty("vote_average")
    private double voteAverage;
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;
    private List<String> Genres;


    public static MovieDTO convertToDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();

        // 고유번호, 제목, 상세보기
        movieDTO.setId(movie.getMovieId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setOverview(movie.getOverview());

        // 이미지의 배경 및 포스터 경로
        movieDTO.setBackdropPath(movie.getImages().getFirst().getBackdropPath());
        movieDTO.setPosterPath(movie.getImages().getFirst().getPosterPath());

        // Optional을 사용하여 MovieDetail이 null인 경우를 처리
        //비디오, 인기 , 평점
        Optional.ofNullable(movie.getMovieDetail()).ifPresent(detail -> {
            movieDTO.setPopularity(detail.getPopularity());
            movieDTO.setVoteAverage(detail.getVoteAverage());
            movieDTO.setVideo(detail.getVideo());
        });

        // MovieDetail이 null인 경우의 대체값 설정
        if (movie.getMovieDetail() == null) {
            movieDTO.setVoteAverage(0);
            movieDTO.setVideo(null);
        }

        // 장르 이름 추출 및 설정
        List<String> genreNames = movie.getGenres().stream()
                .map(Genre::getGenreName) // Genre 객체에서 이름 추출
                .collect(Collectors.toList());
        movieDTO.setGenres(genreNames);

        return movieDTO;
    }


//    public static MovieDTO convertToDTO(Movie movie){
//        return MovieDTO.builder()
//                .id(movie.getMovieId())
//                .title(movie.getTitle())
//                .overview(movie.getOverview())
//                .build();
//    }

}