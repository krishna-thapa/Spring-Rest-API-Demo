package com.krishna.api.repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import com.krishna.api.modle.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountsRepository extends CrudRepository<Account, Integer> {

    List<Account> findByCustomerId (Integer custId);

    @Query(value = "SELECT * from Account where status= :status order by LAST_ACTIVITY_DATE", nativeQuery = true)
    List<Account> findActiveAccounts(@Param("status") String status);
}
