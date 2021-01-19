package com.czy.echat.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * <h3>echat</h3>
 * <p></p>
 *  jsk转换为pfx证书
 * @author : 刘翼丰
 * @date : 2021-01-19 15:13
 **/
public class JKS2PFX {
    public static final String PKCS12 = "PKCS12";
    public static final String JKS = "JKS";
    public static final String PFX_KEYSTORE_FILE = "D:\\KSEC\\2021\\1\\WebSocket\\Echat-springboot-master\\src\\main\\resources\\test.pfx";
    public static final String KEYSTORE_PASSWORD = "123456";
    public static final String JKS_KEYSTORE_FILE = "D:\\KSEC\\2021\\1\\WebSocket\\Echat-springboot-master\\src\\main\\resources\\test.jks";

    public static void coverToPfx() {
        try {
            KeyStore inputKeyStore = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(JKS_KEYSTORE_FILE);
            char[] nPassword = null;

            if ((KEYSTORE_PASSWORD == null)
                    || KEYSTORE_PASSWORD.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }

            inputKeyStore.load(fis, nPassword);
            fis.close();

            KeyStore outputKeyStore = KeyStore.getInstance("PKCS12");

            outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());

            Enumeration enums = inputKeyStore.aliases();

            while (enums.hasMoreElements()) { //   we   are   readin   just   one   certificate.

                String keyAlias = (String) enums.nextElement();

                System.out.println("alias=[" + keyAlias + "]");

                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, nPassword);
                    Certificate[] certChain = inputKeyStore
                            .getCertificateChain(keyAlias);

                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD
                            .toCharArray(), certChain);
                }
            }

            FileOutputStream out = new FileOutputStream(PFX_KEYSTORE_FILE);

            outputKeyStore.store(out, nPassword);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        coverToPfx();
    }
}
