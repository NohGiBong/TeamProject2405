package com.busanit.service;

import com.busanit.domain.CommentDTO;
import com.busanit.entity.Member;
import com.busanit.entity.movie.Comment;
import com.busanit.domain.CommentSummaryDTO;
import com.busanit.entity.movie.Movie;
import com.busanit.repository.CommentRepository;
import com.busanit.repository.MemberRepository;
import com.busanit.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void register(CommentDTO commentDTO) {
        Movie movie = movieRepository.findById(commentDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 movieId: " + commentDTO.getMovieId()));
        Member member = memberRepository.findByEmail(commentDTO.getMemberEmail())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 memberId: " + commentDTO.getMemberEmail()));

        Comment comment = Comment.dtoToEntity(commentDTO);
        member.addComment(comment);
        movie.addComment(comment);

        commentRepository.save(comment);
    }

    public List<CommentDTO> getCommentList(String movieId) {
        List<Comment> commentList = commentRepository.findByMovieIdOrderByCnoDesc(Long.valueOf(movieId));

        return CommentDTO.toDTOList(commentList);
    }

//    public CommentSummary getCommentsAndAverageGrade(String movieId) {
//        CommentSummary gpa = commentRepository.findCommentsAndAvgGrade(Long.valueOf(movieId));
//        List<Comment> commentList = commentRepository.findByMovieIdOrderByCnoDesc(Long.valueOf(movieId));
//        List<CommentDTO> dtoList = CommentDTO.toDTOList(commentList);
//
//    }
//public CommentSummaryDTO getCommentsAndAverageGrade(String movieId) {
//    List<Object[]> results = commentRepository.findCommentsAndAvgGradeByMovieId(Long.valueOf(movieId));
//
//    List<CommentDTO> commentDtos = new ArrayList<>();
//    Double avgGrade = null;
//
//    for (Object[] result : results) {
//        Long id = ((Number) result[0]).longValue();
//        String content = (String) result[1];
//        String author = (String) result[2];
//        if (avgGrade == null) {
//            avgGrade = ((Number) result[3]).doubleValue(); // 첫 번째 레코드에서 평균 점수를 가져옵니다.
//        }
//
//        commentDtos.add(CommentDTO.builder()
//                .cno(id)
//                .comment(content)
//                .memberEmail(author)
//                .build());
//    }
//
//    return new CommentSummaryDTO(Long.valueOf(movieId), avgGrade, commentDtos);
//}



}
