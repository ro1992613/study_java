package io.renren.modules.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.renren.modules.cms.entity.CmsArticleEntity;
@Mapper
public interface CmsArticleDAO extends BaseMapper<CmsArticleEntity>{

    List<CmsArticleEntity> listCmsArticle(Map<String, Object> params);

    int insertArticle(CmsArticleEntity cmsArticleEntity);
}