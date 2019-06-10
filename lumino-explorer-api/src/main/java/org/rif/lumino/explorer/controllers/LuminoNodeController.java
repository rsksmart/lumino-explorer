package org.rif.lumino.explorer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.managers.LuminoNodeManager;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"Lumino Node Resource"})
@RestController
@RequestMapping(ControllerConstants.API_V1_VERSION_PATH)
public class LuminoNodeController {

    private static final String BASE_CONTROLLER_PATH = "luminoNode";

    @Autowired
    private LuminoNodeManager luminoNodeManager;

    @ApiOperation(value = "Add a new lumino node to the topography of the graph", response = LuminoNodeDTO.class)
    @RequestMapping(value = BASE_CONTROLLER_PATH, method = RequestMethod.POST, produces = {ControllerConstants.CONTENT_TYPE_APPLICATION_JSON})
    public ResponseEntity<LuminoNodeDTO> add(@Valid @RequestBody LuminoNodeDTO luminoNodeDTO) {
        LuminoNodeDTO result = luminoNodeManager.register(luminoNodeDTO);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "View a list of available lumino nodes", response = LuminoNodeDTO.class, responseContainer = "List")
    @RequestMapping(value = BASE_CONTROLLER_PATH, method = RequestMethod.GET, produces = {ControllerConstants.CONTENT_TYPE_APPLICATION_JSON})
    public ResponseEntity<List<LuminoNodeDTO>> getNodes() {
        return ResponseEntity.ok(luminoNodeManager.getNodes());
    }
}
