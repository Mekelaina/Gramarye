package com.mekelaina.gramarye.client.model.armor;

import net.minecraft.util.ResourceLocation;

public class GramaryanArmorCirclet extends GramaryanArmorBase {

    public GramaryanArmorCirclet() {
        super(128, 128, new ResourceLocation("gramarye:textures/model/gramaryan_armor.png"));

    }

    @Override
    public void setupArmorParts() {

        bipedHead.setTextureOffset(0, 0).addBox(-4.962F, -8.0F, -3.9022F, 8, 8, 8, 0.0F, false);

        /*armorHead.setTextureOffset(9, 24).addBox(-4.462F, -6.525F, 3.5978F, 7, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(11, 24).addBox(-4.462F, -6.525F, -4.4022F, 7, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(0, 16).addBox(-5.462F, -6.525F, -4.4022F, 1, 1, 9, 0.0F, false);
        armorHead.setTextureOffset(0, 16).addBox(2.538F, -6.525F, -4.4022F, 1, 1, 9, 0.0F, false);
        armorHead.setTextureOffset(0, 16).addBox(-2.462F, -5.525F, -4.4022F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(26, 28).addBox(-5.462F, -7.525F, -3.4022F, 1, 1, 7, 0.0F, false);
        armorHead.setTextureOffset(32, 9).addBox(2.538F, -7.525F, -3.4022F, 1, 1, 7, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(0.538F, -7.525F, -4.4022F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(-5.462F, -7.525F, -4.4022F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(0.538F, -7.525F, 3.5978F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(-5.462F, -7.525F, 3.5978F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(74, 0).addBox(-1.75F, -6.275F, -4.5F, 1, 1, 1, 0.0F, false);*/

        armorHead.setTextureOffset(9, 24).addBox(-3.462F, -6.525F, 3.5978F, 7, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(11, 24).addBox(-3.462F, -6.525F, -4.4022F, 7, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(0, 16).addBox(-4.462F, -6.525F, -4.4022F, 1, 1, 9, 0.0F, false);
        armorHead.setTextureOffset(0, 16).addBox(3.538F, -6.525F, -4.4022F, 1, 1, 9, 0.0F, false);
        armorHead.setTextureOffset(0, 16).addBox(-1.462F, -5.525F, -4.4022F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(26, 28).addBox(-4.462F, -7.525F, -3.4022F, 1, 1, 7, 0.0F, false);
        armorHead.setTextureOffset(32, 9).addBox(3.538F, -7.525F, -3.4022F, 1, 1, 7, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(1.538F, -7.525F, -4.4022F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(-4.462F, -7.525F, -4.4022F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(1.538F, -7.525F, 3.5978F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(35, 15).addBox(-4.462F, -7.525F, 3.5978F, 3, 1, 1, 0.0F, false);
        armorHead.setTextureOffset(74, 0).addBox(-0.75F, -6.275F, -4.5F, 1, 1, 1, 0.0F, false);

    }
}
