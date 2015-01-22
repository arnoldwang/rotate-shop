package com.dianping.rotate.shop.task;

import com.dianping.trade.data.api.ReportRemoteService;
import com.dianping.trade.data.dto.ReportQueryPageDTO;
import com.dianping.trade.data.dto.ReportQueryParameterDTO;
import com.dianping.trade.data.dto.ReportQueryResultDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by luoming on 15/1/21.
 */
public class ReportDataProcessor {

    @Autowired
    private ReportRemoteService reportRemoteService;

    private int pageIndex = 0;
    private int pageSize = 10000;

    public void getData() {
        ReportQueryResultDTO reportQueryResultDTO = reportRemoteService.getReport(getReportQueryParameterDTO());
    }

    private ReportQueryPageDTO getReportQueryPageDTO() {
        ReportQueryPageDTO reportQueryPageDTO = new ReportQueryPageDTO();
        reportQueryPageDTO.setPage(pageIndex);
        reportQueryPageDTO.setSize(pageSize);
        return reportQueryPageDTO;
    }

    private ReportQueryParameterDTO getReportQueryParameterDTO() {
        ReportQueryParameterDTO reportQueryParameterDTO = new ReportQueryParameterDTO();
        reportQueryParameterDTO.setReportName("");
        reportQueryParameterDTO.setReportQueryPageDTO(getReportQueryPageDTO());
        return reportQueryParameterDTO;
    }

}
