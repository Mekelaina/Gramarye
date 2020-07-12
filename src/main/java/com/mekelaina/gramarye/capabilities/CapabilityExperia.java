package com.mekelaina.gramarye.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CapabilityExperia {

    @CapabilityInject(Experia.class)
    public static Capability<Experia> CAPABILITY_EXPERIA = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(Experia.class, new Experia.ExperiaNBTStorage(), Experia::createDefaultInstance);
    }
}
