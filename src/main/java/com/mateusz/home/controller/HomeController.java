package com.mateusz.home.controller;

import com.mateusz.home.command.CreateHomeCommand;
import com.mateusz.home.dto.HomeDto;
import com.mateusz.home.dto.HomeWithInmatesDto;
import com.mateusz.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/homes")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @PostMapping
    public ResponseEntity<HomeDto> addHome(@RequestBody CreateHomeCommand newHome) {
        final HomeDto createdHome = homeService.createHome(newHome);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHome);
    }

    @GetMapping
    public ResponseEntity<List<HomeDto>> getHomes() {
        return ResponseEntity.ok(homeService.getHomes());
    }

    @GetMapping("/{homeId}")
    public ResponseEntity<HomeDto> getHomeById(@PathVariable(value = "homeId") long id) {
        return ResponseEntity.ok(homeService.getHomeById(id));
    }

    @DeleteMapping("/{homeId}")
    public ResponseEntity<HomeDto> deleteHome(@PathVariable(value = "homeId") long id) {
        return ResponseEntity.ok(homeService.deleteHome(id));
    }

    @PostMapping("/{homeId}/inmates/{inmateId}")
    public ResponseEntity<HomeWithInmatesDto> addInmate(@PathVariable(value = "homeId") long homeId, @PathVariable(value = "inmateId") long inmateId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.addInmate(homeId, inmateId));
    }

    @GetMapping("/inmates")
    public ResponseEntity<List<HomeWithInmatesDto>> getHomesWithInmates() {
        return ResponseEntity.ok(homeService.getHomesWithInmates());
    }

    @GetMapping("/{homeId}/inmates")
    public ResponseEntity<HomeWithInmatesDto> getHomeWithInmatesById(@PathVariable(value = "homeId") long id) {
        return ResponseEntity.ok(homeService.getHomeWithInmatesById(id));
    }

    @DeleteMapping("/{homeId}/inmates/{inmateId}")
    public ResponseEntity<HomeWithInmatesDto> removeInmate(@PathVariable(value = "homeId") long homeId, @PathVariable(value = "inmateId") long inmateId) {
        return ResponseEntity.ok(homeService.removeInmate(homeId, inmateId));
    }
}
