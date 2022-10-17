package com.example.springjwt.utils;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.springjwt.constant.SecurityConstants;

public class AlgorithmUtils {

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SecurityConstants.AUTH_SECRET.getBytes());
    }

}
