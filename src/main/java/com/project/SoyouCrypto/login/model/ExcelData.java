package com.project.SoyouCrypto.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
@Entity // DB 테이블 역할을 합니다.
@Table(name = "ExcelData")
public class ExcelData {

    @Id
    @Column(nullable = true)
    private String date;

    @Column(nullable = true)
    private String terminal_price;

    @Column(nullable = true)
    private String opening_price;

    @Column(nullable = true)
    private String transactions;

    @Column(nullable = true)
    private String changes;

//    @Column(nullable = true)
//    private String plant_humid;
//
//    @Column(nullable = true)
//    private String plant_level_code12;
//
//    @Column(nullable = true)
//    private String plant_place_code;
//
//    @Column(nullable = true)
//    private String plant_type_code;
//
//    @Column(nullable = true)
//    private String water_cycle_spring_code;
//
//    @Column(nullable = true)
//    private String water_cycle_summer_code;
//
//    @Column(nullable = true)
//    private String water_cycle_autumn_code;
//
//    @Column(nullable = true)
//    private String water_cycle_winter_code;

}