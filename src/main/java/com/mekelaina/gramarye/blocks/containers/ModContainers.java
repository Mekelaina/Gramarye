package com.mekelaina.gramarye.blocks.containers;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Gramarye.MODID);

    public static final RegistryObject<ContainerType<ObeliskContainer>> OBELISK_CONTAINER = CONTAINERS.register("obelisk", () ->
    IForgeContainerType.create((((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new ObeliskContainer(windowId, Gramarye.proxy.getClientWorld(), pos, inv, Gramarye.proxy.getPlayerEntity());
    }))));
}
