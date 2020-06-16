package com.mekelaina.gramarye.client.model.armor;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;

public abstract class GramaryanArmorBase extends BipedModel<LivingEntity> {

    protected final RendererModel armorHead;
    protected final RendererModel armorBody;
    protected final RendererModel armorRightArm;
    protected final RendererModel armorLeftArm;
    protected final RendererModel armorRightLeg;
    protected final RendererModel armorLeftLeg;
    protected final RendererModel armorRightBoot;
    protected final RendererModel armorLeftBoot;

    private String texture;



    public GramaryanArmorBase(int textureWidth, int textureHeight, ResourceLocation texture){
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture.toString();

        armorHead = new RendererModel(this);
        armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.addChild(armorHead);


        armorBody = new RendererModel(this);
        armorBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.addChild(armorBody);
        armorRightArm = new RendererModel(this);
        armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightArm.addChild(armorRightArm);

        armorLeftArm = new RendererModel(this);
        armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftArm.addChild(armorLeftArm);

        armorRightLeg = new RendererModel(this);
        armorRightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightLeg.addChild(armorRightLeg);

        armorLeftLeg = new RendererModel(this);
        armorLeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftLeg.addChild(armorLeftLeg);


        armorRightBoot = new RendererModel(this);
        armorRightBoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightLeg.addChild(armorRightBoot);

        armorLeftBoot = new RendererModel(this);
        armorLeftBoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftLeg.addChild(armorLeftBoot);

        setupArmorParts();
    }

    public abstract void setupArmorParts();

    public final String getTexture(){
        return this.texture;
    }

    /**
     * Feel free to override this method as needed.
     * It's just required to hide armor parts depending on the equipment slot
     */
    public BipedModel<LivingEntity> applySlot(EquipmentSlotType slot){
        armorHead.isHidden = true;
        armorBody.isHidden = true;
        armorRightArm.isHidden = true;
        armorLeftArm.isHidden = true;
        armorRightLeg.isHidden = true;
        armorLeftLeg.isHidden = true;
        armorRightBoot.isHidden = true;
        armorLeftBoot.isHidden = true;

        switch(slot){
            case HEAD:
                armorHead.isHidden = false;
                break;
            case CHEST:
                armorBody.isHidden = false;
                armorRightArm.isHidden = false;
                armorLeftArm.isHidden = false;
                break;
            case LEGS:
                armorRightLeg.isHidden = false;
                armorLeftLeg.isHidden = false;
                break;
            case FEET:
                armorRightBoot.isHidden = false;
                armorLeftBoot.isHidden = false;
                break;
            default:
                break;
        }

        return this;
    }

    public final GramaryanArmorBase applyEntityStats(BipedModel defaultArmor){
        this.isChild = defaultArmor.isChild;
        this.isSneak = defaultArmor.isSneak;
        this.isSitting = defaultArmor.isSitting;
        this.rightArmPose = defaultArmor.rightArmPose;
        this.leftArmPose = defaultArmor.leftArmPose;

        return this;
    }

    @Override
    public final void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);



        this.armorHead.copyModelAngles(bipedHead);
        this.armorBody.copyModelAngles(bipedBody);
        this.armorLeftArm.copyModelAngles(bipedLeftArm);
        this.armorRightArm.copyModelAngles(bipedRightArm);
        this.armorLeftLeg.copyModelAngles(bipedLeftLeg);
        this.armorRightLeg.copyModelAngles(bipedRightLeg);
        this.armorLeftBoot.copyModelAngles(armorLeftBoot);
        this.armorRightBoot.copyModelAngles(armorRightBoot);



        GlStateManager.pushMatrix();
        if (isSneak) GlStateManager.translatef(0.0F, 0.2F, 0.0F);
        this.armorHead.render(scale);
        this.armorBody.render(scale);
        this.armorRightArm.render(scale);
        this.armorLeftArm.render(scale);
        this.armorRightLeg.render(scale);
        this.armorLeftLeg.render(scale);
        this.armorRightBoot.render(scale);
        this.armorLeftBoot.render(scale);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    }

    public final void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }


}

