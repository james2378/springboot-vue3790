package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *酒店管理：(HotelManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HotelManagement")
public class HotelManagement implements Serializable {

    //HotelManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_management_id")
    private Integer hotel_management_id;
    // 酒店编号
    @Basic
    private String hotel_number;
    // 酒店名称
    @Basic
    private String hotel_name;
    // 房型
    @Basic
    private String layout_of_a_house_or_an_apartment;
    // 价格
    @Basic
    private Integer price;
    // 照片
    @Basic
    private String photo;
    // 地址
    @Basic
    private String address;
    // 便利设施
    @Basic
    private String amenities;
    // 交通指南
    @Basic
    private String traffic_guide;
    // 酒店介绍
    @Basic
    private String hotel_introduction;
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
