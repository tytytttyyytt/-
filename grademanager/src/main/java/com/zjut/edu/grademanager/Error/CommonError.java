package com.zjut.edu.grademanager.Error;

public interface CommonError {
    public int getErrorCode();
    public String getErrorMsg();
    public CommonError setErrMsg(String errMsg);
}
