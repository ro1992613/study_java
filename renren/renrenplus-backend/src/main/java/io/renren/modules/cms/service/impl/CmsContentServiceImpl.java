package io.renren.modules.cms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.R;
import io.renren.modules.cms.dao.CmsContentDAO;
import io.renren.modules.cms.entity.CmsContentEntity;
import io.renren.modules.cms.service.CmsContentService;
@Service
public class CmsContentServiceImpl extends ServiceImpl<CmsContentDAO, CmsContentEntity> implements CmsContentService{

    @Override
    public R insertCmsContent(CmsContentEntity entity) {
        R r=R.error();
        if(this.insert(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R deleteCmsContent(Long[] ids) {
        List<Long> list=new ArrayList<>();
        for(Long id:ids) {
            list.add(id);
        }
        this.deleteBatchIds(list);
        return R.ok();
    }

    @Override
    public R listCmsContent(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public R updateCmsContent(CmsContentEntity entity) {
        R r=R.error();
        if(this.updateById(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R findCmsContent(Long id) {
        R r=R.error();
        try {
            CmsContentEntity rs=this.selectById(id);
            r=R.ok().put("data", rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

}
