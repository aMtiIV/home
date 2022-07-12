package com.mateusz.home.controller;

import com.mateusz.home.command.CreateInmateCommand;
import com.mateusz.home.dto.InmateDto;
import com.mateusz.home.service.InmateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/inmates")
@RequiredArgsConstructor
public class InmateController {
    private final InmateService inmateService;

    @PostMapping
    public ResponseEntity<InmateDto> addInmate(@RequestBody CreateInmateCommand newInmate) {
        final InmateDto createdInmate = inmateService.createInmate(newInmate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInmate);
    }

    @GetMapping
    public ResponseEntity<List<InmateDto>> getInmates() {
        return ResponseEntity.ok(inmateService.getInmates());
    }

    @GetMapping("/{inmateId}")
    public ResponseEntity<InmateDto> findInmateById(@PathVariable(value = "inmateId") long id) {
        return ResponseEntity.ok(inmateService.getInmateById(id));
    }

    @DeleteMapping("/{inmateId}")
    public ResponseEntity<InmateDto> deleteInmate(@PathVariable(value = "inmateId") long id) {
        return ResponseEntity.ok(inmateService.deleteInmate(id));
    }

    @PutMapping("/{inmateId}")
    public ResponseEntity<InmateDto> updateInmate(@PathVariable(value = "inmateId") long id, @RequestBody CreateInmateCommand changedInmate) {
        return ResponseEntity.ok(inmateService.updateInmate(id, changedInmate));
    }
}
