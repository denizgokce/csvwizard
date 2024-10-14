package com.denizgokce.csvwizard.dto.generic;

import java.util.ArrayList;
import java.util.List;

public class ResultBase {
    private boolean isSucceeded;
    private List<String> resultMessage = new ArrayList<>();

    public boolean isSucceeded() {
        return isSucceeded;
    }

    public void setSucceeded(boolean succeeded) {
        isSucceeded = succeeded;
    }

    public List<String> getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(List<String> resultMessage) {
        this.resultMessage = resultMessage;
    }
}

