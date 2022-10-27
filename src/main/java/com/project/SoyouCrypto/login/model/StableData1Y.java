package com.project.SoyouCrypto.login.model;

import com.opencsv.bean.CsvBindByName;
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
@Table(name = "StableData1Y")
public class StableData1Y {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @CsvBindByName
    @Column
    private String datetime;

    @CsvBindByName
    @Column
    private double stable_only;

    @Column(nullable = true)
    private double cum_return_ma;

    @Column(nullable = true)
    private double daily_return_ma;

    @Column(nullable = true)
    private double ATH;

    @Column(nullable = true)
    private double DD;


    public void updateTodayData(double cum_return_ma, double daily_return_ma, double ATH, double DD) {
        this.cum_return_ma = cum_return_ma;
        this.daily_return_ma = daily_return_ma;
        this.ATH = ATH;
        this.DD = DD;
        System.out.println("업데이트하기");
    }
    }