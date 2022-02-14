package com.rebelalliance.operationquasarfire.controller;

import com.rebelalliance.operationquasarfire.models.*;
import com.rebelalliance.operationquasarfire.services.impl.TopSecretService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;


import static com.rebelalliance.operationquasarfire.constants.Satellites.*;

@RestController
@RequestMapping(value = PATH_ROOT)
public class SatelliteController {

    @Autowired()
    private TopSecretService topSecretService;

    @PostMapping(value = PATH_TOP_SECRET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> postSatellite(@Valid @RequestBody Request request){
        Response response = null;
        try {
            response = topSecretService.findPositionMessage(request.getSatellites());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
