package com.zindigi.account_migration.service;

import com.mfs.commonservice.model.LkpAccountType;
import com.mfs.commonservice.model.LkpCurrency;
import com.zindigi.account_migration.model.TblAccountModel;
import com.zindigi.account_migration.model.TblResponseMessage;

public interface ResponseService {
    TblResponseMessage getResponseMessageByResponseDescr(String descr);
    LkpAccountType getLkpAccountType(String accountLevelType);

    LkpCurrency getLkpCurrency(String currencyCode);

    TblAccountModel getTblAccountByAccountNumber(String accountNumber);

//    TblMcRequest getMcRequestCheckerById(Long mcRequestId);

    TblResponseMessage findByResponseMessageCode(String responseMessageCode);

    String getText(String text, String name);

    String getSubject(String subject);

    LkpAccountType getLkpAccountTypeByCode(String accountTypeCode);
    LkpCurrency getLkpCurrencyByCode(String currencyCode);

}
