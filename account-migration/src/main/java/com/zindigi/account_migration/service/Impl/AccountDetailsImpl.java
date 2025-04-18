package com.zindigi.account_migration.service.Impl;

import com.zindigi.account_migration.controller.AbstarctApi;
import com.zindigi.account_migration.model.TblCustomer;
import com.zindigi.account_migration.repo.TblCustomerRepo;
import com.zindigi.account_migration.service.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn =Exception.class )
public class AccountDetailsImpl extends AbstarctApi implements AccountDetailsService {

    @Autowired
    private TblCustomerRepo tblCustomerRepo;
    @Override
    public boolean isEmailExist(String email) {
        long isExist = 0;
        if (!isNullOrEmpty(email)) {
            isExist = tblCustomerRepo.isEmailHashExist(email);
        }
        return isExist == 0 ? false : true;
    }
    @Override
    public boolean isEmailExistUpdateAccount(String email, String mobileNumber) {
        boolean exist = false;
        TblCustomer tblCustomer = tblCustomerRepo.findByMobileNumberHashAndEmailHash(email, mobileNumber);
        if (tblCustomer == null) {
            exist = isEmailExist(email);
        }
        return exist;
    }


    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

}
