package com.scm.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.Comment;
import com.scm.myblog.entity.VO.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVo> getAllComment(@Param("articleTitle") String articleTitle,
                                  @Param("commentAuthorName")String commentAuthorName,
                                  @Param("commentAuthorEmail")String commentAuthorEmail,
                                  @Param("commentContent")String commentContent,
                                  @Param("currentPage")Integer currentPage,
                                  @Param("pageSize")Integer pageSize);

    Integer getAllCommentCount(@Param("articleTitle") String articleTitle,
                               @Param("commentAuthorName")String commentAuthorName,
                               @Param("commentAuthorEmail")String commentAuthorEmail,
                               @Param("commentContent")String commentContent);
}
