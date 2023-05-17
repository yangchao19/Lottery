package com.yang.lottery.application.process.draw.res;


import com.yang.lottery.common.Result;
import com.yang.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * @description:
 * @author：杨超
 * @date: 2023/4/11
 * @Copyright：
 */
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardVO;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawAwardVO getDrawAwardVO() {
        return drawAwardVO;
    }

    public void setDrawAwardVO(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }
}
