// package com.yfh.bookstore.utils;


// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import javax.annotation.Resource;

// import com.yfh.bookstore.config.Setting;

// import org.junit.jupiter.api.Test;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.redis.core.RedisTemplate;

// import cn.hutool.core.date.DateUtil;
// import cn.hutool.core.date.TimeInterval;

// @SpringBootTest
// public class RedisUtilTest {

//     @Autowired
//     RedisUtil redisUtil;

//     @Resource
//     private RedisTemplate<String, Object> redisTemplate;


//     private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtilTest.class);
    
//     @Test
//     public void multiSetValuesTest() {
//         TimeInterval timer = DateUtil.timer();
//         Map<String, String> map = new HashMap<>();
//         for (int i = 0; i < 100; i++) {
//             map.put("mock" + i, String.valueOf(i));
//         }
//         redisUtil.multiSetValues(map);
//         LOGGER.info("耗时：{}ms", timer.interval());
//     }

//     @Test
//     public void multiDeleteTest() {
//         TimeInterval timer = DateUtil.timer();
//         redisUtil.multiDelete("mock:*", 100);
//         LOGGER.info("耗时：{}ms", timer.interval());
//     }



//     @Test
//     public void setBitMapStatusTest() {
//         // 100个人
//         for (int i = 0; i < 100; i++) {
//             // 设置偶数在线 奇数不在线
//             redisUtil.setBitMapStatus(Setting.ONLINE_STATUS, i, i % 2 == 0);
//         }
//     }

//     @Test
//     public void getBitMapCountTest() {
//         Long i = redisUtil.getBitMapCount(Setting.ONLINE_STATUS);
//         LOGGER.info("oline count = {}", i);

//     }



//     @Test
//     public void hllTest() {
//         TimeInterval timer = DateUtil.timer();
//         String key = "pv_hll:20220329";
//         // 模拟1000次操作
//         for (int i = 1; i < 1000; i++) {
//             redisTemplate.opsForHyperLogLog().add(key, i);
//         }
//         Long size = redisTemplate.opsForHyperLogLog().size(key);
//         LOGGER.info("size = {}, 耗时= {}ms", size, timer.interval());
//         // 操作999次返回996
//     }

//     @Test
//     public void setTest() {
//         TimeInterval timer = DateUtil.timer();
//         String key = "pv_set:20220329";
//         // 模拟1000次操作
//         for (int i = 1; i < 1000; i++) {
//             redisTemplate.opsForSet().add(key, i);
//         }
//         Long size = redisTemplate.opsForSet().size(key);
//         LOGGER.info("size = {}, 耗时= {}ms", size, timer.interval());
//         // 操作999次返回999
//     }


//     @Test
//     public void addGeoTest() {
//         // 添加一些城市点位
//         redisUtil.addGeo(Setting.GEO_CITY, "北京", 116.405285, 39.904989);
//         redisUtil.addGeo(Setting.GEO_CITY, "武汉", 114.311582, 30.598467);
//         redisUtil.addGeo(Setting.GEO_CITY, "郑州", 113.631419, 34.753439);
//         redisUtil.addGeo(Setting.GEO_CITY, "广州", 113.271431, 23.135336);
//         redisUtil.addGeo(Setting.GEO_CITY, "南宁", 108.373451, 22.822607);
//     }

//     @Test
//     public void getGeoDistanceTest() {
//         // 北京到武汉的距离
//         double distance = redisUtil.getGeoDistance(Setting.GEO_CITY, "北京", "武汉");
//         LOGGER.info("distance = {}m", distance);
//     }

//     @Test
//     public void getGeoCircumTest() {
//         // 北京周边1000km的城市
//         List<Map<String, Object>> circumCity = redisUtil.getGeoCircum(Setting.GEO_CITY, "北京", 1000000);
//         LOGGER.info("circum city = {}", circumCity);
//     }

    
//     @Test
//     public void sendChannelMsgTest(){
//        redisUtil.sendChannelMsg(Setting.CHANNEL_TEST, "你好啊！ChannelTest");
//     }

// }
