package com.cosme.back.dao;

import com.cosme.entity.ProductEntity;
import com.cosme.param.ProductQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-13 13:48
 **/
public interface ProductDAO {

    @Insert({"INSERT INTO cosme_product(channel_id, operater_id,main_image, product_title, japanese_title,",
            "product_channel, effect, weight, bar_code) ",
            "VALUES(#{entity.channelId}, #{entity.operaterId}, #{entity.mainImage}, #{entity.productTitle},",
            "#{entity.japaneseTitle}, #{entity.productChannel}, #{entity.effect}, #{entity.weight},#{entity.barCode})"})
    int save(@Param("entity") ProductEntity productEntity);


    @Update({"UPDATE cosme_product ",
            "SET main_image= #{entity.mainImage}, product_title= #{entity.productTitle},",
            "japanese_title= #{entity.japaneseTitle},product_channel= #{entity.productChannel},",
            "effect= #{entity.effect},weight= #{entity.weight}, bar_code= #{entity.barCode}",
            "WHERE product_id= #{productId}"})
    int update(@Param("entity") ProductEntity productEntity);

    @Update({"<script>",
            "UPDATE cosme_product SET status= -1",
            "WHERE product_id IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    int delete(@Param("ids") Set<Integer> productIds);

    @Select({"<script>",
            "SELECT product_id, channel_id, operater_id, main_image, product_title, japanese_title,",
            "product_channel, effect, weight, bar_code, update_time",
            "FROM cosme_product",
            "WHERE status = 1 AND weight != 0",
            "<if test='productIds != null and productIds.size() >0'>",
            "<foreach item='productId' collection='productIds' open='AND product_id IN(' separator=',' close=')'>",
            "#{productId}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size() >0'>",
            "<foreach item='operaterId' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{operaterId}",
            "</foreach>",
            "</if>",
            "<if test='weights != null and weights.size >0'>",
            "<foreach item='weight' collection='weights' open='AND weight IN(' separator=',' close=')'>",
            "#{weight}",
            "</foreach>",
            "</if>",
            "<if test='channelIds != null and channelIds.size >0'>",
            "<foreach item='channelId' collection='channelIds' open='AND channel_id IN(' separator=',' close=')'>",
            "#{channelId}",
            "</foreach>",
            "</if>",
            "<if test='productTitle != null'>",
            "AND product_title LIKE CONCAT('%',#{productTitle},'%')",
            "</if>",
            "ORDER BY weight DESC",
            "<if test='needPagination == true'>",
            "LIMIT #{page},#{pageSize}",
            "</if>",
            "</script>"})
    List<ProductEntity> queryByParam(ProductQueryParam queryParam);

    @Select({"<script>",
            "SELECT count(*)",
            "FROM cosme_product",
            "WHERE status = 1 AND weight != 0",
            "<if test='productIds != null and productIds.size() >0'>",
            "<foreach item='id' collection='productIds' open='AND product_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size() >0'>",
            "<foreach item='id' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='weights != null and weights.size >0'>",
            "<foreach item='id' collection='weights' open='AND weight IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='channelIds != null and channelIds.size >0'>",
            "<foreach item='id' collection='channelIds' open='AND channel_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='productTitle != null '>",
            "AND product_title LIKE CONCAT('%',#{productTitle},'%')",
            "</if>",
            "</script>"})
     int count (ProductQueryParam queryParam);


}
