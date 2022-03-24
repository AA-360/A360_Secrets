package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


@BotCommand
@CommandPkg(
        label = "aes256gcm_Decrypt",
        description = "Decript data using Content,IV,Key and/or Tag\n All data instead Content should be used as Hex input!",
        icon = "pkg.svg",
        name = "aes256gcm_Decrypt",
        return_description = "",
        return_type = STRING,
        return_required = true
)


public class aes256gcm_Decrypt {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = SELECT, options = {
                    @Idx.Option(index = "1.1", pkg = @Pkg(label = "Hex", value = "Hex")),
                    @Idx.Option(index = "1.2", pkg = @Pkg(label = "Base64", value = "Base64"))})
            @Pkg(label = "Content Format Input:", description = "", default_value = "Base64", default_value_type = DataType.STRING)
            @NotEmpty
                    String ContentType,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Content",description = "the value that will be decrypted")
            @NotEmpty
                    String Content,
            @Idx(index = "3", type = CHECKBOX)
            @Pkg(label = "Tag:",description = "If tag value is not included in Content value inform it here!",default_value = "false",default_value_type = DataType.BOOLEAN)
            @NotEmpty
                    Boolean isTag,
            @Idx(index = "3.1", type = TEXT)
            @Pkg(label = "Tag:",description = "the tag value that will be used to decrypt content")
            @NotEmpty
                    String Tag,
            @Idx(index = "4", type = TEXT)
            @Pkg(label = "Key",description = "the key that will be used to decrypt content")
            @NotEmpty
                    String Key,
            @Idx(index = "5", type = TEXT)
            @Pkg(label = "Iv",description = "the iv that will be used to decrypt content")
            @NotEmpty
                    String Iv
    ) {
        try {
            byte[] decodedString = null;

            if(isTag){
                Content += Tag;
            }
            if(ContentType.equals("Base64")) {
                decodedString = Base64.getDecoder().decode(new String(Content).getBytes("UTF-8"));
            }else{
                decodedString = Hex.decodeHex(Content);
            }

            String decryptedText = decrypt(decodedString, Key.getBytes(StandardCharsets.UTF_8), Iv.getBytes(StandardCharsets.UTF_8));
            //String decryptedText = decrypt(Content.getBytes(), Key.getBytes(), Iv.getBytes());

            return new StringValue(decryptedText);
        } catch (Exception e) {
            throw new BotCommandException(e.getMessage());
        }



    }

    public static String intArrToHex(int[] arr) {
        StringBuilder builder = new StringBuilder(arr.length * 8);
        for (int b : arr) {
            String l = Integer.toHexString(b);
            builder.append(l.length() == 1?"0"+l:l);
        }
        return builder.toString();
    }

    public static byte[] encrypt(byte[] plaintext, byte[] key, byte[] IV) throws Exception
    {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(16 * 8, IV);

        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        return cipherText;
    }
    public static String decrypt(byte[] cipherText, byte[] key, byte[] IV) throws Exception
    {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");


        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(16 * 8, IV);


        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Decryption
        byte[] decryptedText = cipher.doFinal(cipherText);

        return new String(decryptedText);
    }



}
