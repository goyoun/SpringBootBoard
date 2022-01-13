package com.icia.board.controller;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.icia.board.common.SessionConst.BOARD_WRITER;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService bs;

    //게시판 글쓰기 요청
    @GetMapping("/save")
    public String saveForm() {
        return "board/save";
    }

    //게시판 글쓰기처리
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO){
//        타임리프를 사용안한다면 @ModelAttribute 제외가능
        Long boardId = bs.save(boardSaveDTO);
        return "redirect:/board/";
    }

    // 글목록
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDetailDTO> boardList = bs.findAll();
        model.addAttribute("boardList", boardList);
        return "board/findAll";
    }

    // 상세조회
    // (/member/5)
    // @PathVariable: 경로상에 있는 변수를 가져올 때 사용
    @GetMapping("{boardId}")
    public String findById(@PathVariable Long boardId, Model model) {
        log.info("글보기 메서드 호출. 요청글번호: {}", boardId);
        // 리턴 받아오기
        BoardDetailDTO board = bs.findById(boardId);
        // 모델에 담아서 넘기기
        model.addAttribute("board",board);
        return"board/findById";
    }

    // 상세조회 ajax
    @PostMapping("{boardId}")
    public ResponseEntity findById2(@PathVariable Long boardId) {
        BoardDetailDTO board = bs.findById(boardId);
//                                  <타입을여기에><-(이객채의)
        return new ResponseEntity<BoardDetailDTO>(board, HttpStatus.OK);
    }

    // 업데이트
    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model){
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board", board);
        return "/board/update";
    }



    // 업데이트 처리
//    @PostMapping("/update")
//    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO) {
//        System.out.println(boardUpdateDTO);
//        System.out.println(boardUpdateDTO.getBoardId());
//        System.out.println(boardUpdateDTO.getBoardDate());
//        BoardDetailDTO boardDetailDTO = bs.findById(boardUpdateDTO.getBoardId());
//        if (boardDetailDTO.getBoardPassword().equals(boardUpdateDTO.getBoardPassword())) {
//            bs.update(boardUpdateDTO);
//            return "redirect:/board/"+boardUpdateDTO.getBoardId();
//        } else {
//            return "redirect:/board/";
//        }
//    }

    // 업데이트처리
    @PostMapping("/update")
    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO) {
        bs.update(boardUpdateDTO);
        return "redirect:/board/" + boardUpdateDTO.getBoardId();
    }

    // 업데이트처리 put
    @PutMapping("/{boardId}")
    public ResponseEntity update2(@RequestBody BoardUpdateDTO boardUpdateDTO) {

        bs.update(boardUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
//        BoardDetailDTO boardDetailDTO = bs.findById(boardUpdateDTO.getBoardId());
//        if (boardDetailDTO.getBoardPassword().equals(boardUpdateDTO.getBoardPassword())) {
//            bs.update(boardUpdateDTO);
//            return new ResponseEntity(HttpStatus.OK);
//        } else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }

    // 삭제처리


}
