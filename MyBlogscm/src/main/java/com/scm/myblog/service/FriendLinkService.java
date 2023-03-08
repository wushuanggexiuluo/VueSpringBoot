package com.scm.myblog.service;

import com.scm.myblog.entity.DTO.LinkDto;
import com.scm.myblog.entity.DTO.LinkSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.Link;
import com.scm.myblog.entity.VO.Result;

import javax.servlet.http.HttpServletRequest;

public interface FriendLinkService {

    /**
     * 获取所有友链
     *
     * @return {@link Result}
     */
    Result getAllLinks();

    /**
     * 获取友链已分页和模糊同时进行的方式
     *
     * @param pto 美国专利商标局
     * @return {@link Result}
     */
    Result getLinkPage(PageDto<LinkDto, LinkSearchDto> pto);

    /**
     * 删除友链
     *
     * @param ids id
     * @return {@link Result}
     */
    Result removeLink(Integer[] ids);

    /**
     * 更新友链
     *
     * @param dto dto
     * @return {@link Result}
     */
    Result updateLink(LinkDto dto);

    /**
     * 添加友链
     *
     * @param link 友链
     * @param res  res
     * @return {@link Result}
     */
    Result addLink(Link link, HttpServletRequest res);
}
