package com.edutech.progressive.repository;
 
import com.edutech.progressive.entity.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;
 
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    Accounts findByAccountId(int accountId);

    List<Accounts> findByCustomerId(int customerId);

}

 