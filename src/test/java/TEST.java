import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.basic.*;
import com.automationanywhere.botcommand.samples.commands.utils.AES;
import org.testng.annotations.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jws;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import java.security.SecureRandom;
import java.time.Instant;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.automationanywhere.botcommand.data.Value;
import org.apache.commons.codec.binary.Hex;

import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;


public class TEST {



    public void cmd()throws Exception{

        String command = "powershell -command \"get-appxpackage\"";

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        String text = "";
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            text += "\n" + line;
            //System.out.println(line);
        }
        System.out.println(text);

    }

    @Test
    public void jwt(){

        CreateJwt a = new CreateJwt();

        Map<String, Value> dict = new HashMap<>();
        dict.put("case_number",new StringValue("60-160590337"));
        String token = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf444";
        token=         "2F078FF4F34A3C1D59A3DD1EAC85E629C92800814BB442E81AA36684";
        byte[] aa = Base64.getDecoder().decode(token);

        String jwt = a.action(dict,true,1.0,true,token).toString();

        System.out.println(jwt);

    }


    public void generate_password(){
        randomPassword a = new randomPassword();

        StringValue v = a.action(2.0,3.0,5.0,1.0);

        System.out.println(v.toString());

    }

    //@Test
    public void AesPassword(){
        AesEncrypt aei = new AesEncrypt();
        AesDecrypt aed = new AesDecrypt();

        String key = "bjT3QZwTHBL0fHV2Kdv7tw==";
        String value = "Yviqx09!";


        //String encrypted = AES.encrypt(value,key);
        String encrypted = aei.action(value,key).toString();
        System.out.println("Encrypted:" + encrypted);
        //String decrypted = AES.decrypt(encrypted,key);
        String decrypted = aed.action(encrypted,key).toString();
        System.out.println("Decrypted:" + decrypted);


    }


    public void testEncode(){
        TextToHmacSHA256 a = new TextToHmacSHA256();

        alert(a.action("1234","1234","bASE64").toString());
    }


    public void encode(){
        try {
            alert("qsdasdas");
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        }catch (Exception e){
            alert(e.getMessage());
        }
    }

    //@Test
    public void aes256gcm_Decrypt() throws Exception {

        String key = "xAeTIUoQBCNFgRIUJ196YDxrxnFY7cJq";

        //====================2704===========================
//        String content = "7cf5fb74";
//        String IV = "762ced01457353f8";
//        int[] tag = {157,209,233,176,192,54,138,84,233,139,17,233,153,241,53,47};
//        String contentType = "Hex";

//        //====================4194590035780053===========================
//        String content = "959af809ba72be57a5df33217856ee0b";
//        String IV = "a218f7e79df9b6dc";
//        int[] tag = {6,212,176,34,109,1,7,9,211,7,232,241,168,101,183,245};
//        String contentType = "Hex";

        //====================4701540004597091===========================
        String content = "18b2d6e44decc25136fa5267231b4677";
        String IV = "985146f89d84bf84";
        int[] tag = {41,3,218,120,96,18,251,202,75, 105,117, 124, 189, 233, 158, 134};
        String contentType = "Hex";



        //System.out.println("content: " + content);

        //====================hehe===========================
//        key = "xAeTIUoQBCNFgRIUJ196YDxrxnFY7cJq";
//        String content = "zHaAVK1Au2BFmNv0SLn6E08Je60=";
//        String IV = "J4lCh0gEW//asLj1";
//        int[] tag = {};
//        String contentType = "Base64";

        //==================== hehojpkasinjhda896723bh c 89qt 968asvkasfifsde ===========================
//        key = "qw3ynpTz8gACa95LLCXjdQ==";
//        String content = "k6ITt5bWXPuUmiI8XI8ksoTgEL5ZhUP4tgvsQtC7xWp8eiWQLc4hXnFoH8QeGYq3xa/h+RnvdGx4z4p2AcM=";
//        String IV = "kVW00rbwvQJ+ejrv";
//        int[] tag = {};
//        String contentType = "Base64";

        //============================================== CODE ==============================================
        aes256gcm_Decrypt act = new aes256gcm_Decrypt();

        String nTag = intArrToHex(tag);
        System.out.println("Hex:" + nTag + "-");

        //byte[] cipherText = encrypt(content.getBytes(), key, IV.getBytes());
        //String encryptedText = Base64.getEncoder().encodeToString(cipherText);
        //System.out.println("Encrypted Text : " + encryptedText);

        StringValue decryptedText = act.action(contentType,content,true,nTag,key,IV);
        System.out.println("DeCrypted Text : " + decryptedText);
    }


    //@Test
    public void aes256gcm() throws Exception {

        String value      = "hehojpkasinjhda896723bh c 89qt 968asvkasfifsde";

        Boolean isKey   = false;
        String key      = "xAeTIUoQBCNFgRIUJ196YDxrxnFY7cJq";


        //============================== KEY ===============================
        byte[] b_key = null;

        if(!isKey){
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey keyG = keyGenerator.generateKey();
            key = Base64.getEncoder().encodeToString(keyG.getEncoded());
            b_key = key.getBytes(StandardCharsets.UTF_8);

        }else{
            b_key = key.getBytes(StandardCharsets.UTF_8);
        }


        //============================== IV ================================
        byte[] b_iv             = new byte[12];
        SecureRandom random     = new SecureRandom();
        random.nextBytes(b_iv);
        String iv               = Base64.getEncoder().encodeToString(b_iv);
        b_iv                    = iv.getBytes(StandardCharsets.UTF_8);

        byte[] cipherText = encrypt(value.getBytes(), b_key, b_iv,16);


        String enc = Base64.getEncoder().encodeToString(cipherText);

        System.out.println("Original Text:\t" + value);
        System.out.println("Key: \t\t\t" + key);
        System.out.println("IV: \t\t\t" + iv);
        System.out.println("Encrypted:\t\t" + enc);

        byte[] decodedString = Base64.getDecoder().decode(new String(enc).getBytes("UTF-8"));


        //String decryptedText = decrypt(decodedString, b_key, b_iv);
        //System.out.println("DeCrypted Text : " + decryptedText);

        aes256gcm_Decrypt act = new aes256gcm_Decrypt();
        StringValue decryptedText = act.action("Base64",enc,false,null,key,iv);
        System.out.println("DeCrypted Text : " + decryptedText);

    }


    public void original()throws Exception{

        String plainText = "This is a plain text which need to be encrypted by Java AES 256 GCM";
        String key      = "xAeTIUoQBCNFgRIUJ196YDxrxnFY7cJq";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        // Generate Key
        byte[] IV = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        System.out.println("Original Text : " + plainText);

        byte[] cipherText = encrypt(plainText.getBytes(), key.getBytes(), IV,16);
        System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));

        //String decryptedText = decrypt(cipherText, key.getBytes(), IV);
        //System.out.println("DeCrypted Text : " + decryptedText);
    }

    public static String intArrToHex(int[] arr) {
        StringBuilder builder = new StringBuilder(arr.length * 8);
        for (int b : arr) {
            String l = Integer.toHexString(b);
            builder.append(l.length() == 1?"0"+l:l);
        }
        return builder.toString();
    }

    public static String ByteArrToHex(byte  [] arr) {
        StringBuilder builder = new StringBuilder(arr.length * 8);
        for (byte b : arr) {
            String l = Integer.toHexString(b);
            builder.append(l.length() == 1?"0"+l:l);
        }
        return builder.toString();
    }

    public static byte[] encrypt(byte[] plaintext, byte[] key, byte[] IV,int TagSize) throws Exception
    {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TagSize * 8, IV);

        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        return cipherText;
    }

    public static String decrypt(String cipherText, byte[] key, byte[] IV) throws Exception
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
        byte[] decryptedText = cipher.doFinal(cipherText.getBytes());

        return new String(decryptedText);
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

    private void alert(String text){
        JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}

