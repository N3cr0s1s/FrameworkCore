package me.necrosis.fwc.core.entities;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Data
@Slf4j
@Builder
public class User {

    private final UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private int age;

}
