package com.scm.myblog.entity.DTO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;

import static com.scm.myblog.utils.DbUtils.getCurePage;

@ApiModel("从前台接受的书报对象")
public class PageDto<T,V> {
    Integer currPage;
    Integer pageSize;
    V search;

    public PageDto() {
    }

    public PageDto(Integer currPage, Integer pageSize, V search) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.search = search;
    }


    public IPage<T> pageBuild() {
        return new Page<T>(getCurrPage(), getPageSize());
    }

    /**
     * 获取
     * @return currPage
     */
    public Integer getCurrPage() {
        return currPage;
    }

    /**
     * 设置
     * @param currPage
     */
    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    /**
     * 获取
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取
     * @return search
     */
    public V getSearch() {
        return search;
    }

    /**
     * 设置
     * @param search
     */
    public void setSearch(V search) {
        this.search = search;
    }

    public String toString() {
        return "PageDto{currPage = " + currPage + ", pageSize = " + pageSize + ", search = " + search + "}";
    }


    /**
     * 获取
     * @return currPage
     */

}
