package com.project.SoyouCrypto.login.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
@Entity // DB 테이블 역할을 합니다.
@Table(name = "CsvData")
public class CsvData {

    @Id
    @CsvBindByName
    private String date;

    @CsvBindByName
    private String opening_price;

    @CsvBindByName
    private String terminal_price;




//    @CsvBindByName(column = "country")
//    private String countryCode;
//
//    @CsvBindByName
//    private int age;

//    public User(long id, String name, String email, String countryCode, int age) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.countryCode = countryCode;
//        this.age = age;
    }