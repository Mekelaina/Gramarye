package com.mekelaina.gramarye.client.model.armor;

import net.minecraft.util.ResourceLocation;

public class GramaryanArmorLeggings extends GramaryanArmorBase {

    public GramaryanArmorLeggings() {
        super(128, 128, new ResourceLocation("gramarye:textures/model/gramaryan_armor.png"));
    }

    @Override
    public void setupArmorParts() {
        bipedLeftLeg.setTextureOffset(40, 40).addBox(-1.962F, -0.025F, -1.9022F, 4, 12, 4, 0.0F, false);

        armorRightLeg.setTextureOffset(61, 10).addBox(-1.562F, 2.475F, -2.4022F, 2, 4, 2, 0.0F, false);
        armorRightLeg.setTextureOffset(56, 47).addBox(-2.062F, 0.475F, -2.4022F, 3, 2, 2, 0.0F, false);
        armorRightLeg.setTextureOffset(64, 0).addBox(-2.062F, 0.475F, 1.5978F, 3, 6, 1, 0.0F, false);
        armorRightLeg.setTextureOffset(41, 44).addBox(-2.96F, -0.025F, -1.9272F, 4, 9, 0, 0.0F, false);
        armorRightLeg.setTextureOffset(40, 40).addBox(-2.962F, -0.025F, -1.9272F, 0, 9, 4, 0.0F, false);
        armorRightLeg.setTextureOffset(43, 40).addBox(1.05F, -0.025F, -1.927F, 0, 9, 4, 0.0F, false);
        armorRightLeg.setTextureOffset(47, 44).addBox(-2.962F, -0.025F, 2.0978F, 4, 9, 0, 0.0F, false);

        bipedRightLeg.setTextureOffset(24, 36).addBox(-1.962F, -0.025F, -1.9022F, 4, 12, 4, 0.0F, false);

        armorLeftLeg.setTextureOffset(59, 47).addBox(-2.862F, 0.475F, -2.4022F, 3, 2, 2, 0.0F, false);
        armorLeftLeg.setTextureOffset(63, 10).addBox(-2.862F, 2.475F, -2.4022F, 2, 4, 2, 0.0F, false);
        armorLeftLeg.setTextureOffset(61, 0).addBox(-2.862F, 0.475F, 1.5978F, 3, 6, 1, 0.0F, false);
        armorLeftLeg.setTextureOffset(48, 40).addBox(1.05F, -0.025F, -1.927F, 0, 9, 4, 0.0F, false);
        armorLeftLeg.setTextureOffset(50, 44).addBox(-2.962F, -0.025F, -1.9272F, 4, 9, 0, 0.0F, false);
        armorLeftLeg.setTextureOffset(48, 40).addBox(-2.962F, -0.025F, -1.9272F, 0, 9, 4, 0.0F, false);
        armorLeftLeg.setTextureOffset(48, 44).addBox(-2.96F, -0.025F, 2.0978F, 4, 9, 0, 0.0F, false);
    }
}
