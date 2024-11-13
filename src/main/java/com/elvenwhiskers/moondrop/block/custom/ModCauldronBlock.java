package com.elvenwhiskers.moondrop.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModCauldronBlock extends Block {

    public static final VoxelShape SHAPE1 = Block.box(2, 7, 2, 14, 14, 14);
    public static final VoxelShape SHAPE2 = Block.box(1, 0, 1, 4, 5, 4);
    public static final VoxelShape SHAPE3 = Block.box(12, 0, 1, 15, 5, 4);
    public static final VoxelShape SHAPE4 = Block.box(12, 0, 12, 15, 5, 15);
    public static final VoxelShape SHAPE5 = Block.box(1, 0, 12, 4, 5, 15);
    public static final VoxelShape SHAPE6 = Block.box(0, 5, 0, 16, 7, 16);

    private static final VoxelShape FULLSHAPE = Shapes.or(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6);


    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext){
        return FULLSHAPE;
    }


    public ModCauldronBlock(Properties properties) {
        super(properties);
    }
}
