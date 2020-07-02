package com.mekelaina.gramarye.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class Experia {
    private int experiaAmount;

    public Experia() {
        this(0);
    }

    public Experia(int initialExperiaAmount) {
        experiaAmount = initialExperiaAmount;
    }

    public int getExperiaAmount() {
        return experiaAmount;
    }

    public void addExperia(int experiaToAdd) {
        experiaAmount += experiaToAdd;
    }

    public void subtractExperia(int experiaToSubtract) {
        experiaAmount -= experiaToSubtract;
    }

    public void setExperiaAmount(int newExperiaAmount) {
        experiaAmount = newExperiaAmount;
    }

    public static class ExperiaNBTStorage implements Capability.IStorage<Experia> {
        @Nullable
        @Override
        public INBT writeNBT(Capability<Experia> capability, Experia instance, Direction side) {
            return IntNBT.valueOf(instance.experiaAmount);
        }

        @Override
        public void readNBT(Capability<Experia> capability, Experia instance, Direction side, INBT nbt) {
            int experiaAmount = 0;
            if(nbt.getType() == IntNBT.TYPE) {
                experiaAmount = ((IntNBT)nbt).getInt();
            }
            instance.setExperiaAmount(experiaAmount);
        }
    }

    public static Experia createDefaultInstance() {
        return new Experia();
    }
}
