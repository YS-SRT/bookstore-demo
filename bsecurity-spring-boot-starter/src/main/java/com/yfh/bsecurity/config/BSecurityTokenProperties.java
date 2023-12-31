package com.yfh.bsecurity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("bsecurity.token")
public class BSecurityTokenProperties {
    
    /**
     * working mode: mysql or proxy
     */
    private String mode = "mysql";
    
    /**
     * if selected proxy model, then set target name
     */
    private String proxyTargetServiceName = "user-register-service";

    /**
     * RSA KeyID
     */
    private String keyId = "dffbfcb7d38f46f7977b45788e0d7678";
    
    /**
     * RSA PrivateKey Json Format
     */
    private String privateKey = "{\"kty\":\"RSA\",\"kid\":\"dffbfcb7d38f46f7977b45788e0d7678\",\"alg\":\"RS256\",\"n\":\"qin1rz9bF89bIfbGQR1w19y2nvW9bK3lZ4koBqtEzu7jymbzCmJUfsLdBaZ1p_0phyLgS41zokr2KVXWN9VZJj1qJeHqZ6AgSnD9LOPwiOHw9sS0YzcdKwy_LfRLj6rQ8h-ER1NeiMhsTAG4XZucGIMougOAzlnhTphHEGfcMgIBCKFAurnauqToyTLUow2NbWX0OIrErq7CydM34N86BqJBU6KFgR2sEk76K4wVV9QM5BLFxv8xGrIazAEsebNzcLaT82kPdWa97sVf-pCuswi4oF0Q5VCsHNk9mxAjDiR8ZeXty6N1wxNBUcs1KJ9Ys5wajRGt8zHBwgF5Tk-yTQ\",\"e\":\"AQAB\",\"d\":\"Vh0x9mQrp1sKlZ-bKSzLBJLv4HYgTLWcWM8ycF2QTuDaan27FVA5zoSy8b8R39fggkEGHXYSRNmGxdhotozDtvRxmYZA5_xDGykfFu6rtboSBe_6EhO8MSMcRRdSffxGDaIhgS8RsksrczOTp9uuHUGTBL_tGYCm4u1xWT4WUz-YviBCHuXe9IH0xPJ46NqnCiervYjNv4-_Y4jo_KeCNTAVXP9it-o6WOszoyLI59yWRcoKNAbtC7B1q4CG2k5076MhVUPfyoBQJKPmvnMyyFlNyehnX-_02_3GfEHkmZQC_FWaoUV_yxyXRVkt8tgKsSbq4I1zmo0IeCYtfKgQgQ\",\"p\":\"3c_FXpA9Dpg1sLdyl9SHYe4FJ8vbHCk4YCOtYIwcZe_ESSxAYj2m8Y-FUo-XZH8pQVokcibr-Bsa_Luw9GQijwX8clnL5l4rRNu1qyDbxsJmEJsYZiTMJcXlRrjt1GylAeXTlGD7D6c53hx2n-C56F5IrrHyhRS3DGB6eKCdqpE\",\"q\":\"xGRG4PvZ0yvMSCXy82gZhYnK_m7IDhjk-ORrnVX2Gu025ogELAGtZAVsXJSSzSNnbL_p2Y98i149zRBABbxzXb1NPjvKehKtbXVGPIchxvQbr2vVXkGwON_szDa-d1vqnEJo5l6hiVEm4XzNct7YGrkh3eGiQO-kErjt8VPCkf0\",\"dp\":\"2yvy9QaHHkWuP6h0tavN_ikHtRQMtF3Sk8SNC2pGoT7yb-8myyvqKqRK85Jo4Gd551Zfzt_AqE54MyRurH3JXBNpJ8Cs5jtEe-yxLJ_yKgpqeZwW1s8jXWuWqnQOpKiZvy9OCj5ttUDiwCLV4BJZAE8xeFTiszcNzFAEk-74-9E\",\"dq\":\"BaqrDOTXdStnWpGVwAY57kt6ivrhVN7NBI16uXB4iHBdzCsSi76EpUUuykETN04VNuMWuUtVmaPffqJAZqn4XjUPHIOXJQyS6nyeyTJQ_C-g-N_QqUaV8h4r8_yEZGdHjriv1C3Y1WB9EbaLKwxZCfRbkM5o4xzHDC8_1l3HWHU\",\"qi\":\"P8icCgOFcQoZ30zmO469yND-fauAVQekbY5VFC40OT1fv29ZYTxjEiObvvJBf_gqivI_psJAaqPv2_o1k5QIdZTO8OjiuvW3pmdeD7wQty0NFf49PJEe3p4n2m2BgZLjefWDYeg2aikmsWwkFJ4yqBcvrjovfNhjzPrhxFDE3Nc\"}";
    
    /**
     * RSA PublicKey Json Format
     */
    private String publicKey ="{\"kty\":\"RSA\",\"kid\":\"dffbfcb7d38f46f7977b45788e0d7678\",\"alg\":\"RS256\",\"n\":\"qin1rz9bF89bIfbGQR1w19y2nvW9bK3lZ4koBqtEzu7jymbzCmJUfsLdBaZ1p_0phyLgS41zokr2KVXWN9VZJj1qJeHqZ6AgSnD9LOPwiOHw9sS0YzcdKwy_LfRLj6rQ8h-ER1NeiMhsTAG4XZucGIMougOAzlnhTphHEGfcMgIBCKFAurnauqToyTLUow2NbWX0OIrErq7CydM34N86BqJBU6KFgR2sEk76K4wVV9QM5BLFxv8xGrIazAEsebNzcLaT82kPdWa97sVf-pCuswi4oF0Q5VCsHNk9mxAjDiR8ZeXty6N1wxNBUcs1KJ9Ys5wajRGt8zHBwgF5Tk-yTQ\",\"e\":\"AQAB\"}";
    
    /**
     * Token Header Name
     */
    private String headerName = "Authorization";
    
    /**
     * Token String Prefix
     */
    private String headerValuePrefix = "bearer";
    
    /**
     * AccessToken Expire Time in Seconds
     */
    private int accessExpirationSeconds = 7200;

    /**
     * RefreshToken Method to split Token verified way
     */
    private String refreshMethod = "refresh_token";

    /**
     * RefreshToken Expire Time in Seconds, default is double AccessToken Expiration Time
     */
    private int refreshExpirationSeconds = 14400;
}
