package org.fullstack4.service;

import org.fullstack4.dto.BoardDTO;

public interface BoardServiceIf {
    int regist(BoardDTO boardDTO);
    BoardDTO view(int idx);
    void modify(BoardDTO boardDTO);
    void delete(BoardDTO boardDTO);

}
