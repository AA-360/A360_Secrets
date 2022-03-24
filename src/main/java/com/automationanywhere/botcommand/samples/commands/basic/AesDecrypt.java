package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.AES;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


@BotCommand
@CommandPkg(
        label = "AesDecrypt",
        description = "Decrypts an AES format",
        icon = "pkg.svg",
        name = "AesDecrypt",
        return_description = "",
        return_type = STRING,
        return_required = true
)


public class AesDecrypt {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Hash",description = "The value that will be used to be decrypted")
            @NotEmpty
                    String hash,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Key",description = "The key that will be used to decrypt the hash")
            @NotEmpty
                    String key
    ) {
        try {
            String decrypted = AES.decrypt(hash,key);
            return new StringValue(decrypted);
        } catch (Exception e) {
            throw new BotCommandException(e.getMessage());
        }
    }


}
