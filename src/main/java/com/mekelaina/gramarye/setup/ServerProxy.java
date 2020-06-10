package com.mekelaina.gramarye.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public PlayerEntity getPlayerEntity() {
        throw new IllegalStateException("Only run this on the client!");
    }
}
