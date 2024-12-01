package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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


    //    @Override
//    public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
//        return direction == Direction.UP && !this.canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, offsetState, level, pos, offsetPos);
//    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return Block.isFaceFull(level.getBlockState(pos.above()).getCollisionShape(level, pos.above()), Direction.DOWN) && !level.isWaterAt(pos);
    }
}

