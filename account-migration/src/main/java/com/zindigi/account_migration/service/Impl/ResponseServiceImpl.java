package com.zindigi.account_migration.service.Impl;

import com.mfs.commonservice.model.LkpAccountType;
import com.mfs.commonservice.model.LkpCurrency;
import com.mfs.commonservice.repo.LkpCurrencyRepo;
import com.zindigi.account_migration.model.TblAccountModel;
import com.zindigi.account_migration.model.TblResponseMessage;
import com.zindigi.account_migration.repo.LkpAccountTypeRepo;
import com.zindigi.account_migration.repo.TblAccountRepo;
import com.zindigi.account_migration.repo.TblResponseMessageRepo;
import com.zindigi.account_migration.service.ResponseService;
import com.zindigi.account_migration.util.Constants;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn =Exception.class )
public class ResponseServiceImpl implements ResponseService {
//    @Autowired
    private TblResponseMessageRepo tblResponseMessageRepo;
//    @Autowired
    private LkpCurrencyRepo lkpCurrencyRepo;
//    @Autowired
    private LkpAccountTypeRepo lkpAccountTypeRepo;
//    @Autowired
    private TblAccountRepo tblAccountRepo;
//    @Autowired
//    private TblMcRequestRepo tblMcRequestRepo;


    public ResponseServiceImpl() {
    }

    public ResponseServiceImpl(TblResponseMessageRepo tblResponseMessageRepo, LkpCurrencyRepo lkpCurrencyRepo, LkpAccountTypeRepo lkpAccountTypeRepo, TblAccountRepo tblAccountRepo) {
        this.tblResponseMessageRepo = tblResponseMessageRepo;
        this.lkpCurrencyRepo = lkpCurrencyRepo;
        this.lkpAccountTypeRepo = lkpAccountTypeRepo;
        this.tblAccountRepo = tblAccountRepo;
    }

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
    public TblAccountModel getTblAccountByAccountNumber(String accountNumber) {
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
    @Override
    public LkpAccountType getLkpAccountTypeByCode(String accountTypeCode) {
        return lkpAccountTypeRepo.findByAccountTypeCode(accountTypeCode).orElse(null);
    }

    @Override
    public LkpCurrency getLkpCurrencyByCode(String currencyCode) {
        return null;
    }
}
