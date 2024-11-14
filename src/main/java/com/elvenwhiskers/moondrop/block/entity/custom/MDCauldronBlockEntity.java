package com.elvenwhiskers.moondrop.block.entity.custom;

import com.elvenwhiskers.moondrop.block.entity.ModBlockEntities;
import com.elvenwhiskers.moondrop.screen.custom.ColorerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class MDCauldronBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(4){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int RESULT_SLOT = 1;
    private static final int FUEL_SLOT = 2;


    private final ContainerData data;
    //private int progress = 0;
    //private int maxProgress = 72;
    //private final int DEFAULT_MAX_PROGRESS = 72;


    public MDCauldronBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MDCAULDRON_BE.get(), pos, blockState);
        //removed the progress bar stuff?
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {

            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    //Just a regular translateable for the block
    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.moondrop.mdcauldron");
    }

    //currently null but eventually creates the menu for our block
    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {

        return new ColorerMenu(containerID, inventory, this, this.data);
    }

    //Saves data in case block is unloaded
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    //load data when block is reloaded
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    //drops contents if block is broken
    public void drops(){
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    //under this is both things to help save data and get it reloaded properly? further research needed.
    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
