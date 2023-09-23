package me.necrosis.fwc;

import lombok.*;

/**
 * Options for framework
 */
@Data
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FwOptions {


    @Builder.Default
    private final boolean autoInit = true;

    @Builder.Default
    private final boolean autoStart = true;

    public static FwOptions getDefault() {
        return new FwOptions();
    }

    public static FwOptionsBuilder builder() {
        return new FwOptionsBuilder();
    }
}
