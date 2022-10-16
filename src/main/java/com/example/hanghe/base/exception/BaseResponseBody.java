package com.example.hanghe.base.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponseBody {
    private String statusCode;
    private String statusMsg;
    private Object data;

    public BaseResponseBody(StatusCode statusCode, Object data){
        this.statusCode = statusCode.getStatusCode();
        this.statusMsg = statusCode.getStatusMsg();
        this.data = data;
    }
}
