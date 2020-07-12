package com.mekelaina.gramarye.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class Experia {
    private int experiaAmount;
    private int maxAmount;

    public Experia() {
        this(0, false);
    }

    public Experia(int initialExperiaAmount, boolean fill) {
        experiaAmount = fill ? initialExperiaAmount : 0;
        maxAmount = initialExperiaAmount;
    }

    public int getExperiaAmount() {
        return experiaAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void addExperia(int experiaToAdd) {
        experiaAmount += experiaToAdd;
        if(experiaAmount > maxAmount){
            experiaAmount = maxAmount;
        }
    }

    public void subtractExperia(int experiaToSubtract) {
        experiaAmount -= experiaToSubtract;
        if(experiaAmount < 0){
            experiaAmount = 0;
        }
    }

    public void setExperiaAmount(int newExperiaAmount) {
        experiaAmount = newExperiaAmount;
    }

    public void setMaxAmount(int newMaxAmount){
        maxAmount = newMaxAmount;
    }

    public static class ExperiaNBTStorage implements Capability.IStorage<Experia> {
        @Nullable
        @Override
        public INBT writeNBT(Capability<Experia> capability, Experia instance, Direction side) {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putInt("experia_amount", instance.getExperiaAmount());
            compoundNBT.putInt("experia_max", instance.getMaxAmount());
            return compoundNBT;
        }

        @Override
        public void readNBT(Capability<Experia> capability, Experia instance, Direction side, INBT nbt) {
            int amount = 0;
            int max = 0;

            if(nbt.getType() == CompoundNBT.TYPE){
                CompoundNBT compoundNBT = (CompoundNBT)nbt;
                if(compoundNBT.contains("experia_amount")){
                    amount = compoundNBT.getInt("experia_amount");
                }
                if(compoundNBT.contains("experia_max")){
                    max = compoundNBT.getInt("experia_max");
                }
            }

            instance.setExperiaAmount(amount);
            instance.setMaxAmount(max);
            /*int experiaAmount = 0;
            if(nbt.getType() == IntNBT.TYPE) {
                experiaAmount = ((IntNBT)nbt).getInt();
            }
            instance.setExperiaAmount(experiaAmount);*/
        }
    }

    public static Experia createDefaultInstance() {
        return new Experia();
    }
}
