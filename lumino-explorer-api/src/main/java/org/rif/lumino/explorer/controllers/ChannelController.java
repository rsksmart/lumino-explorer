package org.rif.lumino.explorer.controllers;

import org.rif.lumino.explorer.managers.ChannelManager;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("channel")
public class ChannelController {

    @Autowired
    private ChannelManager channelManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ChannelDTO>> getChannels(@RequestParam(name = "token_network_address", required = false) String tokenNetworkAddress,
                                                                      @RequestParam(name = "state", required = false) String state) {
        return ResponseEntity.ok(channelManager.getChannels(tokenNetworkAddress, state));
    }

}
