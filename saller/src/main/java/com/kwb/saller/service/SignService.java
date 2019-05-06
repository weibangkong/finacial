package com.kwb.saller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SignService {
    static Map<String, String> PUBLICKEYS = new HashMap<String, String>();
    static {
        PUBLICKEYS.put("kwb","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZLLZ2deFxGsRARQcxuY0E5UDU\n" +
                "iskoX607waKI8hYr8KV3Mxw0aF21AcxEKdrUpdHpRUn45ROGjxOntCbNMcSkM4VZ\n" +
                "93dOfUGM45B79uqEhvRPJ4x8Fh58OgMluohNfVof3eYT1zshTk8Stb9zkoD96NjZ\n" +
                "5UJSgnmmRBZX6B/3+QIDAQAB");
    }

    /**
     * 根据用户id获取公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId) {
        return PUBLICKEYS.get(authId).toString();
    }
}
