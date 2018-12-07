package io.renren.modules.cms.service;

import java.util.Map;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsArticleEntity;

public interface CmsArticleService {

    R insertCmsArticle(CmsArticleEntity entity);

    R updateCmsArticle(CmsArticleEntity entity);

    R listCmsArticle(Map<String, Object> params);

    R findCmsArticle(CmsArticleEntity entity);

    R deleteCmsArticle(Long[] id);

}
