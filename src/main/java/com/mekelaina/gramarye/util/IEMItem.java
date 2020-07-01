package com.mekelaina.gramarye.util;

public interface IEMItem {


    int getMaxEM();

    int getCurrentEM();

    void addEm(int amount);

    void consumeEM(int amount);

    boolean canBeRecharged();

}
