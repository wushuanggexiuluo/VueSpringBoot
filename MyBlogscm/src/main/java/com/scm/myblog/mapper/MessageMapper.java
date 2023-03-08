package com.scm.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface MessageMapper extends BaseMapper<Message> {
    List<Message> getAllMessagePage(@Param("messageAuthorName") String messageAuthorName,
                                    @Param("messageAuthorEmail") String messageAuthorEmail,
                                    @Param("messageContent") String messageContent,
                                    @Param("currentPage") Integer currentPage,
                                    @Param("pageSize") Integer pageSize);

    Integer getAllMessageCount(@Param("messageAuthorName") String messageAuthorName,
                               @Param("messageAuthorEmail") String messageAuthorEmail,
                               @Param("messageContent") String messageContent
    );
}
