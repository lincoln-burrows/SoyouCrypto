package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.StableData6M;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StableData6MRepository extends JpaRepository<StableData6M, Integer> {
    Optional<StableData6M> findByDatetime(String time);



}