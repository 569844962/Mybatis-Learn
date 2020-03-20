package com.chen.mybatis.demo2.emuns;

/**
 * 〈实现DisplaydEnum的枚举〉
 *
 * @author chenmingjun
 * @create 2020.1.16
 * @company
 */
public enum BlogGenericeTypeEnum implements DisplayedEnum {
    SPORTS("S", "体育"),
    NEW("N","新闻")
    ;

    private String code;
    private String msg;

    BlogGenericeTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
