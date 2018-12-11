package io.renren.modules.cms.service;

import java.util.Map;

import io.renren.common.utils.R;
import io.renren.modules.cms.entity.CmsChannelEntity;

public interface CmsChannelService {

    R insertCmsChannel(CmsChannelEntity entity);

    R deleteCmsChannel(Long[] ids);

    R listCmsChannel(Map<String, Object> params);

    R updateCmsChannel(CmsChannelEntity entity);

    R findCmsChannel(Long id);

}
