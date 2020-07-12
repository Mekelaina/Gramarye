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
    //Blocks

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gramarye.MODID);

    public static final RegistryObject<Block> CRYSTALBLOCK = BLOCKS.register("crystalblock", () ->
            new CrystalBlock(Block.Properties.create(Material.GLASS).notSolid().hardnessAndResistance(0.3f, 3.0f)
            .sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(0).lightValue(7)));

    public static final RegistryObject<Block> BROADCASTER = BLOCKS.register("broadcaster", () ->
            new Broadcaster(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).harvestLevel(1)
            .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));

    public static final RegistryObject<Block> CHARGER = BLOCKS.register("charger", () ->
            new Charger(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));

    public static final RegistryObject<Block> CRYSTALLIZER = BLOCKS.register("crystallizer", () ->
            new Crystallizer(Block.Properties.create(Material.IRON).hardnessAndResistance(3f).harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).notSolid()));

    public static final RegistryObject<Block> OBELISK = BLOCKS.register("obelisk", () ->
            new Obelisk(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid()));

    public static final RegistryObject<Block> SHRINE = BLOCKS.register("shrine", () ->
            new Shrine(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.8f).harvestLevel(0)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid()));

    public static final RegistryObject<Block> EMBUINGPILLAR = BLOCKS.register("embuing_pillar", () ->
            new EmbuingPillar(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid()));

    public static final RegistryObject<Block> EMBUINGTABLE = BLOCKS.register("embuing_table", () ->
            new EmbuingTable(Block.Properties.create(Material.ROCK).hardnessAndResistance(5f, 1200f).harvestLevel(0)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SPELLTABLE = BLOCKS.register("spell_table", ()->
            new SpellTable(Block.Properties.create(Material.ROCK).hardnessAndResistance(5f, 1200f).harvestLevel(0)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)));

    public static final RegistryObject<Block> TESSERACT = BLOCKS.register("tesseract", ()->
            new Tesseract(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f).harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid()));

    public static final RegistryObject<Block> BATTERY = BLOCKS.register("battery", ()->
            new Battery(Block.Properties.create(Material.IRON).hardnessAndResistance(3f).harvestLevel(1)
            .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)));

    public static final RegistryObject<Block> VOIDBLOCK = BLOCKS.register("voidblock", ()->
            new Block(Block.Properties.create(Material.BARRIER).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));


}
