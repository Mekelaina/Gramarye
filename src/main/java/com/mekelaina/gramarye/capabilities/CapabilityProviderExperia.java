package com.mekelaina.gramarye.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderExperia implements ICapabilitySerializable<INBT> {
    private static final Direction NO_SPECIFIC_SIDE = null;
    private static final String EXPERIA_NBT = "experia";
    private Experia experia;

    public CapabilityProviderExperia() {
        this(0, false);
    }

    public CapabilityProviderExperia(int startingMana, boolean fill) {
        experia = new Experia(startingMana, fill);
    }

    public CapabilityProviderExperia(Experia experia1){
        experia = experia1;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(CapabilityExperia.CAPABILITY_EXPERIA == cap) {
            return (LazyOptional<T>)LazyOptional.of(() -> experia);
        }

        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT newNbt = new CompoundNBT();
        INBT experiaNBT = CapabilityExperia.CAPABILITY_EXPERIA.writeNBT(experia, NO_SPECIFIC_SIDE);
        newNbt.put(EXPERIA_NBT, experiaNBT);
        return newNbt;
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        CompoundNBT compoundNBT = (CompoundNBT)nbt;
        INBT experiaNBT = compoundNBT.get(EXPERIA_NBT);

        CapabilityExperia.CAPABILITY_EXPERIA.readNBT(experia, NO_SPECIFIC_SIDE, experiaNBT);
    }
}
