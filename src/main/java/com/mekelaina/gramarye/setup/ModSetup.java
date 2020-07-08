package com.mekelaina.gramarye.setup;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.blocks.ModBlocks;
import com.mekelaina.gramarye.items.ModItems;
import com.mekelaina.gramarye.network.Networking;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Gramarye.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("gramarye") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.CRYSTAL.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event){
       // ModBlocks.BLOCKS.register();
        Networking.registerMessages();
    }
}
