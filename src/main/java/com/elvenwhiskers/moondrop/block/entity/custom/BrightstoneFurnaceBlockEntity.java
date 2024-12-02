package com.elvenwhiskers.moondrop.block.entity.custom;

import com.elvenwhiskers.moondrop.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BrightstoneFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public BrightstoneFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BRIGHTSTONE_FURNACE_BLOCK_ENTITY.get(), pos, blockState, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.moondrop.brightstone_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new FurnaceMenu(id, inventory, this, this.dataAccess);
    }
}
