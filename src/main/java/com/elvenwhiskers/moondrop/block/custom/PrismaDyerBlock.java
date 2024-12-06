package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.screen.custom.ColorerMenu;
import com.elvenwhiskers.moondrop.screen.custom.PrismaDyerMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class PrismaDyerBlock extends Block {
    private static final Component CONTAINER_TITLE = Component.translatable("block.moondrop.prisma_dyer");
    private static final MapCodec<PrismaDyerBlock> CODEC = simpleCodec(PrismaDyerBlock::new);


    public PrismaDyerBlock(Properties properties) {
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
        return new SimpleMenuProvider((num, inv, player) -> {
            return new PrismaDyerMenu(num, inv, ContainerLevelAccess.create(level, pos));
        }, CONTAINER_TITLE);
    }

    public MapCodec<PrismaDyerBlock> codec() {
        return CODEC;
    }
}
