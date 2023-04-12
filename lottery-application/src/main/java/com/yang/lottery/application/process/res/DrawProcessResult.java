package com.yang.lottery.application.process.res;


import com.yang.lottery.common.Result;
import com.yang.lottery.domain.strategy.model.vo.DrawAwardInfo;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/11
 * @Copyright：
 */
public class DrawProcessResult extends Result {

    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}
