package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingWisteriaBodyBlock extends GrowingPlantBodyBlock {
    public static final MapCodec<HangingWisteriaBodyBlock> CODEC = simpleCodec(HangingWisteriaBodyBlock::new);
    public static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public MapCodec<HangingWisteriaBodyBlock> codec() {
        return CODEC;
    }

    public HangingWisteriaBodyBlock(BlockBehaviour.Properties p_154975_) {
        super(p_154975_, Direction.DOWN, SHAPE, false);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.HANGING_BLUE_WISTERIA_VINES_HEAD.get();
    }
}

