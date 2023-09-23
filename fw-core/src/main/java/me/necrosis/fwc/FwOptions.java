package me.necrosis.fwc;

import lombok.*;

/**
 * Options for framework
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FwOptions {

    @Builder.Default
    private final boolean autoStart = true;

    public static FwOptions getDefault() {
        return new FwOptions();
    }
}
