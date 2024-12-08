package com.ryu.toolkit_for_everything.controller;

import org.springframework.web.bind.annotation.RestController;
import com.ryu.toolkit_for_everything.dto.documentDTO.DocumentDTO;
import com.ryu.toolkit_for_everything.entity.Document;
import com.ryu.toolkit_for_everything.services.documentServices.DocumentServices;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin
@Log4j2
public class DocumentController {
    private final DocumentServices documentServices;

    @Autowired
    public DocumentController(DocumentServices documentServices) {
        this.documentServices = documentServices;
    }

    @PostMapping("/document/create")
    public ResponseEntity<Object> createDocument(
            @RequestBody(required = true) DocumentDTO documentDTO,
            @RequestParam(required = true) Long userId) {

        try {
            documentServices.createOrUpdateDocument(documentDTO, userId);
            return ResponseEntity.ok(documentServices.getDocumentsList(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wrong happened");
        }
    }

    @PutMapping("/document/update")
    public ResponseEntity<Object> updateDocument(
            @RequestBody(required = true) DocumentDTO documentDTO,
            @RequestParam(required = true) Long userId) {

        try {
            return ResponseEntity.ok(documentServices.createOrUpdateDocument(documentDTO, userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something wrong happened");
        }
    }

    @GetMapping("/documentsList/get")
    public ResponseEntity<Object> getAllDocuments(@RequestParam(required = true) Long userId) {
        try {
            return ResponseEntity.ok(documentServices.getDocumentsList(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/document/get")
    public ResponseEntity<Object> getDocument(@RequestParam(required = true) Long documentId,
            @RequestParam(required = true) Long userId) {
        try {
            return ResponseEntity.ok(documentServices.getDocument(documentId, userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
