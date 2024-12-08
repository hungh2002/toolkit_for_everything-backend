package com.ryu.toolkit_for_everything.services.documentServices;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ryu.toolkit_for_everything.dto.documentDTO.DocumentDTO;
import com.ryu.toolkit_for_everything.dto.documentDTO.DocumentsListDTO;
import com.ryu.toolkit_for_everything.entity.Document;
import com.ryu.toolkit_for_everything.entity.User;
import com.ryu.toolkit_for_everything.repository.DocumentRepository;
import com.ryu.toolkit_for_everything.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DocumentServicesHandler implements DocumentServices {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    @Autowired
    public DocumentServicesHandler(DocumentRepository documentRepository,
            UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public DocumentDTO createOrUpdateDocument(DocumentDTO documentDTO, Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent())
            throw new EntityNotFoundException("User not exist");

        Document document = new Document();

        // When update existing document
        if (documentDTO.getId() != null) {
            Optional<Document> documentOptional = documentRepository.findById(documentDTO.getId());

            if (!documentOptional.isPresent()) {
                throw new EntityNotFoundException("Document not exist");
            }

            document.setId(documentDTO.getId());
        }
        //

        document.setName(documentDTO.getName());
        document.setContent(documentDTO.getContent());
        document.setUser(userOptional.get());

        if (documentDTO.getId() == null) {
            documentDTO.setId(documentRepository.save(document).getId());
        } else {
            documentRepository.save(document);
        }


        return documentDTO;
    }

    public List<DocumentsListDTO> getDocumentsList(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("User not exist");
        }

        return documentRepository.findByUserId(userId);
    }

    public DocumentDTO getDocument(Long documentId, Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("User not exist");
        }

        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (!documentOptional.isPresent()) {
            throw new EntityNotFoundException("Document not exist");
        }

        Document document = documentOptional.get();
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(document.getId());
        documentDTO.setName(document.getName());
        documentDTO.setContent(document.getContent());
        return documentDTO;
    };
}
