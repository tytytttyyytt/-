package com.zjut.edu.grademanager.Error;

public enum EnumBusinessError implements CommonError{
    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    //未知错误00002
    UNKNOWN_ERROR(10002,"未知错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    PASSWORD_WRONG(20002,"密码错误"),
    USER_ALREADY_EXIST(20004,"用户已经存在"),
    USER_CHANGE_PASSWORD_FAILED(20005,"用户修改密码失败"),

    /**
     *@author John STRAT
     *@date 2019/12/5 21:45
     */

    // 30000开头的为城市相关信息
    CITY_LIST_LOAD_FAILED(30001,"城市列表加载失败"),

    // 40000开头的为成绩单相关信息;
    REPORT_LOAD_FAILED(40000,"暂无成绩单"),

    // 50000开头的为学年学期相关信息
    TERM_LOAD_ERROR(50000,"学期加载失败"),
    YEARS_LOAD_FAILED(50001,"学年加载失败"),

    // 60000开头的为成绩修改相关信息
    UPDATE_SUCCESS(60001,"成绩修改成功"),
    INSERT_FAILED_EMPTY(60002,"成绩不能为空"),
    INSERT_OR_UPDATE_FAILED(60003,"成绩修改或插入失败"),


    // 70000开头的为管理员相关信息
    STUDENT_LIST_LOAD_FAILED(70001,"学生列表加载失败"),
    REJECT_DELETE_COURSE(70002,"系统拒绝删除课程记录"),
    REJECT_DELETE_OPENCOURSE(70003,"系统拒绝删除开课记录");

    /**
     *@author John END
     *@date 2019/12/5 21:45
     */


    private EnumBusinessError(int errCode, String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
    }

    private int errCode;
    private String errMsg;
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
