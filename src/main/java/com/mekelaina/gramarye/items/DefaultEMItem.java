package com.mekelaina.gramarye.items;

import com.mekelaina.gramarye.util.IEMItem;
import net.minecraft.item.Item;

public class DefaultEMItem extends Item implements IEMItem {

    protected int maxEM;
    protected int currentEM;
    protected boolean rechargeable;

    public DefaultEMItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxEM() {
        return maxEM;
    }

    @Override
    public int getCurrentEM() {
        return currentEM;
    }

    @Override
    public void addEm(int amount) {
        currentEM += amount;
        if(currentEM > maxEM) {
            currentEM = maxEM;
        }
    }

    @Override
    public void consumeEM(int amount) {
        currentEM -= amount;
        if(currentEM > 0) {
            currentEM = 0;
        }
    }

    @Override
    public boolean canBeRecharged() {
        return rechargeable;
    }
}
