package com.mekelaina.gramarye.setup;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = Gramarye.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {

    }
}
