package com.mekelaina.gramarye.client.model.armor;

import net.minecraft.util.ResourceLocation;

public class GramaryanArmorChestpiece extends GramaryanArmorBase {

    public GramaryanArmorChestpiece() {
        super(128, 128, new ResourceLocation("gramarye:textures/model/gramaryan_armor.png"));
    }

    @Override
    public void setupArmorParts() {

        bipedBody.setTextureOffset(0, 26).addBox(-3.962F, -0.025F, -1.9022F, 8, 12, 4, 0.0F, false);

        armorBody.setTextureOffset(27, 17).addBox(-3.962F, -0.525F, -2.4022F, 8, 3, 5, 0.0F, false);
        armorBody.setTextureOffset(0, 4).addBox(-1.462F, 2.475F, -2.4022F, 1, 1, 3, 0.0F, false);
        armorBody.setTextureOffset(0, 0).addBox(0.538F, 2.475F, -2.4022F, 1, 1, 3, 0.0F, false);
        armorBody.setTextureOffset(24, 0).addBox(-3.962F, 10.475F, -2.4022F, 8, 2, 5, 0.0F, false);
        armorBody.setTextureOffset(56, 61).addBox(-1.212F, 10.475F, -2.6522F, 2, 2, 5, 0.0F, false);
        armorBody.setTextureOffset(29, 56).addBox(-2.962F, 9.225F, -1.4022F, 4, 3, 5, 0.0F, false);
        armorBody.setTextureOffset(8, 27).addBox(4.063F, -0.025F, -1.9272F, 0, 10, 4, 0.0F, false);
        armorBody.setTextureOffset(8, 27).addBox(-3.962F, -0.025F, -1.9522F, 0, 10, 4, 0.0F, false);
        armorBody.setTextureOffset(4, 33).addBox(-3.987F, 1.975F, -1.9272F, 8, 8, 0, 0.0F, false);
        armorBody.setTextureOffset(4, 33).addBox(-3.962F, 1.975F, 2.0978F, 8, 8, 0, 0.0F, false);
        armorBody.setTextureOffset(27, 2).addBox(4.063F, 10.475F, -1.9022F, 0, 1, 4, 0.0F, false);
        armorBody.setTextureOffset(27, 2).addBox(-3.962F, 10.475F, -1.9022F, 0, 1, 4, 0.0F, false);

        bipedRightArm.setTextureOffset(0, 42).addBox(-0.962F, -2.025F, -1.9022F, 4, 12, 4, 0.0F, false);

        armorLeftArm.setTextureOffset(56, 37).addBox(1.288F, -2.525F, -2.4022F, 2, 5, 5, 0.0F, false);
        armorLeftArm.setTextureOffset(42, 59).addBox(-0.962F, -2.525F, -2.4022F, 2, 4, 5, 0.0F, false);
        armorLeftArm.setTextureOffset(48, 12).addBox(-1.212F, 6.225F, -2.4022F, 4, 4, 5, 0.0F, false);
        armorLeftArm.setTextureOffset(0, 43).addBox(-0.975F, -2.05F, -1.9022F, 0, 8, 3, 0.0F, false);
        armorLeftArm.setTextureOffset(5, 47).addBox(-0.975F, -2.05F, -1.91F, 4, 8, 0, 0.0F, false);
        armorLeftArm.setTextureOffset(5, 47).addBox(-0.975F, -2.05F, 2.1F, 4, 8, 0, 0.0F, false);
        armorLeftArm.setTextureOffset(6, 44).addBox(3.05F, -1.55F, -1.9022F, 0, 8, 3, 0.0F, false);

        bipedLeftArm.setTextureOffset(49, 21).addBox(-2.962F, -2.025F, -1.9022F, 4, 12, 4, 0.0F, false);

        armorRightArm.setTextureOffset(16, 52).addBox(-3.212F, 6.225F, -2.4022F, 4, 4, 5, 0.0F, false);
        armorRightArm.setTextureOffset(0, 58).addBox(-3.212F, -2.525F, -2.4022F, 2, 5, 5, 0.0F, false);
        armorRightArm.setTextureOffset(14, 61).addBox(-1.212F, -2.525F, -2.4022F, 2, 4, 5, 0.0F, false);
        armorRightArm.setTextureOffset(0, 43).addBox(-2.975F, -1.55F, -1.9022F, 0, 8, 3, 0.0F, false);
        armorRightArm.setTextureOffset(5, 47).addBox(-2.975F, -2.05F, -1.91F, 4, 8, 0, 0.0F, false);
        armorRightArm.setTextureOffset(5, 47).addBox(-3.0F, -2.05F, 2.1F, 4, 8, 0, 0.0F, false);
        armorRightArm.setTextureOffset(6, 44).addBox(1.05F, -2.05F, -1.9022F, 0, 8, 3, 0.0F, false);
    }
}
