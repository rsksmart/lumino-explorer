package org.rif.lumino.explorer.controllers;

import org.rif.lumino.explorer.managers.LuminoNodeManager;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("luminoNode")
public class LuminoNodeController {

  @Autowired private LuminoNodeManager luminoNodeManager;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<LuminoNodeDTO> add(@Valid @RequestBody LuminoNodeDTO luminoNodeDTO) {
    LuminoNodeDTO result = luminoNodeManager.register(luminoNodeDTO);
    return ResponseEntity.ok(result);
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<List<LuminoNodeDTO>> getNodes() {
    return ResponseEntity.ok(luminoNodeManager.getNodes());
  }
}
