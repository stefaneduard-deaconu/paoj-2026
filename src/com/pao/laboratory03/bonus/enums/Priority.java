package com.pao.laboratory03.bonus.enums;

public interface Priority {
    int getLevel();
    double getMultiplier();
    double calculateScore(int baseDays);

    enum PriorityLevel implements Priority {
        LOW(1, 1.0),
        MEDIUM(2, 1.5),
        HIGH(3, 2.0),
        CRITICAL(4, 3.0);

        private final int level;
        private final double multiplier;

        PriorityLevel(int level, double multiplier) {
            this.level = level;
            this.multiplier = multiplier;
        }

        @Override
        public int getLevel() {
            return level;
        }

        @Override
        public double getMultiplier() {
            return multiplier;
        }

        @Override
        public double calculateScore(int baseDays) {
            return baseDays * multiplier;
        }
    }
}
