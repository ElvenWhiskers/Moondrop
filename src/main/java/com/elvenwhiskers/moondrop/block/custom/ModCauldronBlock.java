package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.block.entity.custom.MDCauldronBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ModCauldronBlock extends BaseEntityBlock {

    public static final VoxelShape SHAPE1 = Block.box(2, 7, 2, 14, 14, 14);
    public static final VoxelShape SHAPE2 = Block.box(1, 0, 1, 4, 5, 4);
    public static final VoxelShape SHAPE3 = Block.box(12, 0, 1, 15, 5, 4);
    public static final VoxelShape SHAPE4 = Block.box(12, 0, 12, 15, 5, 15);
    public static final VoxelShape SHAPE5 = Block.box(1, 0, 12, 4, 5, 15);
    public static final VoxelShape SHAPE6 = Block.box(0, 5, 0, 16, 7, 16);

    private static final VoxelShape FULLSHAPE = Shapes.or(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6);

    private static final MapCodec<ModCauldronBlock> CODEC = simpleCodec(ModCauldronBlock::new);


    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext){
        return FULLSHAPE;
    }


    public ModCauldronBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MDCauldronBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }


    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()){
            if(level.getBlockEntity(pos) instanceof MDCauldronBlockEntity mdCauldronBlockEntity){
                mdCauldronBlockEntity.drops();
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof MDCauldronBlockEntity mdCauldronBlockEntity) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(mdCauldronBlockEntity, Component.literal("mdcauldron")), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }


}
