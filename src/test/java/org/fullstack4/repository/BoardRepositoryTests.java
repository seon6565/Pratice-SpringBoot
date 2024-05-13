package org.fullstack4.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.domain.BaseEntity;
import org.fullstack4.domain.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testGetNow(){
        String now = boardRepository.getnow();
        log.info("==================");
        log.info("now : "+now);
        log.info("==================");

    }
    @Test
    public void  testRegist(){
        log.info("testRegist : ");
        IntStream.rangeClosed(0,10).forEach(i->{
            BoardEntity bbs = BoardEntity.builder()
                    .user_id("test")
                    .title("테스트 제목 "+i)
                    .content("테스트 본문 "+i)
                    .display_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(2024-1900,4,(i%10==0?1:i%10))).toString())
                    .build();
            BoardEntity bbsResult = boardRepository.save(bbs);
            log.info("testRegist Result : "+ bbsResult);
        });
    }

    @Test
    public void testView(){
        int idx= 15;

        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(null);
//        if(result.isPresent()){
//            result.get();
//        }
//        else{
//            throw new IllegalArgumentException();
//        }
//        result.orElseThrow(IllegalArgumentException::new);
//        result.orElseThrow(()->new IllegalArgumentException("no find data"));
//        result.orElseGet(BoardEntity::new);
//        result.ifPresent(b->{log.info("result:{}",b);});
        log.info("testView Result : "+ result);
    }

    @Test
    public void testModify(){
        int idx = 15;
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity bbs = result.orElse(null);
//        if(bbs!=null) {
//            bbs = BoardEntity.builder().idx(idx).user_id("test").title("제목수정1").content("내용수정1")
//                    .display_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()).build();
//            boardRepository.save(bbs);
//        }
        bbs.modify("test","제목수정2","내용수정2",new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
        boardRepository.save(bbs);
        log.info("testModify Result : "+ bbs);
    }

    @Test
    public void testDelete(){
        int idx = 16;
        boardRepository.deleteById(idx);
    }
    @Test
    public void testSearch(){
        PageRequest pageable = PageRequest.of(0,30, Sort.by("idx").descending());
        Page<BoardEntity> result = boardRepository.search(pageable);

        log.info("result = "+ result.toString());
        log.info("getSize = "+ result.getSize());
        log.info("getNumber = "+ result.getNumber());
        result.getContent().forEach(board -> log.info(board));
        log.info("hasNext = "+ result.hasNext());
        log.info("hasPrevious = "+ result.hasPrevious());


    }
    @Test
    public void testSearch2(){
        PageRequest pageable = PageRequest.of(0,10, Sort.by("idx").descending());
        String[] types = {"t","c","w"};
        String search_keyword = "제목 1";
        boardRepository.search2(pageable,types,search_keyword);

    }
}
