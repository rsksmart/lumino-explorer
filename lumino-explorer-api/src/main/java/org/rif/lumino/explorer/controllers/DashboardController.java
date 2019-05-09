package org.rif.lumino.explorer.controllers;

import org.rif.lumino.explorer.managers.DashboardManager;
import org.rif.lumino.explorer.models.dto.DashboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private DashboardManager dashboardManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<DashboardDTO> getDashboardData(@RequestParam(name = "token_network_address", required = false) String tokenNetworkAddress) {
        return ResponseEntity.ok(dashboardManager.getDashboardData(tokenNetworkAddress));
    }

}
