package com.yang.lottery.rpc.res;

import com.yang.lottery.common.Result;
import com.yang.lottery.rpc.dto.ActivityDto;

import java.io.Serializable;


/**
 * Date:2023/3/26
 * Author:YangChao
 * Description:
 */
public class ActivityRes implements Serializable {
    private Result result;
    private ActivityDto activity;

    public ActivityRes() {
    }

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }

}
