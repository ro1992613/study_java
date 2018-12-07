package io.renren.modules.cms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.cms.dao.CmsArticleDAO;
import io.renren.modules.cms.entity.CmsArticleEntity;
import io.renren.modules.cms.service.CmsArticleService;
@Service
public class CmsArticleServiceImpl extends ServiceImpl<CmsArticleDAO, CmsArticleEntity> implements CmsArticleService{

    @Override
    public R insertCmsArticle(CmsArticleEntity entity) {
        R r=R.error();
        if(this.insert(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R updateCmsArticle(CmsArticleEntity entity) {
        R r=R.error();
        if(this.updateById(entity)) {
            r=R.ok();
        }
        return r;
    }

    @Override
    public R listCmsArticle(Map<String, Object> params) {
        String title = (String)params.get("title");
        Long channelId = (Long)params.get("channelId");
        Long typeId = (Long)params.get("typeId");
        Integer status=(Integer) params.get("status");
        String keywords=(String)params.get("keywords");
        
        Long authorId = (Long)params.get("authorId");
        String feature=(String)params.get("feature");

        Page<CmsArticleEntity> page = this.selectPage(
            new Query<CmsArticleEntity>(params).getPage(),
            new EntityWrapper<CmsArticleEntity>()
                .like(StringUtils.isNotBlank(title),"title", title)
                .eq(channelId != null,"create_user_id", channelId)
        );
        return null;
    }

    @Override
    public R findCmsArticle(CmsArticleEntity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public R deleteCmsArticle(Long[] ids) {
        List<Long> list=new ArrayList<>();
        for(Long id:ids) {
            list.add(id);
        }
        this.deleteBatchIds(list);
        return R.ok();
    }


}
