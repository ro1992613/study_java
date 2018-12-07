package io.renren.modules.cms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsArticleEntity;
import io.renren.modules.cms.service.CmsArticleService;

public class CmsArticleController {

    @Autowired
    CmsArticleService cmsArticleService;
    
    public R insertCmsArticle(CmsArticleEntity entity) {
        return cmsArticleService.insertCmsArticle(entity);
    }
    
    public R updateCmsArticle(CmsArticleEntity entity) {
        return cmsArticleService.updateCmsArticle(entity);
    }
    
    public R listCmsArticle(Map<String,Object> params) {
        return cmsArticleService.listCmsArticle(params);
    }
    
    public R findCmsArticle(CmsArticleEntity entity) {
        return cmsArticleService.findCmsArticle(entity);
    }
    
    public R deleteCmsArticle(Long[] id) {
        return cmsArticleService.deleteCmsArticle(id);
    }
}
