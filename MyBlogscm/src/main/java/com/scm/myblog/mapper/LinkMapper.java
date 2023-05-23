package com.scm.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scm.myblog.entity.DOMAIN.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface LinkMapper extends BaseMapper<Link> {
    List<Link> getAllLinkPage(@Param("linkOwnerNickname") String linkOwnerNickname,
                                  @Param("linkName")String linkName,
                                  @Param("currentPage")Integer currentPage,
                                  @Param("pageSize")Integer pageSize);

    Integer getAllLinkCount(@Param("linkOwnerNickname") String linkOwnerNickname,
                            @Param("linkName")String linkName);
}
