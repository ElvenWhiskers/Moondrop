package com.elvenwhiskers.moondrop.util;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public enum WisteriaFoliageColorTypes implements StringRepresentable {
    BLUE(
            ModBlocks.BLUE_WISTERIA_LEAVES.get(),
            ModBlocks.WISTERIA_HALF_BLUE_LEAVES.get(),
            ModBlocks.HANGING_BLUE_WISTERIA_VINES_BASE.get(),
            ModBlocks.HANGING_BLUE_WISTERIA_VINES_HEAD.get(),
            "blue"
    );

    private final Block leaves;
    private final Block halfLeaves;
    private final Block hangingVineBody;
    private final Block hangingVineHead;
    private final String name;

    private static final Map<String, WisteriaFoliageColorTypes> BY_NAME = new HashMap<>();

    static {
        for (WisteriaFoliageColorTypes type : values()) {
            BY_NAME.put(type.name, type);
        }
    }

    public static final Codec<WisteriaFoliageColorTypes> CODEC = StringRepresentable.fromEnum(WisteriaFoliageColorTypes::values);

    WisteriaFoliageColorTypes(Block leaves, Block halfLeaves, Block hangingVineBody, Block hangingVineHead, String name) {
        this.leaves = leaves;
        this.halfLeaves = halfLeaves;
        this.hangingVineBody = hangingVineBody;
        this.hangingVineHead = hangingVineHead;
        this.name = name;
    }

    public Block getLeaves() {
        return leaves;
    }

    public Block getHalfLeaves() {
        return halfLeaves;
    }

    public Block getHangingVineBody() {
        return hangingVineBody;
    }

    public Block getHangingVineHead() {
        return hangingVineHead;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public static WisteriaFoliageColorTypes byName(String name) {
        return BY_NAME.getOrDefault(name, BLUE); // Default to BLUE if not found
    }
}

