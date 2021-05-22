package com.goldenburguer.app.controllers;


import com.goldenburguer.app.config.SwaggerConfig;
import com.goldenburguer.app.dto.dashboard.DashboardDTO;
import com.goldenburguer.app.dto.dashboard.QntSellingProductDTO;
import com.goldenburguer.app.services.DashboardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
@Api(tags = SwaggerConfig.ORDER)
public class DashboardController {

private final DashboardService service;


    @GetMapping
    @ApiOperation(value = "List all order")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List all order"),
                    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                    @ApiResponse(code = 500, message = "An exception was generated"),
            })
    public ResponseEntity<DashboardDTO> listOrders() {
        DashboardDTO dashboardDTO = service.getDashboard();
        return ResponseEntity.ok().body(dashboardDTO);
    }

    @GetMapping("a")
    @ApiOperation(value = "List all order")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "List all order"),
                    @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
                    @ApiResponse(code = 500, message = "An exception was generated"),
            })
    public ResponseEntity<List<QntSellingProductDTO>> listOrderss() {
        List<QntSellingProductDTO> a = service.getProductBestSelling();
        return ResponseEntity.ok().body(a);
    }

}
