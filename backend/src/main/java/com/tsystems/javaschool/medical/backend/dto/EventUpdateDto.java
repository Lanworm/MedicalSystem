package com.tsystems.javaschool.medical.backend.dto;

import java.util.List;

public class EventUpdateDto {
    List<MsgDto> msg;

    public List<MsgDto> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgDto> msg) {
        this.msg = msg;
    }
}
