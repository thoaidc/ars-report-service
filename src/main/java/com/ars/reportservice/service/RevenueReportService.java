package com.ars.reportservice.service;

import com.ars.reportservice.dto.request.RevenueReportFilter;
import java.io.ByteArrayInputStream;

public interface RevenueReportService {
    ByteArrayInputStream getRevenueReportExcel(RevenueReportFilter request);
}
