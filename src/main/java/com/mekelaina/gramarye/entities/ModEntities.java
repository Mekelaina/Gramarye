package com.mekelaina.gramarye.entities;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.registry.*;

/**
 * Class for registering my custom entities. Thanks to Electroblob from where most of this code is from
 */
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Gramarye.MODID);

    public static final RegistryObject<EntityType<SpellBoltEntity>> SPELLBOLT = ENTITY_TYPES.register("spellbolt", () ->
            EntityType.Builder.<SpellBoltEntity>create(SpellBoltEntity::new, EntityClassification.MISC)
            .size(1, 1)
            .build(new ResourceLocation(Gramarye.MODID, "spellbolt").toString()));

    private ModEntities(){};

    enum TrackingType {

        LIVING(80, 3, true),
        PROJECTILE(64, 10, true);

        int range;
        int interval;
        boolean trackVelocity;

        TrackingType(int range, int interval, boolean trackVelocity){
            this.range = range;
            this.interval = interval;
            this.trackVelocity = trackVelocity;
        }
    }

    private static int ID = 0;



}
