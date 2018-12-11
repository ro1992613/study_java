package io.renren.modules.cms.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsContentEntity;
import io.renren.modules.cms.service.CmsContentService;

@RestController
@RequestMapping("/cms/content")
public class CmsContentController {

    @Autowired
    CmsContentService cmsContentService;
    
    @PostMapping("/insert")
    @RequiresPermissions("cms:content:insert")
    public R insertCmsContent(@RequestBody CmsContentEntity entity) {
        return cmsContentService.insertCmsContent(entity);
    }
    
    @PostMapping("/delete")
    @RequiresPermissions("cms:content:delete")
    public R deleteCmsContent(@RequestBody Long[] ids) {
        return cmsContentService.deleteCmsContent(ids);
    }
    
    @PostMapping("/list")
    @RequiresPermissions("cms:content:list")
    public R listCmsContent(@RequestBody Map<String,Object> params) {
        return cmsContentService.listCmsContent(params);
    }
    
    @PostMapping("/update")
    @RequiresPermissions("cms:content:update")
    public R updateCmsContent(@RequestBody CmsContentEntity entity) {
        return cmsContentService.updateCmsContent(entity);
    }
    
    @PostMapping("/find")
    @RequiresPermissions("cms:content:find")
    public R findCmsContent(@RequestBody Long id) {
        return cmsContentService.findCmsContent(id);
    }
}
