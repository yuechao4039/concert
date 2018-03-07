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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CityMapper}.
 * @author wonwoo
 * @since 1.2.1
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityMapperTest {

  @Autowired
  private CityMapper cityMapper;

  @Test
  public void findByStateTest() {
    City city = cityMapper.findByState("CA");
    assertThat(city.getName()).isEqualTo("San Francisco");
    assertThat(city.getState()).isEqualTo("CA");
    assertThat(city.getCountry()).isEqualTo("US");
  }

  @Test
  @Rollback(false)
  public void insertTest() {
    City city = new City();
    city.setName("Shanghai");
    city.setState("CA");
    city.setCountry("China");
    int result = this.cityMapper.insert(city);
    Assert.assertEquals(1, result);
  }

  @Test
  @Rollback(false)
  public void updateTest() {
    City city = new City();
    city.setId(3L);
    city.setName("Beijing");
    int result = this.cityMapper.update(city);
    Assert.assertEquals(1, result);
  }

  @Test
  @Rollback(false)
  public void deleteTest() {
    int result = this.cityMapper.delete(4L);
    Assert.assertEquals(1, result);
  }
}
