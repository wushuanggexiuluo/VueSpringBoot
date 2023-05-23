package com.scm.myblog.entity.DTO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

/**
 * 统一页面dto
 *
 * @author 孙超孟
 * @date 2023/04/24
 */
@ApiModel("从前台接受的统一分页对象")
public class PageDto<T,V> {
    @ApiModelProperty(value = "当前查询对象的开始页面")
    @Min(value = 1,message = "开始页不能小于1")
    Integer currPage=1;
    @ApiModelProperty(value = "当前查询对象的页面尺寸")
    Integer pageSize=10;
    @ApiModelProperty(value = "查询对象的数据对象")
    V search;

    public PageDto() {
    }

    public PageDto(Integer currPage, Integer pageSize, V search) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.search = search;
    }

    //注意这里的的分页对象构建，传入1，10会自动转换为limit 1,10，
    // 传入2,10会转换为 10,20，所以不用再去手动构建currpage
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

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public V getSearch() {
        return search;
    }

    public void setSearch(V search) {
        this.search = search;
    }

    public String toString() {
        return "PageDto{currPage = " + currPage + ", pageSize = " + pageSize + ", search = " + search + "}";
    }

}
