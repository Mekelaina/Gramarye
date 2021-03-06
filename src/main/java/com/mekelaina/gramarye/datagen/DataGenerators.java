package com.mekelaina.gramarye.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        if(event.includeServer()) {
            dataGenerator.addProvider(new LootTables(dataGenerator));
        }
        if(event.includeClient()) {
            dataGenerator.addProvider(new SpellBookModelProvider(dataGenerator, event.getExistingFileHelper()));
            dataGenerator.addProvider(new SpellBookLanguageProvider(dataGenerator, "en_us"));
        }
    }
}
