package com.example.lapcov_19.model;

import com.example.lapcov_19.entity.Account;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountResult {
    @SerializedName("account")
    @Expose
    private List<Account> account;

    public List<Account> getAcc(){
        return account;
    }
}
