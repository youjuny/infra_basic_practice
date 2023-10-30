package com.korea.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notebook")
@RequiredArgsConstructor
public class NotebookController {

    private final NotePageService notePageService;
    private final NotebookService notebookService;

    @RequestMapping("/")
    public String main(Model model) {

        List<NotePage> notePageList = notePageService.getNotePageList();
        List<Notebook> notebookList = notebookService.getNotebookList();

        if(notePageList.isEmpty()) {
            notePageService.saveDefaultNotePage();
            return "redirect:/";
        }

        model.addAttribute("notebookList", notebookList);
        model.addAttribute("notePageList", notePageList);
        model.addAttribute("targetPost", notePageList.get(0));
        model.addAttribute("targetNotebook", notebookList.get(0));

        return "main";
    }
}
