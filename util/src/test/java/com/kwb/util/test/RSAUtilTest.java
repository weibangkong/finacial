package com.kwb.util.test;

import com.kwb.util.common.RSAUtil;
import org.junit.Test;

public class RSAUtilTest {

    /**
     * Public Key
     */
    static final String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZLLZ2deFxGsRARQcxuY0E5UDU\n" +
            "iskoX607waKI8hYr8KV3Mxw0aF21AcxEKdrUpdHpRUn45ROGjxOntCbNMcSkM4VZ\n" +
            "93dOfUGM45B79uqEhvRPJ4x8Fh58OgMluohNfVof3eYT1zshTk8Stb9zkoD96NjZ\n" +
            "5UJSgnmmRBZX6B/3+QIDAQAB";

    /**
     * Private Key
     */
    static final String PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANkstnZ14XEaxEBF\n" +
            "BzG5jQTlQNSKyShfrTvBoojyFivwpXczHDRoXbUBzEQp2tSl0elFSfjlE4aPE6e0\n" +
            "Js0xxKQzhVn3d059QYzjkHv26oSG9E8njHwWHnw6AyW6iE19Wh/d5hPXOyFOTxK1\n" +
            "v3OSgP3o2NnlQlKCeaZEFlfoH/f5AgMBAAECgYBoU9V3hNt/FSev5Dk3hvaaonMJ\n" +
            "Z36I+aVjrnqhJQLHqvwiDhh/iJ9zg+CX4i9wthPn5tRJT+qYWoVLq/xz2HOot1hO\n" +
            "oO8UwAVtvMS1bsTrAuiYNiLO5akKA9BMSru7wzctJluE66AxEOp/q9h2hUvTORYV\n" +
            "sKo2TZEswT/cnuOgAQJBAOv/LIck/IIig5pkyBCjqNpbc84mc1ezrY9vwZm3umE8\n" +
            "Ls+Ydb+iAhy2eHAhyTgEIUTBckWeinZvPNH6ONB7k/kCQQDrlR+IOuu0Ji+K1gdp\n" +
            "t7Lt50IwWesLUwJoTXG3J83Rmf++syKuYJQruH01JhUezwyworit3MIa0+eZvLWA\n" +
            "GIQBAkAZSTgOKupbTzNmoZHGJqL68n2QAxKsvPXFvqWxwLvZm0cwuxcytzsetHZn\n" +
            "HVegBuFBqrCBwSD426ZaoULhI7yRAkEApzoG1RRGJm4Ix3vgbTnaCKhlQp9BiptV\n" +
            "FScMNqBJqKSxdvQABVpHM7XVIzoyOdltv6DyiXAwVQzg1S+wgwpkAQJBAMkcgrLa\n" +
            "JGpUV441whSGBnoq8b+fTuCH+kJeyzID/0SrvCmHe8gkw+1Qa9+aqoprXX1cEsS5\n" +
            "JrzA17Qr8MFmpsQ=";

    @Test
    public void signTest() {
        String test = "kwb";
        try {
            String sign = RSAUtil.sign(test.getBytes(), PRIVATEKEY);
            System.err.println("sign:" + sign);
            System.err.println(RSAUtil.verify(test.getBytes(), PUBLICKEY, sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
