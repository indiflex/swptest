package com.jade.swp.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface SampleMapper {
	@Select("select now()")
	public String getTime();
	
	@Select("select uname from User where uid = #{uid}")
	public String getUserName(@Param("uid") String uid);
	
	public String getUserSessionLimit(@Param("uid") String uid);
	
	@SelectProvider(type=SampleProvider.class, method="searchUser")
	public String search(@Param("searchCol") String searchCol, @Param("searchStr") String searchStr);
}
