/*******************************************************************************
 * Copyright (c) 2017 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.acmeair.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SecurityUtils {

  private static final Logger logger =  Logger.getLogger(SecurityUtils.class.getName());

  // TODO: Hardcode for now
  private static final String secretKey = "acmeairsecret128";

  /**
   *  Generate simple JWT with login as the Subject. 
   */
  public static String generateJwt(String customerid) {
    String token = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      token = JWT.create()
          .withSubject(customerid)
          .sign(algorithm);
    } catch (Exception exception) {
      exception.printStackTrace();
    } 

    return token;
  }

  /**
   *  Validate simple JWT. 
   */
  public static boolean validateJwt(String customerid, String jwtToken) {

    if (logger.isLoggable(Level.FINE)) {
      logger.fine("validate : customerid " + customerid);
    }

    try {
      
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build(); //Cache?

      DecodedJWT jwt = verifier.verify(jwtToken);
      return jwt.getSubject().equals(customerid);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return false;
  }
}
