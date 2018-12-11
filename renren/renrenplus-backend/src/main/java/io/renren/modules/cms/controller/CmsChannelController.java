package io.renren.modules.cms.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsChannelEntity;
import io.renren.modules.cms.service.CmsChannelService;

@RestController
@RequestMapping("/cms/channel")
public class CmsChannelController {

    @Autowired
    CmsChannelService cmsChannelService;
    
    @PostMapping("/insert")
    @RequiresPermissions("cms:channel:insert")
    public R insertCmsChannel(@RequestBody CmsChannelEntity entity) {
        return cmsChannelService.insertCmsChannel(entity);
    }
    
    @PostMapping("/delete")
    @RequiresPermissions("cms:channel:delete")
    public R deleteCmsChannel(@RequestBody Long[] ids) {
        return cmsChannelService.deleteCmsChannel(ids);
    }
    
    @PostMapping("/list")
    //@RequiresPermissions("cms:channel:list")
    public R listCmsChannel(@RequestBody Map<String,Object> params) {
        return cmsChannelService.listCmsChannel(params);
    }
    
    @PostMapping("/update")
    @RequiresPermissions("cms:channel:update")
    public R updateCmsChannel(@RequestBody CmsChannelEntity entity) {
        return cmsChannelService.updateCmsChannel(entity);
    }
    
    @PostMapping("/find")
    @RequiresPermissions("cms:channel:find")
    public R findCmsChannel(@RequestBody Long id) {
        return cmsChannelService.findCmsChannel(id);
    }
}
