package com.yang.lottery.common;


/**
 *
 * @author yc
 */
@SuppressWarnings({"all"})
public class Constants {
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001","未知失败"),
        ILLEGAL_PARAMETER("0002","非法参数"),
        INDEX_DUP("0003","主键冲突"),
        NO_UPDATE("0004","SQL操作无更新"),
        LOSING_DRAW("D001","未中奖"),
        RULE_ERR("D002","量化人群规则执行失败"),
        NOT_CONSUMED_TAKE("D003","未消费活动领取记录"),
        OUT_OF_STOCK("D004","活动无库存"),
        ERR_TOKEN("D005","分布式锁失败");


        private String code;
        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    /**
     * 全局属性
     */
    public static final class Global {
        /**空节点值*/
        public static final Long TREE_NULL_NODE = 0L;
    }

    /**
     * 缓存 Key
     */
    public static final class RedisKey {

        // 抽奖活动库存key
        private static final String LOTTERY_ACTIVITY_STOCK_COUNT = "lottery_activity_stock_count_";

        public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT(long activityId) {
            return LOTTERY_ACTIVITY_STOCK_COUNT + activityId;
        }

        // 抽奖活动库锁 Key
        private static final String LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN = "lottery_activity_stock_count_token_";

        public static String KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(Long acticityId, Integer stockUserdCount) {
            return LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN + acticityId + "_" + stockUserdCount;
        }
    }


    /**
     * 决策树节点
     */
    public static final class NodeType {

        /**树茎*/
        public static final Integer STEM = 1;

        /**果实*/
        public static final Integer FRUIT = 2;
    }

    /**
     * 规则限定类型
     */
    public static final class RuleLimitType {

        public static final int EQUAL = 1;

        public static final int GT = 2;

        public static final int LT = 3;

        public static final int GE = 4;

        public static final int LE = 5;

        public static final int ENUM = 6;
    }


    /**
     * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启
     */
    public enum ActivityState{
        /** 1：编辑 */
        EDIT(1,"编辑"),
        /** 2：提审 */
        ARRAIGNMENT(2,"提审"),
        /** 3：撤审 */
        REVOKE(3,"撤审"),
        /** 4：通过 */
        PASS(4,"通过"),
        /** 5：运行 */
        DOING(5,"运行(活动中)"),
        /** 6：拒绝 */
        REFUSE(6,"拒绝"),
        /** 7：关闭 */
        CLOSE(7,"关闭"),
        /** 8：开启 */
        OPEN(8,"开启")
        ;
        private Integer code;
        private String info;

        ActivityState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 抽奖策略模式：总体概率、单项概率
     * 场景：两种抽奖算法描述，场景A20%、B30%、C50%
     * 单项概率：如果A奖品抽空后，B和C保持目前中奖概率，用户抽奖扔有20%中为A，因A库存抽空则结果展示为未中奖。为了运营成本，通常这种情况的使用的比较多
     * 总体概率：如果A奖品抽空后，B和C奖品的概率按照 3:5 均分，相当于B奖品中奖概率由 0.3 升为 0.375
     */
    public enum StrategyMode{
        /**
         * 单项概率：如果A奖品抽空后，B和C保持目前中奖概率，用户抽奖扔有20%中为A，因A库存抽空则结果展示为未中奖。为了运营成本，通常这种情况的使用的比较多
         */
        SINGLE(1,"单项概率"),
        /**
         * 总体概率：如果A奖品抽空后，B和C奖品的概率按照 3:5 均分，相当于B奖品中奖概率由 0.3 升为 0.375
         */
        ENTIRETY(2,"总体概率");

        private Integer code;
        private String info;

        StrategyMode(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 中奖状态：0未中奖、1已中奖、2兜底奖
     */
    public enum DrawState {

        FAIL(0,"未中奖"),

        SUCCESS(1,"已中奖"),

        COVER(2,"兜底奖")
        ;

        private Integer code;
        private String info;

        DrawState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public enum AwardState {
        WAIT(0,"等待发奖"),
        SUCCESS(1,"发奖成功"),
        FAILURE(2,"发奖失败")
        ;
        private Integer code;
        private  String info;

        AwardState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public enum AwardType {
        /**
         * 文字描述
         */
        DESC(1,"文字描述"),
        /**
         * 兑换码
         */
        RedeemCodeGoods(2,"兑换码"),
        /**
         * 优惠券
         */
        CouponGoods(3,"优惠券"),
        /**
         * 实物奖品
         */
        PhysicalGoods(4,"实物奖品")
        ;
        protected Integer code;
        protected String type;

        AwardType(Integer code, String type) {
            this.code = code;
            this.type = type;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public enum Ids {
        /** 雪花算法*/
        SnowFlake,
        /** 日期算法*/
        ShortCode,
        /** 随机算法*/
        RandomNumeric;
    }


    /**
     * 活动单使用状态  0未使用  1已使用
     */
    public enum TaskState {
        NO_USED(0,"未使用"),
        USED(1,"已使用")
        ;


        private Integer code;
        private String info;

        TaskState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


    /**
     * 发奖状态 0初始 1完成  2失败
     */
    public enum GrantState{

        INIT(0,"初始"),
        COMPLETE(1,"完成"),
        FAIL(2,"失败")
        ;

        private Integer code;
        private String info;

        GrantState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public enum MQState {
        INIT(0,"初试"),
        COMPLETE(1,"完成"),
        FAIL(2,"失败")
        ;
        private Integer code;
        private String info;

        MQState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
