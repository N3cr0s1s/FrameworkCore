package me.necrosis.fwc.core.services.impl;

import me.necrosis.fwc.core.services.SingletonService;

import java.util.UUID;

public class TransientServiceImpl implements SingletonService {

    private final UUID uuid = UUID.randomUUID();

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
