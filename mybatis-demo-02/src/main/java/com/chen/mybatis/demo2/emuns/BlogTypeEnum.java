package com.chen.mybatis.demo2.emuns;

import java.util.Objects;

/**
 * 〈博客类型〉
 *
 * @author chenmingjun
 * @create 2020.1.10
 * @company
 */
public enum BlogTypeEnum {
    SPORTS("S", "体育"),
    NEW("N","新闻")
    ;


    BlogTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public static BlogTypeEnum getEnum(String code) {
        for (BlogTypeEnum e : BlogTypeEnum.values()) {
            if (Objects.equals(e.getCode(), code)) {
                return e;
            }
        }
        return null;
    }

}
