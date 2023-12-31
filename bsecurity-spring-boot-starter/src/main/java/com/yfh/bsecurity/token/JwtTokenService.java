package com.yfh.bsecurity.token;

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.yfh.bsecurity.config.BSecurityTokenProperties;

import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class JwtTokenService {

    @Resource
    private BSecurityTokenProperties tokenProperties;

    public String createToken(Object userId, Object userType, Object loginName, int tokenExpirationSeconds){

        try {
            //Payload
            JwtClaims claims = new JwtClaims();
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            //expire time
            NumericDate date = NumericDate.now();
            date.addSeconds(tokenExpirationSeconds);
            claims.setExpirationTime(date);
            claims.setNotBeforeMinutesInThePast(1);
            claims.setSubject("bookstore");
            claims.setAudience("auth_user");
            //add self-defined fields
            claims.setClaim("userId", userId);
            claims.setClaim("userType", userType);
            claims.setClaim("loginName", loginName);
            //jws
            JsonWebSignature jws = new JsonWebSignature();
            //RSA256
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
            jws.setKeyIdHeaderValue(tokenProperties.getKeyId());
            jws.setPayload(claims.toJson());
            PrivateKey privateKey = new RsaJsonWebKey(JsonUtil.parseJson(tokenProperties.getPrivateKey())).getPrivateKey();
            jws.setKey(privateKey);
            //get token
            String idToken = jws.getCompactSerialization();
            return idToken;
        } catch (JoseException ex) {
            log.error(ex.getMessage());
        }
        return null;

       
    }

    public JwtClaims verifyToken(String token, int tokenExpirationSeconds) {
        try {
            JwtConsumer consumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setMaxFutureValidityInMinutes(tokenExpirationSeconds/60)
                    .setAllowedClockSkewInSeconds(30)
                    .setRequireSubject()
                    .setExpectedSubject("bookstore")
                    .setExpectedAudience("auth_user")
                    .setVerificationKey(new RsaJsonWebKey(JsonUtil.parseJson(tokenProperties.getPublicKey())).getPublicKey())
                    .build();
            
            return  consumer.processToClaims(token);
            
        } catch (InvalidJwtException | JoseException ex) {
            
            log.error(ex.getMessage());
        }
        return null;
    }

    public List<String> refreshToken(Object verifiedClaim){

        JwtClaims body = verifiedClaim != null? (JwtClaims)verifiedClaim: null;
        if(body != null){
           String accessToken = this.createToken(body.getClaimValue("userId"), 
                                                 body.getClaimValue("userType"), 
                                                 body.getClaimValue("loginName"), 
                                                 tokenProperties.getAccessExpirationSeconds());

           String refreshToken = this.createToken(body.getClaimValue("userId"), 
                                                 body.getClaimValue("userType"), 
                                                 body.getClaimValue("loginName"), 
                                                 tokenProperties.getRefreshExpirationSeconds());   
                                                 
           return Arrays.asList(accessToken,refreshToken);
        }
        
        return null;
    }
}
