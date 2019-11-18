package com.tsystems.javaschool.medical.backend.dto;

import com.tsystems.javaschool.medical.backend.dto.enums.MsgStatus;

public class MsgDto {
    private String message;
    private MsgStatus status= MsgStatus.SUCCESS;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MsgStatus getStatus() {
        return status;
    }

    public void setStatus(MsgStatus status) {
        this.status = status;
    }
}
