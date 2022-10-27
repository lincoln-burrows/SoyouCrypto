package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.ExcelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelDataRepository extends JpaRepository<ExcelData, Integer> {


}