package com.tsystems.javaschool.medical.backend.dto;

import com.tsystems.javaschool.medical.backend.dto.enums.MessageStatus;

public class MessageDto {
    private String message;
    private MessageStatus status= MessageStatus.SUCCESS;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
