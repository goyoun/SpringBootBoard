package com.icia.board.dto;

import com.icia.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {
    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardDate;

    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity) {
        // 객체생성
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        // 옮겨담기
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardDate(boardEntity.getCreateTime());
        // 리턴
        return boardDetailDTO;
    }

    public static List<BoardDetailDTO> change(List<BoardEntity> boardEntityList) {
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for (BoardEntity m: boardEntityList) {
            boardDetailDTOList.add(toBoardDetailDTO(m));
        }
        return boardDetailDTOList;
    }
}
