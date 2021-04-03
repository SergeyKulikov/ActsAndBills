package com.mycoloruniverse.actsbills.models;

import java.util.UUID;

/**
 * Банковский счет организации
 *
 * https://bik-info.ru/base/base.xml - актуальная база данных??
 *
 * Возможно это будет рабочий вариант http://www.cbr.ru/vfs/?id=111923
 *
 */


class CompanyBankAccount {
    private String guid;  // Код банковскго счета для внешней связи
    private String bankBik;  // БИК
    private String bankAccount;  // Счет бака (корр счет)
    private String bankName;  // Название банка
    private String account;  // Счет организации
    private String currencyCharCode;  // Код валюты (строковый)

    public CompanyBankAccount() {
        guid = UUID.randomUUID().toString();
    }

    public CompanyBankAccount(String guid, String bankBik, String bankAccount, String bankName, String account, String currencyCharCode) {
        this.guid = guid;
        this.bankBik = bankBik;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
        this.account = account;
        this.currencyCharCode = currencyCharCode;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getBankBik() {
        return bankBik;
    }

    public void setBankBik(String bankBik) {
        this.bankBik = bankBik;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCurrencyCharCode() {
        return currencyCharCode;
    }

    public void setCurrencyCharCode(String currencyCharCode) {
        this.currencyCharCode = currencyCharCode;
    }
}
