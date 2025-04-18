package com.zindigi.account_migration.service;

import java.security.NoSuchAlgorithmException;

public interface AccountDetailsService {

    boolean isEmailExist(String email) throws NoSuchAlgorithmException;

    boolean isEmailExistUpdateAccount(String email, String mobileNumber);

}
