package io.renren.modules.cms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.R;
import io.renren.modules.cms.dao.CmsTypeDAO;
import io.renren.modules.cms.entity.CmsTypeEntity;
import io.renren.modules.cms.service.CmsTypeService;
@Service
public class CmsTypeServiceImpl extends ServiceImpl<CmsTypeDAO, CmsTypeEntity> implements CmsTypeService{

    @Override
    public R insertCmsType(CmsTypeEntity entity) {
        R r=R.error();
        if(this.insert(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R deleteCmsType(Long[] ids) {
        List<Long> list=new ArrayList<>();
        for(Long id:ids) {
            list.add(id);
        }
        this.deleteBatchIds(list);
        return R.ok();
    }

    @Override
    public R listCmsType(Map<String, Object> params) {
        return R.ok().put("data", this.selectList(null));
    }

    @Override
    public R updateCmsType(CmsTypeEntity entity) {
        R r=R.error();
        if(this.updateById(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R findCmsType(Long id) {
        R r=R.error();
        try {
            CmsTypeEntity rs=this.selectById(id);
            r=R.ok().put("data", rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

}
