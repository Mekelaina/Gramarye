package com.mekelaina.gramarye.util;

public class XPMathUtils {

    /**
     * gets the total amount of xp at each level
     * @param level
     * @return
     */
    public static int totalXpByLevel(int level) {
        if(level < 17) {
            return (int)(Math.pow(level, 2) + 6 * level);
        } else if(level >= 17 && level < 32) {
            return (int)((2.5*Math.pow(level,2)) - 40.5 * level + 360);
        } else {
            return (int)((4.5*Math.pow(level,2)) - 162.5 * level + 2220);
        }
    }

    /**
     * calculates the current level from current xp, rounds down
     * @param xp
     * @return
     */
    public static int getClosestLevelByXp(int xp) {
        double temp = 0;

        if(xp <= 352) {
            temp = -3 + Math.sqrt(xp + 9);
            return (int) Math.floor(temp);
        } else if(xp > 352 && xp <= 1507) {
            temp = (81 + Math.sqrt((40 * xp) - 7839)) / 10;
            return (int) Math.floor(temp);
        } else {
            temp = (325 + Math.sqrt((72 * xp) - 54215)) / 18;
            return (int) Math.floor(temp);
        }
    }

    /**
     * calculates how much xp it takes to get from lvl a to lvlb (ei from level 5 to 6)
     * @param level
     * @return
     */
    public static int getXpToLevelUp(int level) {
        if(level < 16) {
            return (2 * level + 7);
        } else if(level >= 16 && level < 31) {
            return (5 * level - 38);
        } else {
            return 9 * level - 158;
        }
    }

    /**
     * calculates how much xp is needed to reach next level
     * @param xp
     * @return
     */
    public static int getXpNeededForNextLevel(int xp) {
        int currentLevel = getClosestLevelByXp(xp);
        int nextLevel = currentLevel+ 1;
        nextLevel = totalXpByLevel(nextLevel);
        return  nextLevel - xp;
    }
}
