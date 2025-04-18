package com.zindigi.account_migration.service;

import com.zindigi.account_migration.model.LkpAccountType;
import com.zindigi.account_migration.model.LkpCurrency;
import com.zindigi.account_migration.model.TblAccount;
import com.zindigi.account_migration.model.TblResponseMessage;

public interface ResponseService {
    TblResponseMessage getResponseMessageByResponseDescr(String descr);
    LkpAccountType getLkpAccountType(String accountLevelType);

    LkpCurrency getLkpCurrency(String currencyCode);

    TblAccount getTblAccountByAccountNumber(String accountNumber);

//    TblMcRequest getMcRequestCheckerById(Long mcRequestId);

    TblResponseMessage findByResponseMessageCode(String responseMessageCode);

    String getText(String text, String name);

    String getSubject(String subject);
}
