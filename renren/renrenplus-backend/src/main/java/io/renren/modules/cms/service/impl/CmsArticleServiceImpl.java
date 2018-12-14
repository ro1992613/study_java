package io.renren.modules.cms.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.cms.dao.CmsArticleDAO;
import io.renren.modules.cms.dao.CmsChannelDAO;
import io.renren.modules.cms.dao.CmsContentDAO;
import io.renren.modules.cms.dao.CmsTypeDAO;
import io.renren.modules.cms.entity.CmsArticleEntity;
import io.renren.modules.cms.entity.CmsContentEntity;
import io.renren.modules.cms.service.CmsArticleService;
import io.renren.modules.sys.dao.SysUserDao;
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
        entity.setAuthorId(ShiroUtils.getUserId());
        entity.setPublishDate(new Date());
        R r=R.error();
        if(baseMapper.insertArticle(entity)>0) {
            CmsContentEntity contentEntity=new CmsContentEntity();
            contentEntity.setContent(entity.getContent());
            contentEntity.setArticleId(entity.getId());
            int code=0;
            try {
                code=cmsContentDAO.insert(contentEntity);
            } catch (Exception e) {
                e.printStackTrace();
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
            contentEntity.setArticleId(entity.getId());
            int code=0;
            try {
                code=cmsContentDAO.updateById(contentEntity);
                System.out.println(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if(code<=0) {
                    cmsContentDAO.insert(contentEntity);
                }
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
            try {
                CmsContentEntity queryEtity=new CmsContentEntity();
                queryEtity.setArticleId(entity.getId());
                rs.setContent(cmsContentDAO.selectOne(queryEtity).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            r=R.ok().put("data", rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public R deleteCmsArticle(Long[] ids) {
        R r=R.error();
        try {
            this.deleteBatchIds(Arrays.asList(ids));
            cmsContentDAO.deleteBatchIds(Arrays.asList(ids));
            r=R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
