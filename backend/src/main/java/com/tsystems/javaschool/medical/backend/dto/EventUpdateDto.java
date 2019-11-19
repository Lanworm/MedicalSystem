package com.tsystems.javaschool.medical.backend.dto;

import java.util.List;

public class EventUpdateDto {
    private List<MessageDto> msg;

    public List<MessageDto> getMsg() {
        return msg;
    }

    public void setMsg(List<MessageDto> msg) {
        this.msg = msg;
    }
}
