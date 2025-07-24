package com.sales.project.domain.repository;

import com.sales.project.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
