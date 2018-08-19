package com.cosme.back.dao;

import com.cosme.entity.UserEntity;
import com.cosme.param.UserQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-15 13:50
 **/
public interface UserDAO {

    @Insert({"INSERT INTO cosme_user(user_id, username, password, public_key)",
            "VALUES(#{entity.userId}, #{entity.username}, #{entity.password}, #{entity.publicKey})"})
    int save(@Param("entity") UserEntity userEntity);


    @Select({"<script>",
            "SELECT user_id, username, password, gender, contact,  birthday, public_key",
            "FROM cosme_user",
            "WHERE status = 1 ",
            "<if test='usernames != null and usernames.size() >0'>",
            "<foreach item='id' collection='usernames' open='AND username IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "</script>"})
    List<UserEntity> queryByParam(UserQueryParam userQueryParam);
}
