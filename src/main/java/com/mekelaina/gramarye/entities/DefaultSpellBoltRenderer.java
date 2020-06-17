package com.mekelaina.gramarye.entities;

import com.mekelaina.gramarye.Gramarye;
import com.mekelaina.gramarye.entities.SpellBoltEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class DefaultSpellBoltRenderer extends EntityRenderer<SpellBoltEntity> implements IRenderFactory {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Gramarye.MODID, "textures/entity/entitybolt2.png");

    //private static final DefaultSpellBoltModel MODEL = new DefaultSpellBoltModel();

    public DefaultSpellBoltRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        bindTexture(TEXTURE);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(SpellBoltEntity entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(SpellBoltEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        renderManager.renderEntity(entity, x, y, z, entityYaw, partialTicks, false);
       // MODEL.render(entity, 0f, 0f, 0f, entityYaw, e);
    }

    @Override
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
        return new DefaultSpellBoltRenderer(manager);
    }
}
