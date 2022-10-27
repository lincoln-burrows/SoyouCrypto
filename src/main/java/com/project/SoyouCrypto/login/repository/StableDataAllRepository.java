package com.project.SoyouCrypto.login.repository;



import com.project.SoyouCrypto.login.model.StableDataAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StableDataAllRepository extends JpaRepository<StableDataAll, Integer> {



}