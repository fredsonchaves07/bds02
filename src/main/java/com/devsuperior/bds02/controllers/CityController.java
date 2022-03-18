package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

   @Autowired
   private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(Pageable pageable) {
        List<CityDTO> cityDTOList = cityService.findAll(pageable);
        return ResponseEntity.ok().body(cityDTOList);
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO cityDTO) {
        cityDTO = cityService.insert(cityDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/id")
                .buildAndExpand(cityDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cityDTO);
    }
}
