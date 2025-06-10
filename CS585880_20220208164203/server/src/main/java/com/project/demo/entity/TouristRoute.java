package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *旅游路线：(TouristRoute)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TouristRoute")
public class TouristRoute implements Serializable {

    //TouristRoute编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourist_route_id")
    private Integer tourist_route_id;
    // 路线行程
    @Basic
    private String route_travel;
    // 景点类别
    @Basic
    private String scenic_spot_category;
    // 天数
    @Basic
    private String days;
    // 封面
    @Basic
    private String cover;
    // 价格
    @Basic
    private Integer price;
    // 费用包含
    @Basic
    private String cost_includes;
    // 交通攻略
    @Basic
    private String traffic_strategy;
    // 住宿攻略
    @Basic
    private String accommodation_strategy;
    // 美食攻略
    @Basic
    private String food_introduction;
    // 路线介绍
    @Basic
    private String route_introduction;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
