package com.mekelaina.gramarye.blocks.tiles;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Gramarye.MODID);

    public static final RegistryObject<TileEntityType<ObeliskTile>> OBELISK_TILE = TILE_ENTITIES.register("obelisk", ()->
            TileEntityType.Builder.create(ObeliskTile::new, ModBlocks.OBELISK.get()).build(null));
}
