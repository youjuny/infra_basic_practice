package com.korea.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
     private final NotebookRepository notebookRepository;

    public List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }
}
