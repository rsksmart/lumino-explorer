package org.rif.lumino.explorer.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.managers.LuminoHubBalancingManager;
import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"Lumino Hub Connection Resource"})
@RestController
@RequestMapping(ControllerConstants.API_V1_VERSION_PATH + LuminoHubBalancingController.BASE_CONTROLLER_PATH)
public class LuminoHubBalancingController {

    public static final String BASE_CONTROLLER_PATH = "/luminoHubConnection";

    private final LuminoHubBalancingManager luminoHubBalancingManager;

    public LuminoHubBalancingController(LuminoHubBalancingManager luminoHubBalancingManager) {
        this.luminoHubBalancingManager = luminoHubBalancingManager;
    }

    @ApiOperation(value = "Register a new intent to connect to a Lumino Hub, and returns the url of the most appropriate one")
    @PostMapping("/register_client/{address}")
    @ResponseBody
    public LuminoHubDTO register(@PathVariable(name = "address") String address) {
        return luminoHubBalancingManager.addConnectionAndGetUrl(address);
    }

    @ApiOperation(value = "Register a new intent to connect to a Lumino Hub, and returns the url of the most appropriate one")
    @PostMapping("/unregister_client/{address}")
    public void unregister(@PathVariable(name = "address") String address) {
        luminoHubBalancingManager.removeConnection(address);
    }

    @ApiOperation(value = "Save a new hub to the available list of hubs for the balancer")
    @PostMapping
    @ResponseBody
    public LuminoHub save(@RequestParam(name = "url") String url,
                          @RequestParam(name = "maxConnectionsAllowed", required = false, defaultValue = "0") long maxConnectionsAllowed,
                          @RequestParam(name = "infiniteCapacity", required = false, defaultValue = "false") boolean infiniteCapacity) {
        return luminoHubBalancingManager.saveHub(url, maxConnectionsAllowed, infiniteCapacity);
    }

    @ApiOperation(value = "Update an existent hub with the new paramters")
    @PutMapping("/{id}")
    @ResponseBody
    public LuminoHub update(@PathVariable(name = "id") String id,
                            @RequestParam(name = "newUrl", required = false) Optional<String> newUrl,
                            @RequestParam(name = "newMaxConnectionsAllowed", required = false) Optional<Long> newMaxConnectionsAllowed,
                            @RequestParam(name = "newInfiniteCapacity", required = false) Optional<Boolean> newInfiniteCapacity) {
        return luminoHubBalancingManager.updateHub(id, newUrl, newMaxConnectionsAllowed, newInfiniteCapacity);
    }

    @ApiOperation(value = "Gets a hub by id")
    @GetMapping("/{id}")
    @ResponseBody
    public LuminoHub getHub(@PathVariable(name = "id") String id) {
        return luminoHubBalancingManager.getHub(id);
    }

    @ApiOperation(value = "Gets all available hubs")
    @GetMapping
    @ResponseBody
    public List<LuminoHub> getAllHubs() {
        return luminoHubBalancingManager.getAllHubs();
    }

    @ApiOperation(value = "Deletes a hub by id")
    @DeleteMapping("/{id}")
    public void deleteHub(@PathVariable(name = "id") String id) {
        luminoHubBalancingManager.deleteHub(id);
    }
}
