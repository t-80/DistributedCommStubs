package com.course.distributecommunication.frontend.controllers;

import com.course.distributecommunication.frontend.dto.FrontendDto;
import com.course.distributecommunication.frontend.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public FrontendDto getFrontendDto() {
        return dashboardService.getAggregate();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerSameControllerExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
