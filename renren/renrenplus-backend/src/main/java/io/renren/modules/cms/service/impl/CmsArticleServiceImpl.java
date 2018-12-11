package io.renren.modules.cms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.cms.dao.CmsArticleDAO;
import io.renren.modules.cms.dao.CmsChannelDAO;
import io.renren.modules.cms.dao.CmsContentDAO;
import io.renren.modules.cms.dao.CmsTypeDAO;
import io.renren.modules.cms.entity.CmsArticleEntity;
import io.renren.modules.cms.entity.CmsChannelEntity;
import io.renren.modules.cms.entity.CmsContentEntity;
import io.renren.modules.cms.entity.CmsTypeEntity;
import io.renren.modules.cms.service.CmsArticleService;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
@Service
public class CmsArticleServiceImpl extends ServiceImpl<CmsArticleDAO, CmsArticleEntity> implements CmsArticleService{

    @Autowired
    CmsChannelDAO cmsChannelDAO;
    
    @Autowired
    CmsTypeDAO cmsTypeDAO;
    
    @Autowired
    CmsContentDAO cmsContentDAO;
    
    @Autowired 
    SysUserDao sysUserDao;
    
    @Override
    public R insertCmsArticle(CmsArticleEntity entity) {
        R r=R.error();
        if(this.insert(entity)) {
            CmsContentEntity contentEntity=new CmsContentEntity();
            contentEntity.setContent(entity.getContent());
            entity.setId(entity.getId());
            int code=0;
            try {
                code=cmsContentDAO.insert(contentEntity);
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                if(code<=0) {
                    cmsContentDAO.updateById(contentEntity);
                }
            } catch (Exception e) {
            }
            r=R.ok();
        }
        return r;
    }

    @Override
    public R updateCmsArticle(CmsArticleEntity entity) {
        R r=R.error();
        if(this.updateById(entity)) {
            CmsContentEntity contentEntity=new CmsContentEntity();
            contentEntity.setContent(entity.getContent());
            entity.setId(entity.getId());
            try {
                cmsContentDAO.updateById(contentEntity);
            } catch (Exception e) {
            }
            r=R.ok();
        }
        return r;
    }

    @Override
    public R listCmsArticle(Map<String, Object> params) {
        R r=R.error();
        int page=1;
        int limit=10;
        try {
            page=(int) params.get("page");
        } catch (Exception e) {
        } finally {
            params.put("page", page);
        }
        
        try {
            limit=(int) params.get("limit");
        } catch (Exception e) {
        } finally {
            params.put("limit", limit);
            params.put("offset", (page-1)*limit);
        }
        
        try {
            Page<CmsArticleEntity> pageEntity=new Page<>(page, limit);
            List<CmsArticleEntity> list=baseMapper.listCmsArticle(params);
            pageEntity.setRecords(list);
            r=R.ok().put("page", new PageUtils(pageEntity));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public R findCmsArticle(CmsArticleEntity entity) {
        R r=R.error();
        try {
            CmsArticleEntity rs=this.selectById(entity.getId());
            rs.setContent(cmsContentDAO.selectById(rs.getId()).getContent());
            r=R.ok().put("data", rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
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
