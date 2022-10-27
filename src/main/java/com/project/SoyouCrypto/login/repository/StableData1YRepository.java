package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.StableData1Y;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StableData1YRepository extends JpaRepository<StableData1Y, Integer> {
    Optional<StableData1Y> findByDatetime(String time);



}