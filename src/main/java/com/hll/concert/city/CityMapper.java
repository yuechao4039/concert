/**
 *    Copyright 2015-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.hll.concert.city;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Eddú Meléndez
 */
@Mapper
@Component(value = "cityMapper")
public interface CityMapper {

	@Select("select * from city where state = #{state}")
	City findByState(@Param("state") String state);

	@Select("select * from city")
	List<City> findAll();


	@Insert("insert into city (name, state, country) values (#{name}, #{state}, #{country})")
	int insert(City city);

	@Update("update city set name = #{name} where id = #{id}")
	int update(City city);

	@Delete("delete from city where id = #{id}")
	int delete(@Param("id") Long id);

}
