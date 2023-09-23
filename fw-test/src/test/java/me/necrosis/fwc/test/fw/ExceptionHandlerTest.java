package me.necrosis.fwc.test.fw;

import me.necrosis.fwc.core.frameworks.ExceptionHandlerFramework;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionHandlerTest {

    private static String currentException;

    @Test
    public void testExceptionHandler() {
        final String exceptionMessage = "TestMessage";

        new ExceptionHandlerFramework(() ->{
            throw new RuntimeException(exceptionMessage);
        },(t,e) -> currentException = e.getException().getMessage());

        Assertions.assertEquals(exceptionMessage,currentException);
    }

}
