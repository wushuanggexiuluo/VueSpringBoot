package com.scm.myblog.controller.admincontrollers;



import com.scm.myblog.entity.DOMAIN.Category;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.TagSearchDto;
import com.scm.myblog.entity.VO.Result;
import com.scm.myblog.service.impl.TagServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(tags = "管理标签控制器")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Admin")
public class AdminTagController {
    @Autowired
    public TagServiceImpl tagService;

    /**
     * 获得所有消息
     *
     * @return {@link Result}
     */
    @ApiOperation("模糊查询加分页获取标签数据")
    @PostMapping("/getTagPage")
    public Result getAllTag(@RequestBody @ApiParam("标签dto对象") PageDto<Category, TagSearchDto> pageData) {
        return tagService.getAllTagPage(pageData);
    }
    /**
     * 删除消息
     *
     * @param Ids id
     * @return {@link Result}
     */
    @ApiOperation("移除标签")
    @PostMapping("/removeTag")
    public Result removeTag(@RequestBody Integer[] Ids) {
        return tagService.removeTag(Ids);
    }
    @ApiOperation("更新标签")
    @PostMapping("/updateTag")
    public Result updateTag(@RequestBody Category tag) {
        return tagService.updateTag(tag);
    }
    @ApiOperation("新增标签")
    @PostMapping("/addTag")
    public Result addTag(@RequestBody Category tag) {
        return tagService.AddTag(tag);
    }
}
