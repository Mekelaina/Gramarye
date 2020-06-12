package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Gramarye.MODID);

    public static final RegistryObject<Item> WAND = ITEMS.register("wand", ()->
            new Wand(new Item.Properties().maxStackSize(1).group(Gramarye.setup.itemGroup)));
}
