package com.project.SoyouCrypto.login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Table(name = "OutputIndex")
public class OutputIndex {

    // ID가 자동으로 생성 및 증가합니다.

    @Id
    @Column(name = "index_type")
    private String indexType;

    @Column
    private double cumReturn;

    @Column
    private double dailyAvg;

    @Column
    private double dailySharp;

    @Column
    private double mdd;


    public OutputIndex(String indexType, double cumReturn, double dailyAvg, double dailySharp, double mdd) {
        this.indexType = indexType;
        this.cumReturn = cumReturn;
        this.dailyAvg = dailyAvg;
        this.dailySharp = dailySharp;
        this.mdd = mdd;
    }
    }