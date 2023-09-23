package me.necrosis.fwc.utils;

import org.slf4j.Logger;

import java.util.function.Supplier;

public final class LoggerUtil {

    public static void processLogger(String processName, Logger logger, Runnable process) {
        logger.trace("{} started...",processName);
        process.run();
        logger.trace("{} done.",processName);
    }

    public static <T> T processLogger(String processName, Logger logger, Supplier<T> process) {
        logger.trace("{} started...",processName);
        T o = process.get();
        if (o == null) {
            logger.error("{} failed, because created object is null.",processName);
            return null;
        }
        logger.trace("{} done.",processName);
        return o;
    }

    public static void processTimeLogger(String processName, Logger logger, Runnable process) {
        logTime(processName,logger,() -> {
            logger.trace("{} started...",processName);
            process.run();
            logger.trace("{} done.",processName);
            return null;
        });
    }

    public static <T> T processTimeLogger(String processName, Logger logger, Supplier<T> process) {
        return logTime(processName,logger,() -> {
            logger.trace("{} started...",processName);
            T o = process.get();
            if (o == null) {
                logger.error("{} failed, because created object is null.",processName);
                return null;
            }
            logger.trace("{} done.",processName);
            return o;
        });
    }

    public static <T> T logTime(String processName,Logger logger,Supplier<T> process) {
        long l = System.currentTimeMillis();
        T t = process.get();
        logger.trace("- [\"{}\" took: {} ms]", processName,System.currentTimeMillis() - l);
        return t;
    }

}
