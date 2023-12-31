package com.yfh.register;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jwk.JsonWebKey.OutputControlLevel;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import cn.hutool.core.lang.UUID;

public class RSAKeyGenerator {
     public static void main(String[] args) {

        String keyId = UUID.fastUUID().toString(true);
        try{
            RsaJsonWebKey jwk = RsaJwkGenerator.generateJwk(2048);
            jwk.setKeyId(keyId);
            jwk.setAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256);
            String publicKey = jwk.toJson(OutputControlLevel.PUBLIC_ONLY);
            String privateKey = jwk.toJson(OutputControlLevel.INCLUDE_PRIVATE);

            System.out.println(String.format("RSAKeys: keyId=%s\r publicKey=%s\r privateKey=%s", 
                                              keyId, publicKey, privateKey));
        }catch(JoseException ex){
            ex.printStackTrace();
        }
     }
}
