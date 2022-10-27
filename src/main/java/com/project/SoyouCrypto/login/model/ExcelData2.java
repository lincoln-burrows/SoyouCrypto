package com.project.SoyouCrypto.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
@Entity // DB 테이블 역할을 합니다.
@Table(name = "ExcelData2")
public class ExcelData2 {

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


}