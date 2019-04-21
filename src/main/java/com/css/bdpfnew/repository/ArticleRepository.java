package com.css.bdpfnew.repository;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


public interface ArticleRepository {
    /**
     * 新增文章
     *
     * @param jsonObject
     * @return
     */
    int addArticle(JSONObject jsonObject);

    /**
     * 统计文章总数
     *
     * @param jsonObject
     * @return
     */
    int countArticle(JSONObject jsonObject);

    /**
     * 文章列表
     *
     * @param jsonObject
     * @return
     */
    List<JSONObject> listArticle(JSONObject jsonObject);

    /**
     * 更新文章
     *
     * @param jsonObject
     * @return
     */
    int updateArticle(JSONObject jsonObject);
}
