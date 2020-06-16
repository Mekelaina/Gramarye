package com.mekelaina.gramarye.client.model.armor;

import net.minecraft.util.ResourceLocation;

public class GramaryanArmorBoots extends GramaryanArmorBase {

    public GramaryanArmorBoots() {
        super(128, 128, new ResourceLocation("gramarye:textures/model/gramaryan_armor.png"));
    }

    @Override
    public void setupArmorParts() {
        /*armorRightBoot.setTextureOffset(45, 2).addBox(-3.312F, 7.225F, -2.4022F, 4, 5, 5, 0.0F, false);
        armorLeftBoot.setTextureOffset(51, 51).addBox(-3.112F, 7.225F, -2.4022F, 4, 5, 5, 0.0F, false);*/

        armorLeftBoot.setTextureOffset(51, 51).addBox(-5.112F, 19F, -2.4022F, 4, 5, 5, 0.0F, false);
        armorRightBoot.setTextureOffset(45, 2).addBox(-2.312F, 19f, -2.4022F, 4, 5, 5, 0.0F, false);
    }
}