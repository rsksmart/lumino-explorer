package org.rif.lumino.explorer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.managers.FeedManager;
import org.rif.lumino.explorer.models.dto.FeedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"Feed Resource"})
@RestController
@RequestMapping(ControllerConstants.API_V1_VERSION_PATH)
public class FeedController {

    private static final String BASE_CONTROLLER_PATH = "feed";

    @Autowired
    private FeedManager feedManager;

    @ApiOperation(value = "View a list of available feeds, these can be of the type: channel, node or token",
            response = FeedDTO.class, responseContainer = ControllerConstants.LIST_RESPONSE_CONTAINER)
    @RequestMapping(value = BASE_CONTROLLER_PATH, method = RequestMethod.GET, produces = {ControllerConstants.CONTENT_TYPE_APPLICATION_JSON})
    public ResponseEntity<List<FeedDTO>> getFeeds(@RequestParam(name = "id", required = false) Long feedId) {
        return ResponseEntity.ok(feedManager.getFeeds(feedId));
    }
}
