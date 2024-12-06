package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.screen.custom.ColorerMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ModCauldronBlock extends Block {

    public static final VoxelShape SHAPE1 = Block.box(2, 7, 2, 14, 14, 14);
    public static final VoxelShape SHAPE2 = Block.box(1, 0, 1, 4, 5, 4);
    public static final VoxelShape SHAPE3 = Block.box(12, 0, 1, 15, 5, 4);
    public static final VoxelShape SHAPE4 = Block.box(12, 0, 12, 15, 5, 15);
    public static final VoxelShape SHAPE5 = Block.box(1, 0, 12, 4, 5, 15);
    public static final VoxelShape SHAPE6 = Block.box(0, 5, 0, 16, 7, 16);

    public static final DirectionProperty FACING;

    private static final Component CONTAINER_TITLE = Component.translatable("block.moondrop.moondrop_cauldron");

    private static final VoxelShape FULLSHAPE = Shapes.or(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6);

    private static final MapCodec<ModCauldronBlock> CODEC = simpleCodec(ModCauldronBlock::new);

    public MapCodec<ModCauldronBlock> codec() {
        return CODEC;
    }

    public ModCauldronBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_STONECUTTER);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((num, inv, player) -> {
            return new ColorerMenu(num, inv, ContainerLevelAccess.create(level, pos));
        }, CONTAINER_TITLE);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext){
        return FULLSHAPE;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        return (BlockState)state.setValue(FACING, rotation.rotate((Direction)state.getValue(FACING)));
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
    }



}
