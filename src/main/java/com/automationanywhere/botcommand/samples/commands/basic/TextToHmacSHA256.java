package com.automationanywhere.botcommand.samples.commands.basic;
import java.util.ArrayList;
import java.util.List;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.model.DataType;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.platform.win32.User32;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.*;

import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.automationanywhere.commandsdk.model.AttributeType.SELECT;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.LIST;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


@BotCommand
@CommandPkg(
        label = "TextToHmacSHA256",
        description = "Encript a text to HmacSHA256",
        icon = "pkg.svg",
        name = "TextToHmacSHA256",
        return_description = "",
        return_type = STRING,
        return_required = true
)


public class TextToHmacSHA256 {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Value",description = "the value that will be used to generate the hash")
            @NotEmpty
                    String value,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Key",description = "the key that will be used to generate the hash")
            @NotEmpty
                    String key,
            @Idx(index = "3", type = SELECT, options = {
                    @Idx.Option(index = "3.1", pkg = @Pkg(label = "String", value = "String")),
                    @Idx.Option(index = "3.2", pkg = @Pkg(label = "Base64", value = "Base64"))})
            @Pkg(label = "OutPut Format:", description = "the hash code will be converted to according output", default_value = "String", default_value_type = DataType.STRING)
            @NotEmpty
                    String OutputType

    ) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            String ret = Hex.encodeHexString(sha256_HMAC.doFinal(value.getBytes("UTF-8")));
            String ret2 = Base64.encodeBase64String(sha256_HMAC.doFinal(value.getBytes("UTF-8")));

            return new StringValue(OutputType.equals("String")?ret:ret2);
        } catch (Exception e) {
            throw new BotCommandException(e.getMessage());
        }
    }


}
