package com.korea.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotePageController {

    private final NotePageService notePageService;

    @RequestMapping("/test")
    @ResponseBody public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String main(Model model) {
        //1. DB에서 데이터 꺼내오기
        List<NotePage> notePageList = notePageService.getNotePageList();

        if(notePageList.isEmpty()) {
            notePageService.saveDefaultNotePage();
            return "redirect:/";
        }

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("notePageList", notePageList);
        model.addAttribute("targetPost", notePageList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write() {
        notePageService.saveDefaultNotePage();
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        NotePage notePage = notePageService.getNotePageById(id);

        model.addAttribute("targetPost", notePage);
        model.addAttribute("notePageList", notePageService.getNotePageList());

        return "main";
    }
    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        NotePage notePage = notePageService.getNotePageById(id);

        if(title.trim().length() == 0) {
            title = "제목 없음";
        }

        notePage.setTitle(title);
        notePage.setContent(content);

        notePageService.save(notePage);
        return "redirect:/detail/" + id;
    }

    @PostMapping("/delete")
    public String delete(Long id) {
        notePageService.deleteById(id);
        return "redirect:/";
    }

}
