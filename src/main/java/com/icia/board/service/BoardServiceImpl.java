package com.icia.board.service;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository br;

    // 글쓰기
    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        // JpaRepository는 무조건 Entity만 받음.

        // BoardSaveDTO -> BoardEntity 로 변환
        // boardSaveDTO를 전달하고 받는다
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardSaveDTO);
//        두줄을 밑에 리턴한줄로 가능
//        Long boardId = mr.save(boardEntity).getId();
//        return boardId;
        System.out.println("BoardServiceImpl.save");
        return br.save(boardEntity).getId();
    }

    // 글목록
    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll();
        List<BoardDetailDTO> boardList = new ArrayList<>();
        for (BoardEntity b: boardEntityList){
            boardList.add(BoardDetailDTO.toBoardDetailDTO(b));
        }
        return boardList;
    }

    // 게시글 조회
    @Override
    public BoardDetailDTO findById(Long boardId) {
         /*
            1. MemberRepository로 부터 해당 회원의 정보를 MemberEntity로 가져옴.
            2. MemberEntity를 MemberDetailDTO로 바꿔서 컨트롤러로 리턴.
         */
        // 1.
        // Optional = null 방지
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);

        /*
            Optional 객체 메서드
            - isPresnet(): 데이터가 있으면 true, 없으면 false 반환
            - isEmpty(); 데이터가 없으면 true, 있으면 false 반환
            - get(): Optional 객체에 들어있는 실제 데이터를 가져올때
         */

        BoardDetailDTO boardDetailDTO=null;
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        }
//        // .get() 은 옵셔널안에 Entity를 꺼냄
//        BoardEntity boardEntity = optionalBoardEntity.get();
//        // 2.
//        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        return boardDetailDTO;
        // 위의 4줄을 밑에 한줄로도 가능하다.
        //return MemberDetailDTO.toMemberDetailDTO(mr.findById(memberId).get());
    }

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardUpdateDTO);
        return br.save(boardEntity).getId();
    }

//    @Override
//    public void update(BoardUpdateDTO boardUpdateDTO) {
//        BoardEntity boardEntity = BoardEntity.updateBoard(boardUpdateDTO);
//        br.save(boardEntity);
//    }

//
//    @Override
//    public void update(BoardUpdateDTO boardUpdateDTO) {
//        BoardEntity boardEntity = BoardEntity.updateBoard(boardUpdateDTO);
//        br.save(boardEntity);
//    }


}
