package com.example.lapcov_19.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.lapcov_19.entity.Account;

import java.util.List;

public class AccountAdapter extends ArrayAdapter<Account> {
    public  AccountAdapter(@NonNull Context context, int resource, @NonNull List<Account> objects){
        super(context, resource, objects);
    }
}
