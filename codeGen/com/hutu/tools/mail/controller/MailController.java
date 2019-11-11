package com.hutu.tools.mail.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hutu.common.entity.R;
import com.hutu.tools.mail.entity.Mail;
import com.hutu.tools.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  前端控制器
 *
 * @author generator
 * @since 2019-11-08
 */

@Api(tags = "")
@RestController
@RequestMapping("mail")
public class MailController{
    @Autowired
    private MailService mailService;

    @ApiOperation("获取page")
    @GetMapping("/page/{current}/{pageSize}")
    public R getPage(@ApiParam("当前页")@PathVariable("current")int current,@ApiParam("分页大小")@PathVariable("pageSize")int pageSize,
                     @ApiParam("关键字") @RequestParam(required = false) String keyWord) {
        QueryWrapper<Mail> query = new QueryWrapper<Mail>().like(StrUtil.isNotEmpty(keyWord), "name", keyWord);
        IPage<Mail> page = mailService.page(new Page<>(current,pageSize),query);
        return R.ok().put("list",page.getRecords()).put("total",page.getTotal());
    }
    @ApiOperation("新增或更新")
    @PostMapping("/createOrUpdate")
    public R createOrUpdate(@RequestBody @ApiParam("数据对象")Mail data){
        return mailService.saveOrUpdate(data)?R.ok():R.error("保存错误");
    }
    @ApiOperation("删除")
    @PostMapping("/delete/{ids}")
    public R delete(@ApiParam("数据对象id集合")@PathVariable("ids")String ids){
        return mailService.removeByIds(StrUtil.split(ids,','))?R.ok():R.error("删除错误");
    }
    @ApiOperation("通过ID获取一条数据")
    @GetMapping("/read/{id}")
    public R read(@ApiParam("数据对象id")@PathVariable("id")String id){
        return R.ok().put("info",mailService.getById(id));
    }
}
