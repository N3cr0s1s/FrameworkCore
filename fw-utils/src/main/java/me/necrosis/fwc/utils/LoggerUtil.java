package me.necrosis.fwc.utils;

import org.slf4j.Logger;

import java.util.function.Consumer;
import java.util.function.Function;
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

}
