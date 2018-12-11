package io.renren.modules.cms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.R;
import io.renren.modules.cms.dao.CmsChannelDAO;
import io.renren.modules.cms.entity.CmsChannelEntity;
import io.renren.modules.cms.service.CmsChannelService;
@Service
public class CmsChannelServiceImpl extends ServiceImpl<CmsChannelDAO, CmsChannelEntity> implements CmsChannelService{

    @Override
    public R insertCmsChannel(CmsChannelEntity entity) {
        R r=R.error();
        if(this.insert(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R deleteCmsChannel(Long[] ids) {
        List<Long> list=new ArrayList<>();
        for(Long id:ids) {
            list.add(id);
        }
        this.deleteBatchIds(list);
        return R.ok();
    }

    @Override
    public R listCmsChannel(Map<String, Object> params) {
        return R.ok().put("data", this.selectList(null));
    }

    @Override
    public R updateCmsChannel(CmsChannelEntity entity) {
        R r=R.error();
        if(this.updateById(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R findCmsChannel(Long id) {
        R r=R.error();
        try {
            CmsChannelEntity rs=this.selectById(id);
            r=R.ok().put("data", rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

}
