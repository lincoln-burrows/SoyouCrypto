package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.MomentumData3M;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MomentumData3MRepository extends JpaRepository<MomentumData3M, Integer> {
    Optional<MomentumData3M> findByTime(String time);



}