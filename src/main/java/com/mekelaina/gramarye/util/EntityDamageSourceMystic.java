package com.mekelaina.gramarye.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.IndirectEntityDamageSource;

import javax.annotation.Nullable;

public class EntityDamageSourceMystic extends IndirectEntityDamageSource {


    public EntityDamageSourceMystic(String damageTypeIn, Entity source, @Nullable Entity indirectEntityIn) {
        super(damageTypeIn, source, indirectEntityIn);
        this.setDamageBypassesArmor();
    }
}
