package com.ars.reportservice.service;

import com.ars.reportservice.dto.request.RevenueFilterRequest;
import com.dct.model.dto.response.BaseResponseDTO;

public interface RevenueReportService {
    BaseResponseDTO getRevenueReport(RevenueFilterRequest request);
}
