package com.cosme.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author Tanlian
 * @create 2018-08-11 18:49
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsAddRequest {

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
}
