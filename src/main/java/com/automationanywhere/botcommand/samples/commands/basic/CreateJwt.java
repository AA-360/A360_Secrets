/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 *
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.core.security.SecureString;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.DICTIONARY;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import MaskFormatter;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.botcommand.data.Value;
//import java.Math;
//import Math;

@BotCommand
@CommandPkg(label = "CreateJwt",
        description = "This action creates a JWT token",
        icon = "pkg.svg",
        name = "CreateJwt",
        return_description = "",
        return_type = STRING,
        return_required = true
)


public class CreateJwt {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = AttributeType.DICTIONARY)
            @VariableType(DICTIONARY)
            @Pkg(label = "Fields:",description = "Key and value to be attached to the jwt token")
            @NotEmpty
                    Map<String, Value> dict,
            @Idx(index = "2", type = CHECKBOX)
            @Pkg(label = "Exp:",description = "Provide a expiration time",default_value = "false",default_value_type = DataType.BOOLEAN)
            @NotEmpty
                    Boolean bol_exp,
            @Idx(index = "2.1", type = NUMBER)
            @Pkg(label = "Minutes:",description = "will expire in the provided minutes")
            @NotEmpty
                    Double exp,
            @Idx(index = "3", type = CHECKBOX)
            @Pkg(label = "Token:",description = "Provide a token validator",default_value = "false",default_value_type = DataType.BOOLEAN)
            @NotEmpty
                    Boolean bol_tkn,
            @Idx(index = "3.1", type = TEXT)
            @Pkg(label = "Token:",description = "Provide a token validator")
            @NotEmpty
                    String token


    ) {
        JwtBuilder jwt = Jwts.builder();

        if(bol_tkn) {
            String tkn = Base64.getDecoder().decode(token).toString();
            Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(token), SignatureAlgorithm.HS256.getJcaName());
            jwt.signWith(hmacKey);
        }
        if(bol_exp){
            Instant now = Instant.now();
            Instant exp_inst = now.plus(exp.intValue(), ChronoUnit.MINUTES);
            jwt.setExpiration(Date.from(exp_inst));
        }

        for (Map.Entry<String, Value> entry : dict.entrySet()) {
            jwt.claim(entry.getKey(),  entry.getValue().toString());
        }

        return new StringValue(jwt.compact());
    }
}
