package com.korea.test.Controller;

import com.korea.test.Entity.NotePage;
import com.korea.test.Entity.Notebook;
import com.korea.test.Service.NotePageService;
import com.korea.test.Service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notebook")
@RequiredArgsConstructor
public class NotebookController {

    private final NotePageService notePageService;
    private final NotebookService notebookService;

    @RequestMapping("/{notebookId}")
    public String select(@PathVariable("notebookId") Long notebookId, Model model) {

        Notebook notebook = notebookService.getNotebookById(notebookId);
        List<Notebook> notebookList = notebookService.getNotebookList();

        model.addAttribute("notebookList", notebookList);
        model.addAttribute("notePageList", notebook.getNotePageList());
        model.addAttribute("targetPost", notebook.getNotePageList().get(0));
        model.addAttribute("targetNotebook", notebook);

        return "main";

    }
    @RequestMapping("/")
    public String main(Model model) {

        List<NotePage> notePageList = notePageService.getNotePageList();
        List<Notebook> notebookList = notebookService.getNotebookList();
        if(notebookList.isEmpty()) {
            return "redirect:/";
        }

        if(notePageList.isEmpty()) {
            notePageService.saveDefaultNotePage(notebookList.get(0));
            return "redirect:/";
        }

        model.addAttribute("notebookList", notebookList);
        model.addAttribute("notePageList", notePageList);
        model.addAttribute("targetPost", notePageList.get(0));
        model.addAttribute("targetNotebook", notebookList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write() {
        Notebook notebook = notebookService.saveDefaultNotebook();
        notePageService.saveDefaultNotePage(notebook);
        return "redirect:/";
    }
    
    @PostMapping("/delete")
    public String delete(Long id) {
        
        notebookService.deleteById(id);
        
        return "redirect:/";
    }
    
    
    @PostMapping("/update")
    public String update(Long id, String name) {
        
        Notebook notebook = notebookService.getNotebookById(id);
        
        if(name.trim().length() == 0) {
            name = "노트 제목 없음";
        }
        
        notebook.setName(name);
        
        notebookService.updateName(id, name);
        
        return "redirect:/";
    }
    
}
