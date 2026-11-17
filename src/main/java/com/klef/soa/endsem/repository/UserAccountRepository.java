
package com.klef.soa.endsem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.endsem.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>
{
    UserAccount findByUsername(String username);

    boolean existsByUsername(String username);
}
