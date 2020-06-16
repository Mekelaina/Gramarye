package com.mekelaina.gramarye.datagen;

import com.mekelaina.gramarye.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {


    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.OBELISK.get(), createStandardTable(ModBlocks.OBELISK.getId().getNamespace(), ModBlocks.OBELISK.get()));
    }
}
