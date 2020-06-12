package com.mekelaina.gramarye.blocks;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public ModBlocks() {

    }

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Gramarye.MODID);

    public static final RegistryObject<Block> CRYSTAL = BLOCKS.register("crystalblock", () ->
            new CrystalBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3f, 3.0f)
            .sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(0)));
}
