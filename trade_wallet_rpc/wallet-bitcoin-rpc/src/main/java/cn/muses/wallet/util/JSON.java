package cn.muses.wallet.util;

public class JSON {
    public JSON() {
    }

    public static String stringify(Object o) {
        return com.alibaba.fastjson.JSON.toJSONString(o);
    }

    public static Object parse(String s) {
        return CrippledJavaScriptParser.parseJSExpr(s);
    }
}
