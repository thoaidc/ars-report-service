package com.ars.reportservice.service;

import com.ars.reportservice.dto.request.RevenueReportFilter;
import com.ars.reportservice.dto.response.RevenueReportDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelExportService {
    ByteArrayInputStream exportRevenueReport(List<RevenueReportDTO> reports, RevenueReportFilter filter);
}
