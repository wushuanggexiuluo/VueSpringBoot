package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scm.myblog.mapper.*;
import com.scm.myblog.entity.*;
import com.scm.myblog.entity.DTO.LinkDto;
import com.scm.myblog.entity.DTO.LinkSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.LinkVo;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.FriendLinkService;
import com.scm.myblog.utils.DbUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 获取所有友链
     *
     * @return {@link Result}
     */
    public Result getAllLinks() {
        LambdaQueryWrapper<Link> lq = new LambdaQueryWrapper<>();
        lq.eq(Link::getLinkStatus, 1);
        lq.select(
                Link::getLinkUrl,
                Link::getLinkName,
                Link::getLinkImage,
                Link::getLinkUpdateTime,
                Link::getLinkDescription,
                Link::getLinkOwnerNickname);
        List<Link> links = linkMapper.selectList(lq);
        List<LinkVo> linkVos = new ArrayList<>();
        for (Link link : links) {
            LinkVo link2 = new LinkVo();
            BeanUtils.copyProperties(link, link2);
            linkVos.add(link2);
        }

        return new Result(linkVos, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMes());
    }

    @Override
    public Result getLinkPage(PageDto<LinkDto, LinkSearchDto> pto) {
        LinkSearchDto search = pto.getSearch();
        if (pto.getCurrPage() == null) {
            pto.setCurrPage(1);
        }
        if (pto.getPageSize() == null) {
            pto.setPageSize(10);
        }
        pto.setCurrPage(DbUtils.getCurePage(pto.getCurrPage(), pto.getPageSize()));
        //分页获取友链
        List<Link> linkPage = linkMapper.getAllLinkPage(
                search.getLinkOwnerNickname(),
                search.getLinkName(),
                pto.getCurrPage(),
                pto.getPageSize());
        //获取查询条数
        Integer i = linkMapper.getAllLinkCount(
                search.getLinkOwnerNickname(),
                search.getLinkName());
        return new Result(new PageData<>(linkPage, i), StatusMes.PAGE_OK.getCode(), StatusMes.PAGE_OK.getMes());

    }

    @Override
    public Result removeLink(Integer[] ids) {
        int i = 0;
        if (ids.length > 1) {
            i = linkMapper.deleteBatchIds(Arrays.asList(ids));
        } else {
            i = linkMapper.deleteById(ids[0]);
        }
        return new Result(null, i > 0 ? StatusMes.DELETE_OK.getCode() : StatusMes.DELETE_ERR.getCode(), i > 0 ? StatusMes.DELETE_OK.getMes() : StatusMes.DELETE_ERR.getMes());

    }

    @Override
    public Result updateLink(LinkDto dto) {
        Link link1 = new Link();
        BeanUtils.copyProperties(dto, link1);
        int i = linkMapper.updateById(link1);
        return new Result(null,
                i > 0 ? StatusMes.UPDATE_OK.getCode() : StatusMes.UPDATE_ERR.getCode(), i > 0 ? StatusMes.UPDATE_OK.getMes() : StatusMes.UPDATE_ERR.getMes());
    }

    /**
     * 增加链接
     *
     * @param link 链接
     * @return {@link Result}
     */
    public Result addLink(Link link, HttpServletRequest res) {
        Link one = linkMapper.selectOne(new LambdaQueryWrapper<Link>().eq(Link::getLinkName, link.getLinkName()));
        if (one == null) {
            //获取友链图片链接，从session中
            Object url = res.getSession().getAttribute("url");
            if(url != null) {
                link.setLinkImage((String) url);
            }
            link.setLinkStatus(0);
            int i = linkMapper.insert(link);
            return new Result(null, i > 0 ? StatusMes.SAVE_OK.getCode() : StatusMes.SAVE_ERR.getCode(), i > 0 ? StatusMes.SAVE_OK.getMes() : StatusMes.SAVE_ERR.getMes());
        } else {
            return new Result(null, StatusMes.SAVE_ERR.getCode(), "友链名不能重复！");
        }
    }
}
