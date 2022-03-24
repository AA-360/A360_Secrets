package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Double;
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

public final class randomPasswordCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(randomPasswordCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    randomPassword command = new randomPassword();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("numSpecial") && parameters.get("numSpecial") != null && parameters.get("numSpecial").get() != null) {
      convertedParameters.put("numSpecial", parameters.get("numSpecial").get());
      if(convertedParameters.get("numSpecial") !=null && !(convertedParameters.get("numSpecial") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","numSpecial", "Double", parameters.get("numSpecial").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("numSpecial") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","numSpecial"));
    }

    if(parameters.containsKey("numUpper") && parameters.get("numUpper") != null && parameters.get("numUpper").get() != null) {
      convertedParameters.put("numUpper", parameters.get("numUpper").get());
      if(convertedParameters.get("numUpper") !=null && !(convertedParameters.get("numUpper") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","numUpper", "Double", parameters.get("numUpper").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("numUpper") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","numUpper"));
    }

    if(parameters.containsKey("numlower") && parameters.get("numlower") != null && parameters.get("numlower").get() != null) {
      convertedParameters.put("numlower", parameters.get("numlower").get());
      if(convertedParameters.get("numlower") !=null && !(convertedParameters.get("numlower") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","numlower", "Double", parameters.get("numlower").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("numlower") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","numlower"));
    }

    if(parameters.containsKey("numNumbers") && parameters.get("numNumbers") != null && parameters.get("numNumbers").get() != null) {
      convertedParameters.put("numNumbers", parameters.get("numNumbers").get());
      if(convertedParameters.get("numNumbers") !=null && !(convertedParameters.get("numNumbers") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","numNumbers", "Double", parameters.get("numNumbers").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("numNumbers") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","numNumbers"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((Double)convertedParameters.get("numSpecial"),(Double)convertedParameters.get("numUpper"),(Double)convertedParameters.get("numlower"),(Double)convertedParameters.get("numNumbers")));
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
