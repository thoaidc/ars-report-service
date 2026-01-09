package com.ars.reportservice.resource;

import com.ars.reportservice.dto.request.RevenueFilterRequest;
import com.ars.reportservice.service.RevenueReportService;
import com.dct.model.dto.response.BaseResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class RevenueReportResource {
    private final RevenueReportService revenueReportService;

    public RevenueReportResource(RevenueReportService revenueReportService) {
        this.revenueReportService = revenueReportService;
    }

    @GetMapping("/revenues")
    public BaseResponseDTO getRevenueReport(@ModelAttribute RevenueFilterRequest request) {
        return revenueReportService.getRevenueReport(request);
    }
}
