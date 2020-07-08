package com.mekelaina.gramarye.util;

import com.mekelaina.gramarye.Gramarye;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {
    public CustomEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }

    public void addEnergy(int energy)
    {
        this.energy += energy;
        if(this.energy > getMaxEnergyStored()){
            this.energy = getEnergyStored();
        }
    }

    public void consumeEnergy(int energy1)
    {
        Gramarye.LOGGER.debug("consume: " + energy1);
        this.energy -= energy1;
        if(this.energy <= 0){
            this.energy = 0;
        }
    }



    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("experion", getEnergyStored());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setEnergy(nbt.getInt("experion"));
    }
}
