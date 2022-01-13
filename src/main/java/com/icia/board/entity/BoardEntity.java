package com.icia.board.entity;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(length = 50)
    private String boardWriter;

    @Column(length = 20)
    private String boardPassword;

    @Column(length = 50)
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

//    @Column(length = 50)
//    private LocalDateTime boardDate;


    // MemberSaveDTO -> MemberEntity 객체로 변환하기 위한 메서드
    public static BoardEntity toSaveEntity(BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = new BoardEntity();

//          두줄을 밑에 한줄로 표현이 가능하다다
//       String memberEmail = memberSaveDTO.getMemberEmail();
//        memberEntity.setMemberEmail();

        boardEntity.setBoardWriter(boardSaveDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardSaveDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
//        boardEntity.setBoardDate(LocalDateTime.now());
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardUpdateDTO boardDetailDTO) {
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setId(boardDetailDTO.getBoardId());
        boardEntity.setBoardWriter(boardDetailDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardDetailDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardDetailDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDetailDTO.getBoardContents());
//        boardEntity.setBoardDate(LocalDateTime.now());
        return boardEntity;
    }

    public static BoardEntity updateBoard(BoardUpdateDTO boardUpdateDTO) {

        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setId(boardUpdateDTO.getBoardId());
        boardEntity.setBoardWriter(boardUpdateDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardUpdateDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
//        boardEntity.setBoardDate(LocalDateTime.now());
        return boardEntity;
    }
}
