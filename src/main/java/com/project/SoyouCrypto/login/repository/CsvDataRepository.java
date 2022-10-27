package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.CsvData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvDataRepository extends JpaRepository<CsvData, Integer> {


}