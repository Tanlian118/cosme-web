package com.cosme.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tanlian
 * @create 2018-08-11 18:49
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsDTO implements Serializable {

    /**
     * 新闻id
     */
    Integer newsId;

    /**
     * 操作员id
     */
    String operaterId;

    /**
     * 新闻图片
     */
    String newsImage;

    /**
     * 新闻标题
     */
    String newsTitle;

    /**
     * 内容简介
     */
    String contentSummary;

    /**
     * 发布时间
     */
    Date releaseTime;

    /**
     * 内容
     */
    String content;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 更新时间
     */
    Date updateTime;
}
