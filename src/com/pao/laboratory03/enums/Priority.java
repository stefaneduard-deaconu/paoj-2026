package com.pao.laboratory03.enums;

import java.awt.*;
import java.util.Objects;

public enum Priority {
    // LOW → "🟢", MEDIUM → "🟡", HIGH → "🟠", CRITICAL → "🔴"
    LOW(1,"GREEN"){
        @Override
        public String getEmoji(){
            return "🟢";
        }
    },
    MEDIUM(2, "YELLOW"){
        @Override
        public String getEmoji(){
            return "🟡";
        }
    },
    HIGH(3, "ORANGE"){
        @Override
        public String getEmoji(){
            return "🟠";
        }
    },
    CRITICAL(4, "RED"){
        @Override
        public String getEmoji(){
            return "🔴";
        }
    };

    public int getLevel(){
        return this.level;
    }
    public String getColor(){
        return this.color;
    }
    public String describe(){
        return "(level=" + getLevel() + ", color=" + getColor() + ")";
    }
    public abstract String getEmoji();

    private int level;
    private String color;

    Priority(int level, String color){
        this.level = level;
        this.color = color;
    }

}
