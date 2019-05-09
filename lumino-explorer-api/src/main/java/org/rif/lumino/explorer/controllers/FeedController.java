package org.rif.lumino.explorer.controllers;

import org.rif.lumino.explorer.managers.FeedManager;
import org.rif.lumino.explorer.models.dto.FeedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("feed")
public class FeedController {

    @Autowired
    private FeedManager feedManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FeedDTO>> getFeeds(@RequestParam(name = "id", required = false) Long feedId) {
        return ResponseEntity.ok(feedManager.getFeeds(feedId));
    }
}
