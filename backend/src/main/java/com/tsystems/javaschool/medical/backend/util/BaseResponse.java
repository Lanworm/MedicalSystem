package com.tsystems.javaschool.medical.backend.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private List<MsgStatus> msg = new ArrayList<>();
    private Map<String, Object> data;
    private List<Object> list;
    private int count;
    private long pages;
    private long records;

    public List<MsgStatus> getMsg() {
        if (msg == null) {
            msg = new ArrayList<>();
        }
        msg.sort(null);
        return msg;
    }

    public void setMsg(List<MsgStatus> msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    private void setData(Map<String, Object> data) {
        this.data = data;
    }

    public List<Object> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.sort(null);
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Integer getCount() {
        count = this.getList().size();
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public void addMessageWithStatus(final MsgStatus.Status status) {
        BaseResponse.MsgStatus msgStatus = new BaseResponse.MsgStatus();
        msgStatus.setStatus(status);
        this.msg.add(msgStatus);
    }

    private void addMessageWithStatus(final MsgStatus.Status status, final String text) {
        BaseResponse.MsgStatus msgStatus = new BaseResponse.MsgStatus();
        msgStatus.setStatus(status);
        if (text != null) {
            msgStatus.setText(text);
        }
        this.msg.add(msgStatus);
    }

    public void addMessageWithStatus(final BaseResponse.MsgStatus status) {
        this.msg.add(status);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MsgStatus {
        private Status status = Status.success;
        private String text;
        @JsonProperty("for")
        private String fort;

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getFort() {
            return fort;
        }

        public void setFort(String fort) {
            this.fort = fort;
        }

        public enum Status {
            success, error, warning, info, debug
        }
    }

    public static BaseResponse ok(final String text) {
        final BaseResponse result = new BaseResponse();
        result.setData(new HashMap<>());
        result.addMessageWithStatus(BaseResponse.MsgStatus.Status.success, text);
        return result;
    }


    public static BaseResponse error(final String text) {
        final BaseResponse result = new BaseResponse();
        result.setData(new HashMap<>());
        result.addMessageWithStatus(BaseResponse.MsgStatus.Status.error, text);
        return result;
    }
}
