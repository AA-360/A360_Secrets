package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.AES;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


@BotCommand
@CommandPkg(
        label = "AesEncrypt",
        description = "Encrypts an value to AES format",
        icon = "pkg.svg",
        name = "AesEncrypt",
        return_description = "",
        return_type = STRING,
        return_required = true
)


public class AesEncrypt {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Value",description = "the value that will be used to generate the hash")
            @NotEmpty
                    String value,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Key",description = "the key that will be used to generate the hash")
            @NotEmpty
                    String key
    ) {
        try {
            String encrypted = AES.encrypt(value,key);
            return new StringValue(encrypted);
        } catch (Exception e) {
            throw new BotCommandException(e.getMessage());
        }
    }


}
