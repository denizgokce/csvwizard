package com.denizgokce.csvwizard.dto.generic;

import java.util.ArrayList;
import java.util.List;

public class Result<T> extends ResultBase {
    private T transactionResult;
    private List<T> transactionResultList = new ArrayList<>();

    public T getTransactionResult() {
        return transactionResult;
    }

    public void setTransactionResult(T transactionResult) {
        this.transactionResult = transactionResult;
    }

    public List<T> getTransactionResultList() {
        return transactionResultList;
    }

    public void setTransactionResultList(List<T> transactionResultList) {
        this.transactionResultList = transactionResultList;
    }
}