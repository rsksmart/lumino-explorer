package org.rif.lumino.explorer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.exceptions.MaxConnectionException;
import org.rif.lumino.explorer.exceptions.NotFoundException;
import org.rif.lumino.explorer.managers.LuminoHubManager;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Lumino Hub Connection Resource"})
@RestController
@RequestMapping(ControllerConstants.API_V1_VERSION_PATH + LuminoHubConnectionController.BASE_CONTROLLER_PATH)
public class LuminoHubConnectionController {

    public static final String BASE_CONTROLLER_PATH = "/luminoHubConnection";

    @Autowired
    private LuminoHubManager luminoHubManager;

    @ApiOperation(value = "Register a new intent to connect to a Lumino Hub, and returns the url of the most appropriate one")
    @PostMapping
    public ResponseEntity<LuminoHubDTO> register() {
        LuminoHubDTO hubDto = luminoHubManager.addConnectionAndGetUrl();
        return ResponseEntity.ok(hubDto);
    }

    @ExceptionHandler(MaxConnectionException.class)
    public ResponseEntity<Void> handle(MaxConnectionException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handle(NotFoundException e) {
        return ResponseEntity.notFound().build();
    }

}
