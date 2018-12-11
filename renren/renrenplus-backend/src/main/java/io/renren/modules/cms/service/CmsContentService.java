package io.renren.modules.cms.service;

import java.util.Map;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsContentEntity;

public interface CmsContentService {

    R insertCmsContent(CmsContentEntity entity);

    R deleteCmsContent(Long[] ids);

    R listCmsContent(Map<String, Object> params);

    R updateCmsContent(CmsContentEntity entity);

    R findCmsContent(Long id);

}
