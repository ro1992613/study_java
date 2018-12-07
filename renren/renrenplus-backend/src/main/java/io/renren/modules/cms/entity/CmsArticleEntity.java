package io.renren.modules.cms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * cms_article
 * @author 
 */
public class CmsArticleEntity implements Serializable {
    /**
     * 文章id
     */
    private Long id;

    /**
     * 栏目id
     */
    private Long channelId;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布日期
     */
    private Date publishDate;

    /**
     * 简介
     */
    private String summary;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 状态(0:草稿;1:审核中;2:发布;3:回收站;-1:删除)
     */
    private Integer status;

    /**
     * 访问数
     */
    private Integer viewsCount;

    /**
     * 评论数
     */
    private Integer commentsCount;

    /**
     * 关键词，用逗号隔开
     */
    private String keywords;

    /**
     * 特征（保留）
     */
    private String feature;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CmsArticleEntity other = (CmsArticleEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getChannelId() == null ? other.getChannelId() == null : this.getChannelId().equals(other.getChannelId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getPublishDate() == null ? other.getPublishDate() == null : this.getPublishDate().equals(other.getPublishDate()))
            && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()))
            && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getViewsCount() == null ? other.getViewsCount() == null : this.getViewsCount().equals(other.getViewsCount()))
            && (this.getCommentsCount() == null ? other.getCommentsCount() == null : this.getCommentsCount().equals(other.getCommentsCount()))
            && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()))
            && (this.getFeature() == null ? other.getFeature() == null : this.getFeature().equals(other.getFeature()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getChannelId() == null) ? 0 : getChannelId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPublishDate() == null) ? 0 : getPublishDate().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getViewsCount() == null) ? 0 : getViewsCount().hashCode());
        result = prime * result + ((getCommentsCount() == null) ? 0 : getCommentsCount().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        result = prime * result + ((getFeature() == null) ? 0 : getFeature().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", channelId=").append(channelId);
        sb.append(", typeId=").append(typeId);
        sb.append(", title=").append(title);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", summary=").append(summary);
        sb.append(", authorId=").append(authorId);
        sb.append(", status=").append(status);
        sb.append(", viewsCount=").append(viewsCount);
        sb.append(", commentsCount=").append(commentsCount);
        sb.append(", keywords=").append(keywords);
        sb.append(", feature=").append(feature);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}