package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

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

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        // Check if there's something solid above (the head block)
        BlockState aboveState = level.getBlockState(pos.above());
        return !aboveState.isAir();  // Only survive if there's a block above it (not air)
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
        // Remove the head block if the body block is removed
        if (!level.isClientSide) {
            BlockPos headPos = pos.above();
            BlockState headState = level.getBlockState(headPos);
            if (headState.is(this.getHeadBlock())) {
                level.setBlockAndUpdate(headPos, Blocks.AIR.defaultBlockState());  // Remove the head block
            }
        }
    }
}
