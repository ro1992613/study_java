package io.renren.modules.cms.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsTypeEntity;
import io.renren.modules.cms.service.CmsTypeService;

@RestController
@RequestMapping("/cms/type")
public class CmsTypeController {

    @Autowired
    CmsTypeService cmsTypeService;
    
    @PostMapping("/insert")
    @RequiresPermissions("cms:type:insert")
    public R insertCmsType(@RequestBody CmsTypeEntity entity) {
        return cmsTypeService.insertCmsType(entity);
    }
    
    @PostMapping("/delete")
    //@RequiresPermissions("cms:type:delete")
    public R deleteCmsType(@RequestBody Long[] ids) {
        return cmsTypeService.deleteCmsType(ids);
    }
    
    @PostMapping("/list")
   // @RequiresPermissions("cms:type:list")
    public R listCmsType(@RequestBody Map<String,Object> params) {
        return cmsTypeService.listCmsType(params);
    }
    
    @PostMapping("/update")
    @RequiresPermissions("cms:type:update")
    public R updateCmsType(@RequestBody CmsTypeEntity entity) {
        return cmsTypeService.updateCmsType(entity);
    }
    
    @PostMapping("/find")
    @RequiresPermissions("cms:type:find")
    public R findCmsType(@RequestBody Long id) {
        return cmsTypeService.findCmsType(id);
    }
}
