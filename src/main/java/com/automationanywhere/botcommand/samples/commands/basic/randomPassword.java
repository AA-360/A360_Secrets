package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


@BotCommand
@CommandPkg(
        label = "randomPassword",
        description = "Generate a random password",
        icon = "pkg.svg",
        name = "randomPassword",
        return_description = "",
        return_type = STRING,
        return_required = true
)


public class randomPassword {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = NUMBER)
            @Pkg(label = "Special Char",description = "Number of special chars")
            @NotEmpty
                    Double numSpecial,
            @Idx(index = "2", type = NUMBER)
            @Pkg(label = "Upper Char",description = "Number of upper chars")
            @NotEmpty
                    Double numUpper,
            @Idx(index = "3", type = NUMBER)
            @Pkg(label = "Lower Char",description = "Number of lower chars")
            @NotEmpty
                    Double numlower,
            @Idx(index = "4", type = NUMBER)
            @Pkg(label = "Number Char",description = "Number of number chars")
            @NotEmpty
                    Double numNumbers
    ) {
        String special = "\\.[]{}()<>*+-=!?^$|";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";


        String pwd = randChar(special,numSpecial.intValue()) + randChar(upper,numUpper.intValue()) + randChar(lower,numlower.intValue()) + randChar(numbers,numNumbers.intValue());

        return new StringValue(pwd);
    }

    private String randChar(String text,int qtd){
        String ret = "";
        for(int i=0;i<qtd;i++){
            ret+=text.toCharArray()[(int) (Math.random() * text.length())];
        }
        return ret;
    }


}
