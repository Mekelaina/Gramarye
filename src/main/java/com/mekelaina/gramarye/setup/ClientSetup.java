package com.mekelaina.gramarye.setup;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.ModBlocks;
import com.mekelaina.gramarye.gui.containers.ModContainers;
import com.mekelaina.gramarye.gui.screens.ObeliskScreen;
import com.mekelaina.gramarye.blocks.tiles.ModTileEntities;
import com.mekelaina.gramarye.client.render.ChargerTileEntityRenderer;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Gramarye.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainers.OBELISK_CONTAINER.get(), ObeliskScreen::new);

        //RenderingRegistry.registerEntityRenderingHandler(SpellBoltEntity.class, DefaultSpellBoltRenderer::new);

        ClientRegistry.bindTileEntityRenderer(ModTileEntities.CHARGER_TILE.get(), ChargerTileEntityRenderer::new);

    }

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.CRYSTALBLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.BATTERY.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.BROADCASTER.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.CRYSTALLIZER.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.CHARGER.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.EMBUINGPILLAR.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.OBELISK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.SPELLTABLE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.TESSERACT.get(), RenderType.getTranslucent());
    }
}
