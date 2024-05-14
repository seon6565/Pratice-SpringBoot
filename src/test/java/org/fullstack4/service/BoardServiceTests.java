package org.fullstack4.service;


import lombok.extern.log4j.Log4j2;
import org.fullstack4.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BoardServiceTests {
    @Autowired
    private BoardServiceIf boardService;

    @Test
    public void testRegist(){
        log.info("====================");
        log.info("BoardServiceTests >> testRegist Start");

        log.info("boardService.getClass().getName(): {}", boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder().user_id("test").title("제목 테스트").content("내용 테스트").build();
        int result = boardService.regist(boardDTO);

        log.info("result : "+result);
        log.info("====================");
    }

    @Test
    public void testModify(){
        log.info("====================");
        log.info("BoardServiceTests >> testModify Start");

        log.info("boardService.getClass().getName(): {}", boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder().idx(26).user_id("test").title("수정 테스트23").content("수정 테스트23").build();
        boardService.modify(boardDTO);

        log.info("testModify boardDTO : "+boardDTO);
        log.info("====================");
    }

    @Test
    public void testView(){
        int idx = 10;
        log.info("====================");
        log.info("BoardServiceTests >> testView Start");

        log.info("boardService.getClass().getName(): {}", boardService.getClass().getName());

        BoardDTO boardDTO = boardService.view(idx);

        log.info("testView boardDTO : "+boardDTO);
        log.info("====================");
    }

    @Test
    public void testDelete(){
        int idx = 10;
        log.info("====================");
        log.info("BoardServiceTests >> testView Start");

        log.info("boardService.getClass().getName(): {}", boardService.getClass().getName());

        boardService.delete(idx);

        log.info("boardService delete ");
        log.info("====================");
    }
}
