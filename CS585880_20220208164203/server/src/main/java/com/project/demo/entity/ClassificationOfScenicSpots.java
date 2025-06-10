package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *景点分类：(ClassificationOfScenicSpots)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ClassificationOfScenicSpots")
public class ClassificationOfScenicSpots implements Serializable {

    //ClassificationOfScenicSpots编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classification_of_scenic_spots_id")
    private Integer classification_of_scenic_spots_id;
    // 景点类别
    @Basic
    private String scenic_spot_category;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
