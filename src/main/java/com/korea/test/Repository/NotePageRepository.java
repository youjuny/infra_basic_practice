package com.korea.test.Repository;

import com.korea.test.Entity.NotePage;
import com.korea.test.Entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotePageRepository extends JpaRepository<NotePage, Long>  {
    List<NotePage> findByNotebook(Notebook targetNotebook);
}
