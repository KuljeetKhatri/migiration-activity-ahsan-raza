package com.zindigi.account_migration.service.Impl;

import com.mfs.commonservice.util.AbstractApi;
import com.zindigi.account_migration.model.TblResponseMessage;
import com.zindigi.account_migration.repo.TblResponseMessageRepo;
import com.zindigi.account_migration.service.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn =Exception.class )
public class KycServiceImpl extends AbstractApi implements KycService {

//    @Autowired
    private TblResponseMessageRepo tblResponseMessageRepo;

    public KycServiceImpl() {
    }

    public KycServiceImpl(TblResponseMessageRepo tblResponseMessageRepo) {
        this.tblResponseMessageRepo = tblResponseMessageRepo;
    }

    @Override
    public TblResponseMessage findByResponseMessageDescr(String responseMessageDesc) {
        return tblResponseMessageRepo.findByResponseMessageDescr(responseMessageDesc);

    }
}
