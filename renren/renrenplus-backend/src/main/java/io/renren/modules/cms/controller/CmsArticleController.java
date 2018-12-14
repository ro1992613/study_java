package io.renren.modules.cms.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsArticleEntity;
import io.renren.modules.cms.service.CmsArticleService;

@RestController
@RequestMapping("/cms/article")
public class CmsArticleController {

    @Autowired
    CmsArticleService cmsArticleService;
    
    @PostMapping("/insert")
    //@RequiresPermissions("cms:article:insert")
    public R insertCmsArticle(@RequestBody CmsArticleEntity entity) {
        return cmsArticleService.insertCmsArticle(entity);
    }
    
    @PostMapping("/update")
    //@RequiresPermissions("cms:article:update")
    public R updateCmsArticle(@RequestBody CmsArticleEntity entity) {
        return cmsArticleService.updateCmsArticle(entity);
    }
    
    @PostMapping("/list")
    @RequiresPermissions("cms:article:list")
    public R listCmsArticle(@RequestBody Map<String,Object> params) {
        return cmsArticleService.listCmsArticle(params);
    }
    
    @PostMapping("/find")
    //@RequiresPermissions("cms:article:find")
    public R findCmsArticle(@RequestBody CmsArticleEntity entity) {
        return cmsArticleService.findCmsArticle(entity);
    }
    
    @PostMapping("/delete")
    //@RequiresPermissions("cms:article:delete")
    public R deleteCmsArticle(@RequestBody Long[] ids) {
        return cmsArticleService.deleteCmsArticle(ids);
    }
}
