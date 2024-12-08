package com.ryu.toolkit_for_everything.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ryu.toolkit_for_everything.dto.documentDTO.DocumentsListDTO;
import com.ryu.toolkit_for_everything.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select new com.ryu.toolkit_for_everything.dto.documentDTO.DocumentsListDTO(d.id, d.name) from Document d where d.user.id = ?1")
    List<DocumentsListDTO> findByUserId(Long userId);
}
