package com.zindigi.account_migration.service;

import com.zindigi.account_migration.model.TblResponseMessage;

public interface KycService {
    TblResponseMessage findByResponseMessageDescr(String responseMessageDesc);

}
