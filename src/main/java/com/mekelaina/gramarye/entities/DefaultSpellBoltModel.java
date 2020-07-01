package com.mekelaina.gramarye.entities;

import com.mekelaina.gramarye.entities.AbstractBoltEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;


import net.minecraft.client.renderer.model.ModelRenderer;

public class DefaultSpellBoltModel extends EntityModel<AbstractBoltEntity> {


    private final ModelRenderer bone;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer bone4;

    public DefaultSpellBoltModel() {
        textureWidth = 32;
        textureHeight = 32;


        bone = new ModelRenderer(this);
/*        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 9, 3, -2.0F, -5.0F, -1.0F, 3, 3, 3, 0.0F, false));*/

        bone2 = new ModelRenderer(this);
       /* bone2.setRotationPoint(-1.5F, 20.5F, -1.5F);
        setRotationAngle(bone2, 0.0F, -0.7854F, 0.0F);
        bone2.cubeList.add(new ModelBox(bone2, 9, 9, 0.6213F, -1.5F, -0.7929F, 3, 3, 3, 0.0F, false));*/

        bone3 = new ModelRenderer(this);
        /*bone3.setRotationPoint(-1.5F, 20.5F, -1.5F);
        setRotationAngle(bone3, 0.0F, -0.7854F, -1.5708F);
        bone3.cubeList.add(new ModelBox(bone3, 0, 6, -0.0858F, -0.5F, -0.0858F, 3, 3, 3, 0.0F, false));*/

        bone4 = new ModelRenderer(this);
       /* bone4.setRotationPoint(-1.5F, 20.5F, -1.5F);
        setRotationAngle(bone4, -0.7854F, 1.5708F, 0.0F);
        bone4.cubeList.add(new ModelBox(bone4, 0, 0, -3.5F, -2.2071F, -0.7929F, 3, 3, 3, 0.0F, false));*/
    }



    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
           /*  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        bone.render(scale);
        bone2.render(scale);
        bone3.render(scale);
        bone4.render(scale);*/
    }

    @Override
    public void setRotationAngles(AbstractBoltEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
