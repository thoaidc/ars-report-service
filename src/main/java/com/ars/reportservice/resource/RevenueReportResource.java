package com.ars.reportservice.resource;

import com.ars.reportservice.dto.request.RevenueReportFilter;
import com.ars.reportservice.service.RevenueReportService;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/v1/reports")
public class RevenueReportResource {
    private final RevenueReportService revenueReportService;

    public RevenueReportResource(RevenueReportService revenueReportService) {
        this.revenueReportService = revenueReportService;
    }

    @GetMapping("/revenues")
    public ResponseEntity<InputStreamResource> getRevenueReport(@ModelAttribute RevenueReportFilter request) {
        ByteArrayInputStream in = revenueReportService.getRevenueReportExcel(request);
        String fileName = "bao_cao_doanh_thu.xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
}
