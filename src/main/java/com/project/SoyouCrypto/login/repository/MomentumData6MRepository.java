package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.MomentumData6M;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MomentumData6MRepository extends JpaRepository<MomentumData6M, Integer> {
    Optional<MomentumData6M> findByTime(String time);



}