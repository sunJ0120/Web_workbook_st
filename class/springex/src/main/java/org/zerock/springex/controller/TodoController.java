package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;

/*
여기 controller 안 되는 것 해결해야 한다.
 */
@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    //왜 여기를 RequestMapping으로 했을까?..음 이해 안된다......
    //이거 만약에 그냥 Get이면 바꿔도 될 것 같다.
    @RequestMapping("/list")
    public void list(Model model){
        log.info("todo list.....");
        // 서비스에서 모든 TodoDTO를 가져와서 모델에 추가한다. 이 모델을 JSP에서 뷰로 이용할 수 있는 것이다.
        model.addAttribute("dtoList", todoService.getAll());
    }

    @GetMapping(value="/register")
    public void register(){
        log.info("GET todo register.......");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO dto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("POST todo register.......");

        // 유효성 검사 결과가 있으면 에러 메시지를 리다이렉트 속성에 추가하고 등록 페이지로 리다이렉트 한다.
        if (bindingResult.hasErrors()) {
            log.info("has errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info("dto = {}", dto);

        todoService.register(dto);

        return "redirect:/todo/list"; // 리다이렉트하여 list로 이동
    }

    // read와 modify가 같은 Get method로 들어가도록 한다.
    @GetMapping({"/read", "/modify"})
    public void read(Long tno, Model model){
        //controller에서는 service를 호출한다. 그리고 하나만 조회하는 것이므로 tno를 가져와서 조회한다.
        TodoDTO dto = todoService.getOne(tno);
        //가져온 dto를 한 번 log로 출력해보자.
        log.info("dto = {}", dto);

        // Controller에서 model에 dto를 추가하고, jsp에서 이를 이용할 수 있도록 한다.
        model.addAttribute("dto", dto);
    }

    // [2025-06-10] 지금 remove가 안되고 있는 상황인데, 뭐가 문젠지 알아야 한다.
    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){
        log.info("--------------remove--------------");
        //우선 tno가 제대로 들어오는지 로그를 찍어본다.
        log.info("tno : {}", tno);

        //service의 delete를 불러온다
        todoService.remove(tno);

        return "redirect:/todo/list"; // 삭제 후에는 목록으로 리다이렉트한다.
    }

    //Get은 받았으니까 post 방식으로 동작하는 modify method를 구현한다.
    @PostMapping("/modify")
    public String modify(@Valid TodoDTO dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        //bindingResult는 유효성 검사 결과를 담고 있다. 이게 에러일 경우를 체크하는 것이다.
        if(bindingResult.hasErrors()){
            log.info("has errors......"); //에러 체크를 위한 log이다.
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            //tno를 담아주는건...이걸 화면에서 쓰기 때문인가? 모르겠다....
            redirectAttributes.addAttribute("tno", dto.getTno());
            return "redirect:/todo/modify"; // 에러가 있을 경우 수정 페이지로 다시 리다이렉트한다.
        }

        log.info("dto = {}", dto);
        todoService.modify(dto); //service의 modify로 이동한다.

        return "redirect:/todo/list"; // 수정 후에는 목록으로 리다이렉트한다.
    }
}
