package org.rif.lumino.explorer.controllers;

import org.rif.lumino.explorer.managers.SearchManager;
import org.rif.lumino.explorer.models.dto.SearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired private SearchManager searchManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<SearchResultDTO> search(@RequestParam(name = "query", required = true) String query) {
        return ResponseEntity.ok(searchManager.search(query));
    }

}
