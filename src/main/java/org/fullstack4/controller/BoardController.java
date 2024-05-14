package org.fullstack4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.dto.BoardDTO;
import org.fullstack4.dto.PageRequestDTO;
import org.fullstack4.dto.PageResponseDTO;
import org.fullstack4.service.BoardServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardServiceIf boardServiceIf;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> pageResponseDTO = boardServiceIf.list(pageRequestDTO);

        log.info("pageResponseDTO test : " + pageResponseDTO);
        model.addAttribute("pageResponseDTO" , pageResponseDTO);
    }
    @GetMapping("/view")
    public void view(int idx, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardServiceIf.view(idx);
        log.info("pageRequestDTO view test : " +pageRequestDTO);
        model.addAttribute("dto",boardDTO);
    }

}
