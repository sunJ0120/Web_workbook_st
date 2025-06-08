package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(){
        log.info("todo list.....");
    }

    @GetMapping(value="/register")
    public void register(){
        log.info("GET todo register.......");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO dto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        log.info("POST todo register.......");

        // 유효성 검사 결과가 있으면 에러 메시지를 리다이렉트 속성에 추가하고 등록 페이지로 리다이렉트 한다.
        if(bindingResult.hasErrors()){
            log.info("has errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info("dto = {}", dto);

        todoService.register(dto);

        return "redirect:/todo/list"; // 리다이렉트하여 list로 이동
    }
}
