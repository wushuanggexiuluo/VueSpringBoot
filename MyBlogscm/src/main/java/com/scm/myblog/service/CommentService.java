package com.scm.myblog.service;

import com.scm.myblog.entity.DTO.CommentDto;
import com.scm.myblog.entity.DTO.CommentSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.entity.DTO.UserCommentDto;

public interface CommentService {

    /**
     * 设置评论
     *
     * @param comment 评论
     * @return {@link Result}
     */
    public Result setComment(UserCommentDto comment);

    /**
     * 得到文章评论
     *
     * @param title 标题
     * @return {@link Result}
     */
    public Result getArticleComment(String title);

    /**
     * 得到评论页面
     *
     * @param pto 美国专利商标局
     * @return {@link Result}
     */
    public Result getCommentPage(PageDto<CommentDto, CommentSearchDto> pto);

    /**
     * 删除评论
     *
     * @param ids id
     * @return {@link Result}
     */
    Result removeComment(Long[] ids);

    /**
     * 更新评论
     *
     * @param dto dto
     * @return {@link Result}
     */
    Result updateComment(CommentDto dto);

}
