package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.Boolean;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class aes256gcm_DecryptCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(aes256gcm_DecryptCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    aes256gcm_Decrypt command = new aes256gcm_Decrypt();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("ContentType") && parameters.get("ContentType") != null && parameters.get("ContentType").get() != null) {
      convertedParameters.put("ContentType", parameters.get("ContentType").get());
      if(convertedParameters.get("ContentType") !=null && !(convertedParameters.get("ContentType") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","ContentType", "String", parameters.get("ContentType").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("ContentType") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","ContentType"));
    }
    if(convertedParameters.get("ContentType") != null) {
      switch((String)convertedParameters.get("ContentType")) {
        case "Hex" : {

        } break;
        case "Base64" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","ContentType"));
      }
    }

    if(parameters.containsKey("Content") && parameters.get("Content") != null && parameters.get("Content").get() != null) {
      convertedParameters.put("Content", parameters.get("Content").get());
      if(convertedParameters.get("Content") !=null && !(convertedParameters.get("Content") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Content", "String", parameters.get("Content").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("Content") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Content"));
    }

    if(parameters.containsKey("isTag") && parameters.get("isTag") != null && parameters.get("isTag").get() != null) {
      convertedParameters.put("isTag", parameters.get("isTag").get());
      if(convertedParameters.get("isTag") !=null && !(convertedParameters.get("isTag") instanceof Boolean)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","isTag", "Boolean", parameters.get("isTag").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("isTag") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","isTag"));
    }
    if(convertedParameters.get("isTag") != null && (Boolean)convertedParameters.get("isTag")) {
      if(parameters.containsKey("Tag") && parameters.get("Tag") != null && parameters.get("Tag").get() != null) {
        convertedParameters.put("Tag", parameters.get("Tag").get());
        if(convertedParameters.get("Tag") !=null && !(convertedParameters.get("Tag") instanceof String)) {
          throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Tag", "String", parameters.get("Tag").get().getClass().getSimpleName()));
        }
      }
      if(convertedParameters.get("Tag") == null) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Tag"));
      }

    }

    if(parameters.containsKey("Key") && parameters.get("Key") != null && parameters.get("Key").get() != null) {
      convertedParameters.put("Key", parameters.get("Key").get());
      if(convertedParameters.get("Key") !=null && !(convertedParameters.get("Key") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Key", "String", parameters.get("Key").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("Key") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Key"));
    }

    if(parameters.containsKey("Iv") && parameters.get("Iv") != null && parameters.get("Iv").get() != null) {
      convertedParameters.put("Iv", parameters.get("Iv").get());
      if(convertedParameters.get("Iv") !=null && !(convertedParameters.get("Iv") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Iv", "String", parameters.get("Iv").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("Iv") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Iv"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("ContentType"),(String)convertedParameters.get("Content"),(Boolean)convertedParameters.get("isTag"),(String)convertedParameters.get("Tag"),(String)convertedParameters.get("Key"),(String)convertedParameters.get("Iv")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
