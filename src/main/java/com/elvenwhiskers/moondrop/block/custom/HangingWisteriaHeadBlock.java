package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class HangingWisteriaHeadBlock extends GrowingPlantHeadBlock {
    public static final MapCodec<HangingWisteriaHeadBlock> CODEC = simpleCodec(HangingWisteriaHeadBlock::new);
    protected static final VoxelShape SHAPE = Block.box(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);

    public MapCodec<HangingWisteriaHeadBlock> codec() {
        return CODEC;
    }

    public HangingWisteriaHeadBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1);
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource p_222680_) {
        return NetherVines.getBlocksToGrowWhenBonemealed(p_222680_);
    }

    protected Block getBodyBlock() {
        return ModBlocks.HANGING_BLUE_WISTERIA_VINES_BASE.get();
    }

    protected boolean canGrowInto(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        // The head block only survives if there is a block above it (not air)
        BlockState aboveState = level.getBlockState(pos.above());
        return !aboveState.isAir();  // Only survive if there’s a block above
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
        // Remove the body block if the head block is removed
        if (!level.isClientSide) {
            BlockPos bodyPos = pos.below();
            BlockState bodyState = level.getBlockState(bodyPos);
            if (bodyState.is(this.getBodyBlock())) {
                level.setBlockAndUpdate(bodyPos, Blocks.AIR.defaultBlockState());  // Remove the body block
            }
        }
    }
}