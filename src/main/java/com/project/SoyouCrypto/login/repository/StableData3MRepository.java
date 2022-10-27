package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.StableData3M;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StableData3MRepository extends JpaRepository<StableData3M, Integer> {
    Optional<StableData3M> findByDatetime(String time);



}