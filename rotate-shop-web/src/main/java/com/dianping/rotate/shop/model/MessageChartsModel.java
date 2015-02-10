package com.dianping.rotate.shop.model;

import com.dianping.rotate.shop.model.ChartsModel.Series;
import com.dianping.rotate.shop.model.ChartsModel.Title;
import com.dianping.rotate.shop.model.ChartsModel.XAxis;

import java.util.List;

/**
 * Created by zaza on 15/2/9.
 */
public class MessageChartsModel {
    private Title title;
    private XAxis xAxis;
    private List<Series> series;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public XAxis getXAxis() {
        return xAxis;
    }

    public void setXAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

}
