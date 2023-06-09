package com.example.backend.controllers;

import com.example.backend.models.Country;
import com.example.backend.models.Museum;
import com.example.backend.repositories.MuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MuseumController {
    @Autowired
    MuseumRepository museumRepository;

    @GetMapping("/museums")
    public Page<Museum> getAllCountries(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return museumRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "name")));
    }

    @GetMapping("/museums/{id}")
    public Optional<Museum>
    getMuseum(@PathVariable(value = "id") Long museumId) {
        return museumRepository.findById(museumId);
    }

    @PostMapping("/museums")
    public ResponseEntity<Object> createMuseum(@RequestBody Museum museum)
            throws Exception {
        try {
            Museum nc = museumRepository.save(museum);
            return ResponseEntity.ok(nc);
        } catch (Exception ex) {
            String error = "undefinederror";
            Map<String, String> map = new HashMap<>();
            map.put("error", error);
            return ResponseEntity.ok(map);
        }
    }

    @PostMapping("/deletemuseums")
    public ResponseEntity<HttpStatus> deleteMuseums(@RequestBody List<Museum> museums) {
        museumRepository.deleteAll(museums);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/museums/{id}")
    public ResponseEntity<Museum> updateMuseum(@PathVariable(value = "id") Long museumId,
                                               @RequestBody Museum museum) {
        Museum mus = null;
        Optional<Museum> cc = museumRepository.findById(museumId);
        if (cc.isPresent()) {
            mus = cc.get();
            mus.name = museum.name;
            mus.location = museum.location;
            museumRepository.save(mus);
            return ResponseEntity.ok(mus);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "artist not found");
        }
    }
}