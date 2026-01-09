package com.ars.reportservice.service;

import com.ars.reportservice.dto.request.RevenueReportFilter;
import com.dct.model.dto.response.BaseResponseDTO;

public interface RevenueReportService {
    BaseResponseDTO getRevenueReport(RevenueReportFilter request);
}
