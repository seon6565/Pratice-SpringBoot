package org.fullstack4.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.domain.BoardEntity;
import org.fullstack4.domain.QBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(BoardEntity.class);
    }

    @Override
    public Page<BoardEntity> search(Pageable pageable) {
        log.info("-------------------");
        log.info("BoardSearchImpl >> search");
        QBoardEntity qBoard = QBoardEntity.boardEntity;
        JPQLQuery<BoardEntity> query = from(qBoard);
        //query.where(qBoard.title.contains("제목"));
        //query.where(qBoard.content.like("제목"));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(qBoard.title.contains("제목"));
        booleanBuilder.or(qBoard.content.contains("제목"));
        query.where(booleanBuilder);

        this.getQuerydsl().applyPagination(pageable,query);
        log.info("query : {}", query);
        List<BoardEntity> boards = query.fetch();

        int total = (int)query.fetchCount();
        log.info("board : "+boards);
        log.info("total : "+total);

        return new PageImpl<>(boards,pageable,total);
    }

    @Override
    public Page<BoardEntity> search2(Pageable pageable, String[] types, String search_keyword) {
        QBoardEntity qBoard = QBoardEntity.boardEntity;
        JPQLQuery<BoardEntity> query = from(qBoard);
        if(types !=null && types.length > 0 && search_keyword != null && search_keyword.length()>0){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for(String type : types){
                switch (type) {
                    case "t":
                        booleanBuilder.or(qBoard.title.contains(search_keyword));
                        break;

                    case "c":
                        booleanBuilder.or(qBoard.content.contains(search_keyword));
                        break;

                    case "w":
                        booleanBuilder.or(qBoard.user_id.contains(search_keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        this.getQuerydsl().applyPagination(pageable,query);
        log.info("keyword query : {}", query);
        List<BoardEntity> boards = query.fetch();
        int total = (int)query.fetchCount();
        log.info("keyword board : "+boards);
        log.info("keyword total : "+total);
        return new PageImpl<>(boards,pageable,total);
    }
}
