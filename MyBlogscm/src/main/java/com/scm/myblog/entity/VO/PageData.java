package com.scm.myblog.entity.VO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class PageData<T> {
    List<T> PageDataList;
    Integer total;

    public PageData() {
    }

    public PageData(List<T> PageDataList, Integer total) {
        this.PageDataList = PageDataList;
        this.total = total;
    }

    /**
     * 获取
     * @return PageDataList
     */
    public List<T> getPageDataList() {
        return PageDataList;
    }

    /**
     * 设置
     * @param PageDataList
     */
    public void setPageDataList(List<T> PageDataList) {
        this.PageDataList = PageDataList;
    }

    /**
     * 获取
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    public String toString() {
        return "PageData{PageDataList = " + PageDataList + ", total = " + total + "}";
    }
}
