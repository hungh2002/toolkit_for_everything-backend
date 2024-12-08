package com.ryu.toolkit_for_everything.services.documentServices;

import java.util.List;
import com.ryu.toolkit_for_everything.dto.documentDTO.DocumentDTO;
import com.ryu.toolkit_for_everything.dto.documentDTO.DocumentsListDTO;

public interface DocumentServices {
    public DocumentDTO createOrUpdateDocument(DocumentDTO documentDTO, Long userId);

    public List<DocumentsListDTO> getDocumentsList(Long userId);

    public DocumentDTO getDocument(Long documentId, Long userId);
}
