package org.rif.lumino.explorer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.managers.SearchManager;
import org.rif.lumino.explorer.models.dto.SearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = { "Search Resource" })
@RestController
@RequestMapping(ControllerConstants.API_V1_VERSION_PATH)
public class SearchController {

    private static final String BASE_CONTROLLER_PATH = "search";

    @Autowired private SearchManager searchManager;

    @ApiOperation(value = "View a list of result for tokens, nodes, channels", response = SearchResultDTO.class)
    @RequestMapping(value = BASE_CONTROLLER_PATH, method = RequestMethod.GET, produces = {ControllerConstants.CONTENT_TYPE_APPLICATION_JSON})
    public ResponseEntity<SearchResultDTO> search(@RequestParam(name = "query", required = true) String query) {
        return ResponseEntity.ok(searchManager.search(query));
    }

}
