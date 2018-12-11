package io.renren.modules.cms.service;

import java.util.Map;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsTypeEntity;

public interface CmsTypeService {

    R insertCmsType(CmsTypeEntity entity);

    R deleteCmsType(Long[] ids);

    R listCmsType(Map<String, Object> params);

    R updateCmsType(CmsTypeEntity entity);

    R findCmsType(Long id);

}
