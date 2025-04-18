package com.zindigi.account_migration.repo;

import com.zindigi.account_migration.model.TblResponseMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TblResponseMessageRepo extends JpaRepository<TblResponseMessage, Long> {

    TblResponseMessage findByResponseMessageDescr(String responseMessageDesc);
    TblResponseMessage findByResponseMessageCode(String responseMessageCode);

}
