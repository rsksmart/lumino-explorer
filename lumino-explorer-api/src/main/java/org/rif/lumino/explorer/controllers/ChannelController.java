package org.rif.lumino.explorer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.managers.ChannelManager;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.rif.lumino.explorer.models.dto.ListChannelsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Channel Resource"})
@RestController
@RequestMapping(ControllerConstants.API_V1_VERSION_PATH)
public class ChannelController {

    public static final String BASE_CONTROLLER_PATH = "channel";

    @Autowired
    private ChannelManager channelManager;

    @ApiOperation(value = "View a list of available channels by state and/or token_network_address values",
            response = ChannelDTO.class, responseContainer = ControllerConstants.LIST_RESPONSE_CONTAINER)
    @RequestMapping(value = BASE_CONTROLLER_PATH, method = RequestMethod.GET, produces = {ControllerConstants.CONTENT_TYPE_APPLICATION_JSON})
    public ResponseEntity<ListChannelsResponseDTO> getChannels(@RequestParam(name = "token_network_address", required = false) String tokenNetworkAddress,
                                                               @RequestParam(name = "state", required = false) String state) {
        return ResponseEntity.ok(channelManager.getChannels(tokenNetworkAddress, state));
    }

}
