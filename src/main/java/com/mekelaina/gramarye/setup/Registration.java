package com.mekelaina.gramarye.setup;


import com.mekelaina.gramarye.blocks.ModBlocks;
import net.minecraftforge.eventbus.api.IEventBus;

public class Registration {

    public static void init(IEventBus bus){
        ModBlocks.BLOCKS.register(bus);
    }

}
