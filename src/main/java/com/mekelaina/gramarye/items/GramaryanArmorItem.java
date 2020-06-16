package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.client.model.armor.GramaryanArmorBase;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class GramaryanArmorItem extends ArmorItem {

    private GramaryanArmorBase armorModel;

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
    }
}
