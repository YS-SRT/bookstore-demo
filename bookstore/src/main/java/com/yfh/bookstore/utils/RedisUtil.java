package com.yfh.bookstore.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.yfh.bookstore.config.Setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisUtil {
    

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    

    /**
     * 获取一批指定前缀的key eg: key:* 获取所有key:开头的key
     *
     * @param pattern key匹配正则
     * @param count   一次获取数目
     * @return
     */
    public Set<String> scan(String pattern, int count) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(pattern)
                    .count(count).build())) {
                while (cursor.hasNext()) {
                    keysTmp.add(new String(cursor.next(), StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return keysTmp;
        });
    }

    /**
     * 批量删除
     *
     * @param pattern key匹配正则
     * @param step    阶梯删除的数目
     */
    public void multiDelete(String pattern, int step) {
        while (scan(pattern, step).size() > 0) {
            Set<String> keys = scan(pattern, step);
            redisTemplate.delete(keys);
        }
    }

    /**
     * 
     * @param <T>
     * @param map
     */
    public <T> void multiSetValues(Map<String, T> map){
        if(map!= null && !map.isEmpty()){
            redisTemplate.opsForValue().multiSet(map);
        }
    }


    /**
     * 设置bitmap状态
     * 
     * @param key
     * @param offset
     * @param value
     */
    public void setBitMapStatus(String key, int offset, boolean value) {
        redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 统计bitmap
     * 
     * @param key
     * @return
     */
    public Long getBitMapCount(String key) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }




     /**
      * 添加地理点位
      * 
      * @param key    名称
      * @param member 成员
      * @param x      经度
      * @param y      纬度
      */
    public void addGeo(String key, Object member, double x, double y) {
        redisTemplate.opsForGeo().add(key, new Point(x, y), member);
    }
    
    /**
     * 计算成员之间的地理距离（m）
     *
     * @param key
     * @param member1
     * @param member2
     * @return
     */
    public double getGeoDistance(String key, Object member1, Object member2) {
        Distance distance = redisTemplate.opsForGeo().distance(key, member1, member2, RedisGeoCommands.DistanceUnit.METERS);
        return distance.getValue();
    }
    
    /**
     * 获取周边成员
     *
     * @param key
     * @param member
     * @param distance （m）
     * @return
     */
    public List<Map<String, Object>> getGeoCircum(String key, Object member, double distance) {
        // 获取中心坐标
        List<Point> positions = redisTemplate.opsForGeo().position(key, member);
        List<Map<String, Object>> memberList = new ArrayList<>();
        if (CollectionUtils.isEmpty(positions)) {
            return memberList;
        }
        Point point = positions.stream().findFirst().get();
        Circle circle = new Circle(point, new Distance(distance, Metrics.MILES));
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = redisTemplate.opsForGeo()
                .radius(key, circle, args);
        for (GeoResult<RedisGeoCommands.GeoLocation<Object>> result : results) {
            RedisGeoCommands.GeoLocation<Object> content = result.getContent();
            Object name = content.getName();
            Point memberPoint = content.getPoint();
            Distance memberDistance = result.getDistance();
            // 为了展示这些api的使用，我将返回值包装成map
            Map<String, Object> memberMap = new HashMap<>();
            memberMap.put("name", name);
            memberMap.put("lng", memberPoint.getX());
            memberMap.put("lat", memberPoint.getY());
            memberMap.put("distance", memberDistance.getValue());
            memberList.add(memberMap);
        }
        return memberList;
    }




    public void sendMQMsg(String topic, String msg) {
        redisTemplate.opsForList().leftPush(topic, msg);
        //redisTemplate.opsForList().rightPush(topic, msg);
    }
    public String receiveMQMsg(String topic){
        return redisTemplate.opsForList().rightPop(topic, 1, TimeUnit.SECONDS).toString();
        //return redisTemplate.opsForList().leftPop(topic, 1, TimeUnit.SECONDS).toString();
    }



    //*************** Testing Pub/Sub **********************
    public void sendChannelMsg(String channel, String msg) {
        redisTemplate.convertAndSend(channel, msg);
    }
    public void handleChannelMessage(String msg){
       log.info("received channel message is {}", msg);
    }
     /**
     * 注册消息监听
     * RedisConfig.java
     */
   
    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * RedisConfig.java
     */
    





  
}
