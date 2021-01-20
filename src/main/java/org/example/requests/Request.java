package org.example.requests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Request {
    private final ReqType type;
    private final List<String> reqParams;

    public Request(ReqType rType, String... params){
        this.type = rType;
        this.reqParams = new ArrayList<>();
        this.reqParams.addAll(Arrays.asList(params));
    }



    // GETTERS

    public List<String> getReqParams() {
        return reqParams;
    }

    public ReqType getType() {
        return type;
    }

}
