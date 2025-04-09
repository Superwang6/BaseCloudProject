package cn.fudges.common.utils.authentication;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * @author 王平远
 * @since 2025/4/8
 */
public class AuthenticationUtils {

    private static final SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, Base64.decode("sHDaWDnBAO0pMvP2l4lDzQ=="));

    public static String encode(String value) {
        return aes.encryptHex(value);
    }

    public static String decode(String encryptHex) {
        return aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
    }
}
