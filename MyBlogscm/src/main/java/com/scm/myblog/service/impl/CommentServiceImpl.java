package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.Article;
import com.scm.myblog.entity.DOMAIN.Comment;
import com.scm.myblog.entity.DTO.CommentDto;
import com.scm.myblog.entity.DTO.CommentSearchDto;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.UserCommentDto;
import com.scm.myblog.entity.VO.CommentVo;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.manager.ServiceCommonManager.DeleteManager;
import com.scm.myblog.mapper.ArticleMapper;
import com.scm.myblog.mapper.CommentMapper;
import com.scm.myblog.service.CommentService;
import com.scm.myblog.utils.DbUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;

    /**
    /**
     * 设置评论
     *
     * @param comment 评论
     * @return {@link Result}
     */
    public Result setComment(UserCommentDto comment) {
        //获取文章id
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(Article::getArticleTitle, comment.getArticleTitle());
        queryWrapper.select(Article::getArticleId);
        long articleId = articleMapper.selectOne(queryWrapper).getArticleId();
        //复制属性
        Comment comment1 = new Comment();
        BeanUtils.copyProperties(comment, comment1, "articleTitle");
        //设置文章id
        comment1.setCommentArticleId(articleId);
        //插入评论数据
        int i = commentMapper.insert(comment1);
        Result r=new Result();
        if (i > 0) {
            r.setData(comment.getArticleTitle());
            r.setMessage(StatusMes.SAVE_OK.getMessage());
            r.setCode(StatusMes.SAVE_OK.getCode());
        }
        else {
            r.setData(null);
            r.setMessage(StatusMes.SAVE_ERR.getMessage());
            r.setCode(StatusMes.SAVE_ERR.getCode());
        }
        return r;
    }

    /**
     * 得到单个文章评论
     *
     * @param title 标题
     * @return {@link Result}
     */
    public Result getArticleComment(String title) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Comment> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(Article::getArticleTitle, title).select(Article::getArticleId);
        Long articleId = articleMapper.selectOne(queryWrapper).getArticleId();
        queryWrapper2 = queryWrapper2.eq(Comment::getCommentArticleId, articleId);
        List<Comment> list = commentMapper.selectList(queryWrapper2);
        return new Result(list, StatusMes.GET_OK.getCode(), StatusMes.GET_OK.getMessage());
    }

    @Override
    public Result getCommentPage(PageDto<CommentDto, CommentSearchDto> pto) {
        CommentSearchDto search1 = pto.getSearch();
        if(pto.getCurrPage()==null){
            pto.setCurrPage(1);
        }
        if (pto.getPageSize() == null) {
            pto.setPageSize(10);
        }

        pto.setCurrPage(DbUtils.getCurePage(pto.getCurrPage(),pto.getPageSize()));
        //连表获得所有数据
        List<CommentVo> allComment = commentMapper.getAllComment(
                search1.getTitle(),
                search1.getName(),
                search1.getEmail(),
                search1.getContent(),
                pto.getCurrPage(),
                pto.getPageSize());
        //获取条数
        Integer total= commentMapper.getAllCommentCount(search1.getTitle(),
                search1.getName(),
                search1.getEmail(),
                search1.getContent());
        return new Result(new PageData<>(allComment,total),StatusMes.PAGE_OK.getCode(), StatusMes.PAGE_OK.getMessage());
    }
    @Override
    public Result removeComment(Long[] ids) {
        boolean isOk=false;
        List<Long> idList = Arrays.asList(ids);
        List<Comment> list = commentMapper.selectBatchIds(idList);
        if (list.size() >=ids.length){
            isOk = true;
            commentMapper.deleteBatchIds(idList);
        }
        return DeleteManager.deleteBatchIds(isOk, ids);
    }

    @Override
    public Result updateComment(CommentDto dto) {
        int i;
        Comment c=new Comment();
        BeanUtils.copyProperties(dto,c);
        i= commentMapper.updateById(c);
        return new Result(null,i>0?StatusMes.UPDATE_OK.getCode()
                :StatusMes.UPDATE_ERR.getCode(),i>0?StatusMes.UPDATE_OK.getMessage():StatusMes.UPDATE_ERR.getMessage());
    }
}
