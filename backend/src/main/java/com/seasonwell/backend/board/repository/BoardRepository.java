package com.seasonwell.backend.board.repository;

import com.seasonwell.backend.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();

    List<Board> findAllByBoardType(String boardType);

    List<Board> findAllByBoardTypeOrderByBoardNoDesc(String boardType);

    Board findByBoardTypeAndBoardNo(String boardType, Long boardNo);

    @Query("SELECT m FROM Board m WHERE lower(m.boardTitle) LIKE lower(concat('%', :boardTitle, '%'))")
    List<Board> findByBoardTitle(
            @Param("boardTitle") String board_title
    );

    @Query("SELECT m FROM Board m WHERE lower(m.boardContent) LIKE lower(concat('%', :boardContent, '%'))")
    List<Board> findByBoardContent(
            @Param("boardContent") String board_content
    );

    @Query("SELECT m FROM Board m WHERE lower(m.boardAuthor) LIKE lower(concat('%', :boardAuthor, '%'))")
    List<Board> findByBoardAuthor(
            @Param("boardAuthor") String board_author
    );
}