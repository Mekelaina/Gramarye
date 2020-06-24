package com.mekelaina.gramarye.setup;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.containers.ModContainers;
import com.mekelaina.gramarye.blocks.screens.ObeliskScreen;
import com.mekelaina.gramarye.entities.DefaultSpellBoltRenderer;
import com.mekelaina.gramarye.entities.SpellBoltEntity;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry2;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Gramarye.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainers.OBELISK_CONTAINER.get(), ObeliskScreen::new);

        //RenderingRegistry.registerEntityRenderingHandler(SpellBoltEntity.class, DefaultSpellBoltRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {

    }
}
