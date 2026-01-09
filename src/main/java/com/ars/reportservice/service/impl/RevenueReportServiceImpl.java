package com.ars.reportservice.service.impl;

import com.ars.reportservice.client.OrderServiceClient;
import com.ars.reportservice.dto.request.RevenueReportFilter;
import com.ars.reportservice.dto.response.RevenueReportDTO;
import com.ars.reportservice.service.ExcelExportService;
import com.ars.reportservice.service.RevenueReportService;
import com.dct.config.common.Common;
import com.dct.model.dto.response.BaseResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class RevenueReportServiceImpl implements RevenueReportService {
    private final OrderServiceClient orderServiceClient;
    private final ObjectMapper objectMapper;
    private final ExcelExportService excelExportService;

    public RevenueReportServiceImpl(OrderServiceClient orderServiceClient,
                                    ObjectMapper objectMapper,
                                    ExcelExportService excelExportService) {
        this.orderServiceClient = orderServiceClient;
        this.objectMapper = objectMapper;
        this.excelExportService = excelExportService;
    }

    @Override
    public ByteArrayInputStream getRevenueReportExcel(RevenueReportFilter request) {
        Common.checkShopAuthorities(request.getShopId());
        BaseResponseDTO responseDTO = orderServiceClient.getRevenueReport(request);
        TypeReference<List<RevenueReportDTO>> typeReference = new TypeReference<>() {};
        List<RevenueReportDTO> revenueReportDTOS = objectMapper.convertValue(responseDTO.getResult(), typeReference);
        return excelExportService.exportRevenueReport(revenueReportDTOS, request);
    }
}
