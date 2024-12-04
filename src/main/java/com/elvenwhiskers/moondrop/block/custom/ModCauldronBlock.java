package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.screen.custom.ColorerMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
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

    private static final Component CONTAINER_TITLE = Component.translatable("block.moondrop.moondrop_cauldron");

    private static final VoxelShape FULLSHAPE = Shapes.or(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6);

    private static final MapCodec<ModCauldronBlock> CODEC = simpleCodec(ModCauldronBlock::new);

    public ModCauldronBlock(Properties properties) {
        super(properties);
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
        return new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
            return new ColorerMenu(p_57074_, p_57075_, ContainerLevelAccess.create(level, pos));
        }, CONTAINER_TITLE);
        //return super.getMenuProvider(state, level, pos);
    }


    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext){
        return FULLSHAPE;
    }


    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }



}
