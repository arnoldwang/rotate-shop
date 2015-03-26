package com.dianping.rotate.shop.task;

import com.dianping.rotate.shop.utils.JsonUtil;
import com.dianping.trade.data.api.ReportRemoteService;
import com.dianping.trade.data.dto.*;
import com.dianping.trade.data.enums.ReportQueryOrderByType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luoming on 15/1/21.
 */
public class ReportDataProcessor {

    @Autowired
    private ReportRemoteService reportRemoteService;

    private AtomicInteger pageIndex = new AtomicInteger(1);
    private final static int pageSize = 2000;

    private boolean isDataOver;
    private String reportName;
    private String orderByName = "id";

    public AtomicInteger getPageIndex() {
        return pageIndex;
    }

    public boolean isDataOver() {
        return isDataOver;
    }

    public void setDataOver(boolean isDataOver) {
        this.isDataOver = isDataOver;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getOrderByName() {
        return orderByName;
    }

    public void setOrderByName(String orderByName) {
        this.orderByName = orderByName;
    }

    public List<HashMap<String, Object>> getData() {
        ReportQueryResultDTO reportQueryResultDTO = reportRemoteService.getReport(getReportQueryParameterDTO());
        if(reportQueryResultDTO != null) {
            List<HashMap<String, Object>> result = reportQueryResultDTO.getReport();
            if(CollectionUtils.isNotEmpty(result)) {
                if(result.size() < pageSize) {
                    this.setDataOver(true);
                } else {
                    pageIndex.getAndIncrement();
                }
                return result;
            }
        }
        this.setDataOver(true);
        return null;
    }

    private ReportQueryPageDTO getReportQueryPageDTO() {
        ReportQueryPageDTO reportQueryPageDTO = new ReportQueryPageDTO();
        reportQueryPageDTO.setPage(pageIndex.get());
        reportQueryPageDTO.setSize(pageSize);
        return reportQueryPageDTO;
    }

    private List<ReportQueryOrderByDTO> getReportQueryOrderByDTOList() {
        List<ReportQueryOrderByDTO> reportQueryOrderByDTOList = new ArrayList<ReportQueryOrderByDTO>();
        ReportQueryOrderByDTO reportQueryOrderByDTO = new ReportQueryOrderByDTO();
        reportQueryOrderByDTO.setName(this.getOrderByName());
        reportQueryOrderByDTO.setType(ReportQueryOrderByType.ASC);
        reportQueryOrderByDTOList.add(reportQueryOrderByDTO);
        return reportQueryOrderByDTOList;
    }

    private ReportQueryParameterDTO getReportQueryParameterDTO() {
        ReportQueryParameterDTO reportQueryParameterDTO = new ReportQueryParameterDTO();
        reportQueryParameterDTO.setReportName(this.getReportName());
        reportQueryParameterDTO.setReportQueryPageDTO(getReportQueryPageDTO());
        reportQueryParameterDTO.setReportQueryOrderByDTOList(getReportQueryOrderByDTOList());
        return reportQueryParameterDTO;
    }

}
