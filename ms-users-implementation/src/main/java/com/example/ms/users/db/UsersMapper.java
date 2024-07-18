package com.example.ms.users.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.ms.users.def.model.User;

@Mapper
public interface UsersMapper {
	@Select("SELECT * FROM MS_USERS001_USERS WHERE USERNAME = #{username}")
	public User select(@Param("username") String username);

	@Insert("INSERT INTO MS_USERS001_USERS VALUES (#{user.username}, #{user.password}, #{user.email})")
	public int insert(@Param("user") User user);
}
