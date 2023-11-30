package com.korea.test.Service;

import com.korea.test.Entity.Notebook;
import com.korea.test.Repository.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotebookService {
     private final NotebookRepository notebookRepository;

    public List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }

    public Notebook getNotebookById(Long id) {
        Optional<Notebook> notebookOptional = notebookRepository.findById(id);
        if(notebookOptional.isPresent()) {
            return notebookOptional.get();
        }

        throw new IllegalArgumentException("해당 노트북은 존재하지 않습니다.");
    }

    public Notebook saveDefaultNotebook() {
        Notebook notebook = new Notebook();
        notebook.setName("새노트");
        notebook.setCreateDate(LocalDateTime.now());

        return notebookRepository.save(notebook);
    }
    
    public void deleteById(Long id) {
        notebookRepository.deleteById(id);
    }
    
    public void updateName(Long id, String name) {
        Notebook notebook = getNotebookById(id);
        notebook.setName(name);
        notebookRepository.save(notebook);
    }
}
