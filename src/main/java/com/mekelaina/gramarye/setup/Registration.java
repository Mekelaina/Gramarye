package com.mekelaina.gramarye.setup;


import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.Spell.SpellSetup;
import com.mekelaina.gramarye.blocks.ModBlocks;
import com.mekelaina.gramarye.gui.containers.ModContainers;
import com.mekelaina.gramarye.blocks.tiles.ModTileEntities;
import com.mekelaina.gramarye.entities.ModEntities;
import com.mekelaina.gramarye.items.ModItems;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class Registration {

    public static void init(IEventBus bus){
        SpellSetup.registerSpellBooks();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModTileEntities.TILE_ENTITIES.register(bus);
        ModContainers.CONTAINERS.register(bus);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(Gramarye.setup.itemGroup);
                    final BlockItem blockItem = new BlockItem(block, properties);
                    blockItem.setRegistryName(block.getRegistryName());
                    registry.register(blockItem);
                });
        Gramarye.LOGGER.debug("Registered BlockItems");
    }
}
