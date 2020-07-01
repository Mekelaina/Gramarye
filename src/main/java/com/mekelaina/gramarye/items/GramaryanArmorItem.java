package com.mekelaina.gramarye.items;

import net.minecraft.item.Item;

public class GramaryanArmorItem extends Item {
    public GramaryanArmorItem(Properties properties) {
        super(properties);
    }

    /* private GramaryanArmorBase armorModel;

    public GramaryanArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, GramaryanArmorBase armorModel, Properties builder) {
        super(materialIn, slot, builder);

        this.armorModel = armorModel;
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) armorModel.applyEntityStats(_default).applySlot(armorSlot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return armorModel.getTexture();
    }*/
}
