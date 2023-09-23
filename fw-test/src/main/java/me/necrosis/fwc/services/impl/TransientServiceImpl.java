package me.necrosis.fwc.services.impl;

import me.necrosis.fwc.services.SingletonService;
import me.necrosis.fwc.services.TransientService;

import java.util.UUID;

public class TransientServiceImpl implements SingletonService {

    private final UUID uuid = UUID.randomUUID();

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
