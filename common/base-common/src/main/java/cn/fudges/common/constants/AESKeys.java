package cn.fudges.common.constants;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

import java.util.Base64;

/**
 * @author 王平远
 * @since 2025/4/25
 */

public class AESKeys {

    public static final String AUTHORIZATION_KEY = "sHDaWDnBAO0pMvP2l4lDzQ==";

    public static final String USER_ID_KEY = "sKHOOJXxE5R1lulnKDT6GA==";

    public static void main(String[] args) {
        byte[] byteKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        String key = Base64.getEncoder().encodeToString(byteKey);
        System.out.println(key);
    }
}
