package org.rif.lumino.explorer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.managers.DashboardManager;
import org.rif.lumino.explorer.models.dto.DashboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Dashboard Resource"})
@RestController
@RequestMapping(ControllerConstants.API_V1_VERSION_PATH)
public class DashboardController {

    private static final String BASE_CONTROLLER_PATH = "dashboard";

    @Autowired
    private DashboardManager dashboardManager;

    @ApiOperation(value = "View a open channel list, registered nodes list, a summary object with nodes and tokens information, " +
            "and all registered tokens with those respective open channels", response = DashboardDTO.class)
    @RequestMapping(value = BASE_CONTROLLER_PATH, method = RequestMethod.GET, produces = {ControllerConstants.CONTENT_TYPE_APPLICATION_JSON})
    public ResponseEntity<DashboardDTO> getDashboardData(@RequestParam(name = "token_network_address", required = false) String tokenNetworkAddress) {
        return ResponseEntity.ok(dashboardManager.getDashboardData(tokenNetworkAddress));
    }

}
