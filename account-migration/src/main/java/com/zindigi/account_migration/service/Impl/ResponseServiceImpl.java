package com.zindigi.account_migration.service.Impl;

import com.zindigi.account_migration.model.LkpAccountType;
import com.zindigi.account_migration.model.LkpCurrency;
import com.zindigi.account_migration.model.TblAccount;
import com.zindigi.account_migration.model.TblResponseMessage;
import com.zindigi.account_migration.repo.LkpAccountTypeRepo;
import com.zindigi.account_migration.repo.LkpCurrencyRepo;
import com.zindigi.account_migration.repo.TblAccountRepo;
import com.zindigi.account_migration.repo.TblResponseMessageRepo;
import com.zindigi.account_migration.service.ResponseService;
import com.zindigi.account_migration.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn =Exception.class )
public class ResponseServiceImpl implements ResponseService {
    @Autowired
    private TblResponseMessageRepo tblResponseMessageRepo;
    @Autowired
    private LkpCurrencyRepo lkpCurrencyRepo;
    @Autowired
    private LkpAccountTypeRepo lkpAccountTypeRepo;
    @Autowired
    private TblAccountRepo tblAccountRepo;
//    @Autowired
//    private TblMcRequestRepo tblMcRequestRepo;


    @Override
    public TblResponseMessage getResponseMessageByResponseDescr(String descr) {
        TblResponseMessage tblResponseMessage = tblResponseMessageRepo.findByResponseMessageDescr(descr);
        if (tblResponseMessage != null) {
            return tblResponseMessage;
        } else {
            return null;
        }

    }

    @Override
    public LkpAccountType getLkpAccountType(String accountLevelType) {
        LkpAccountType lkpAccountType = lkpAccountTypeRepo.findByAccountTypeName(accountLevelType);
        return lkpAccountType;
    }

    @Override
    public LkpCurrency getLkpCurrency(String currencyCode) {
        LkpCurrency lkpCurrency = lkpCurrencyRepo.findByCurrencyName(currencyCode);
        return lkpCurrency;
    }

    @Override
    public TblAccount getTblAccountByAccountNumber(String accountNumber) {
        return tblAccountRepo.findByAccountNo(accountNumber);
    }

//    @Override
//    public TblMcRequest getMcRequestCheckerById(Long mcRequestId) {
//        return tblMcRequestRepo.findByMcRequestId(mcRequestId);
//    }

    @Override
    public TblResponseMessage findByResponseMessageCode(String responseMessageCode) {
        return tblResponseMessageRepo.findByResponseMessageCode(responseMessageCode);
    }

    @Override
    public String getText(String text, String name) {
        TblResponseMessage tblResponseMessage = findByResponseMessageCode(text);
        if (tblResponseMessage == null) {
            tblResponseMessage = new TblResponseMessage();
            tblResponseMessage.setResponseMessageDescr(Constants.EMAIL_MESSAGE);
        }
        String result = tblResponseMessage.getResponseMessageDescr().replace(Constants.DUMMY_TEXT, Constants.DEAR + name);
        return result;
    }

    @Override
    public String getSubject(String subject) {
        TblResponseMessage tblResponseMessage = findByResponseMessageCode(subject);
        if (tblResponseMessage == null) {
            tblResponseMessage = new TblResponseMessage();
            tblResponseMessage.setResponseMessageDescr(Constants.DOCUMENT);
        }
        return tblResponseMessage.getResponseMessageDescr();
    }
}
