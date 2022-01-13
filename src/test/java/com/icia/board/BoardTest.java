package com.icia.board;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.service.BoardService;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardService bs;

    @Test
    @DisplayName("게시판 데이터 생성")
    public void newBoard(){
        //IntStream을 이용하여 새글 30개 DB에 저장장
        IntStream.rangeClosed(1, 30). forEach(i -> {
            bs.save(new BoardSaveDTO("writer"+i,"pw"+i,"title"+i,"contents"+i));
        });
   }

    @Test
    @DisplayName("게시판 조회 테스트")
    public void findById(){
        /*
        글을하나쓰고 글이써진지 확인한다.
         */
        final String testWriter = "글쓴이1";
        final String testPassword = "글비번1";
        final String testTitle = "글제목1";
        final String testContents = "글내용1";

        BoardSaveDTO boardSaveDTO = new BoardSaveDTO(testWriter,testPassword,testTitle,testContents);
        Long SaveResult= bs.save(boardSaveDTO);
        assertThat(SaveResult).isEqualTo(true);



    }

}
