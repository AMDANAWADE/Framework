package Utilities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class Log { //Initialize Log4j instance
    private static final Logger Log = LogManager.getLogger(Log.class);

    static {
        String filename = "C:\\My Learning\\Framework\\logs\\automation_log_" + System.currentTimeMillis() + ".log";
        initializeYourLogger(filename, "[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1} - %msg%n");

    }

    public static void info(String message) {
        Log.info(message);

    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error(String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        Log.debug(message);
    }

    public static void initializeYourLogger(String fileName, String pattern) {

        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setStatusLevel(Level.INFO);
        builder.setConfigurationName("DefaultFileLogger");

// set the pattern layout and pattern
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", pattern);

// create a file appender
        AppenderComponentBuilder appenderBuilder = builder.newAppender("LogToFile", "File")
                .addAttribute("fileName", fileName)
                .add(layoutBuilder);

        builder.add(appenderBuilder);
        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);

        rootLogger.add(builder.newAppenderRef("LogToFile"));
        builder.add(rootLogger);
        Configurator.reconfigure(builder.build());
    }
}


