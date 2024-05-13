package org.fullstack4.controller;

import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model){
        model.addAttribute("msg","Hello");

    }
    @GetMapping("/ex/ex1")
    public void ex1(Model model){
        List<String> list = Arrays.asList("AAA","BBB","CCC");
        model.addAttribute("list",list);
    }
    @GetMapping("/ex/ex2")
    public void ex2(Model model){
        List<String> strlist = IntStream.range(1,10).mapToObj(i->"Data "+i).collect(Collectors.toList());
        model.addAttribute("list",strlist);

        Map<String,String> map = new HashMap<>();
        map.put("A","aaaa");
        map.put("B","bbbb");
        map.put("C","cccc");
        model.addAttribute("map",map);

        SampleDTO dto = new SampleDTO();
        dto.p1 = "value -- p1";
        dto.p2 = "value -- p2";
        dto.p3 = "value -- p3";
        model.addAttribute("dto",dto);
    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model){
        model.addAttribute("arr", new String[]{"AAA","BBB","CCC"});
    }

    @Getter
    class SampleDTO{
        public String p1;
        public String p2;
        public String p3;

    }
}
