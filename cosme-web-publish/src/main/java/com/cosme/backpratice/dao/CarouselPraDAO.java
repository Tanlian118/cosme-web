package com.cosme.backpratice.dao;

import com.cosme.entity.CarouselEntity;
import com.cosme.param.CarouselQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-16 14:42
 **/
public interface CarouselPraDAO {


    @Insert({"INSERT INTO cosme_carousel_image (operater_id, carousel_image, weight, link_url)",
            "VALUES(#{entity.operaterId}, #{entity.carouselImage}, #{entity.weight},",
            "#{entity.linkUrl})"
    })
    int save(@Param("entity") CarouselEntity carouselEntity);


    @Update({"UPDATE cosme_carousel_image SET carousel_image= #{entity.carouselImage},",
            "weight= #{entity.weight}, link_url= #{entity.linkUrl} ",
            "WHERE cosme_image_id= #{entity.cosmeImageId}"
    })
    int update(@Param("entity") CarouselEntity carouselEntity);

    @Update({"<script>",
            "UPDATE cosme_carousel_image SET status= -1",
            "WHERE cosme_image_id IN",
            "<foreach item='cosmeImageId' collection='cosmeImageIds' open='(' separator=',' close=')'>",
            "#{cosmeImageId}",
            "</foreach>",
            "</script>"
    })
    int delete(@Param("cosmeImageIds") Set<Integer> cosmeImageIds);

    @Select({"<script>",
            "SELECT cosme_image_id,  operater_id, carousel_image, weight, link_url, update_time",
            "FROM cosme_carousel_image",
            "WHERE status = 1 AND weight != 0",
            "<if test='cosmeImageIds != null and cosmeImageIds.size() >0'>",
            "<foreach test='cosmeImageId' collection='cosmeImageIds' open='AND cosme_image_id IN(' separator=',' close=')'>",
            "#{cosmeImageId}",
            "</foreach>",
            "</if>",
            "ORDER BY weight DESC",
            "<if test='needPagination == true'>",
            "LIMIT #{page},#{pageSize}",
            "</if>",
            "</script>"
    })
    List<CarouselEntity> list(CarouselQueryParam carouselQueryParam);

    @Select({"<script>",
            "SELECT count(*)",
            "FROM cosme_carousel_image",
            "WHERE status = 1 AND weight != 0",
            "<if test='cosmeImageIds != null and cosmeImageIds.size() >0'>",
            "<foreach item='id' collection='cosmeImageIds' open='AND cosme_image_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "</script>"
    })
    int count(CarouselQueryParam queryParam);
}
