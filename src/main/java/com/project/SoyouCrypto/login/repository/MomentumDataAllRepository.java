package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.MomentumDataAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentumDataAllRepository extends JpaRepository<MomentumDataAll, Integer> {



}