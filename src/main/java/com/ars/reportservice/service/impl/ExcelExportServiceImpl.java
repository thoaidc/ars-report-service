package com.ars.reportservice.service.impl;

import com.ars.reportservice.dto.request.RevenueReportFilter;
import com.ars.reportservice.dto.response.RevenueReportDTO;
import com.ars.reportservice.service.ExcelExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Override
    public ByteArrayInputStream exportRevenueReport(List<RevenueReportDTO> reports, RevenueReportFilter filter) {
        String[] columns = {"ID", "Mã SP", "Tên Sản Phẩm", "Doanh thu theo giá bán", "Doanh thu thực tế", "Số lượng bán"};

        try (Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Báo cáo doanh thu theo sản phẩm");
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            CellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setBorderBottom(BorderStyle.THIN);
            dataCellStyle.setBorderTop(BorderStyle.THIN);
            dataCellStyle.setBorderLeft(BorderStyle.THIN);
            dataCellStyle.setBorderRight(BorderStyle.THIN);
            dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            CellStyle centerCellStyle = workbook.createCellStyle();
            centerCellStyle.setBorderBottom(BorderStyle.THIN);
            centerCellStyle.setBorderTop(BorderStyle.THIN);
            centerCellStyle.setBorderLeft(BorderStyle.THIN);
            centerCellStyle.setBorderRight(BorderStyle.THIN);
            centerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            centerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BÁO CÁO DOANH THU THEO SẢN PHẨM");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.length - 1));

            Row timeRow = sheet.createRow(1);
            Cell timeCell = timeRow.createCell(0);
            String timeInfo = String.format("Thời gian: %s - %s", filter.getFromDate(), filter.getToDate());
            timeCell.setCellValue(timeInfo);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columns.length - 1));

            int currentRow = 3;
            Row headerRow = sheet.createRow(currentRow++);
            headerRow.setHeightInPoints(25);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            for (RevenueReportDTO report : reports) {
                Row row = sheet.createRow(currentRow++);
                row.setHeightInPoints(20);
                createCell(row, 0, report.getProductId(), centerCellStyle);
                createCell(row, 1, report.getProductCode(), dataCellStyle);
                createCell(row, 2, report.getProductName(), dataCellStyle);
                createCell(row, 3, report.getGrossRevenue().doubleValue(), dataCellStyle);
                createCell(row, 4, report.getNetRevenue().doubleValue(), dataCellStyle);
                createCell(row, 5, report.getTotalSales(), centerCellStyle);
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1000);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi xuất file Excel: " + e.getMessage());
        }
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue(value != null ? value.toString() : "");
        }

        cell.setCellStyle(style);
    }
}
