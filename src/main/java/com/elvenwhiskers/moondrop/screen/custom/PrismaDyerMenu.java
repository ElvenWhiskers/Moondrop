package com.elvenwhiskers.moondrop.screen.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;


public class PrismaDyerMenu extends ModAbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final Level level;
    Runnable slotUpdateListener;
    public static final int INPUT_SLOT = 0;
    private ItemStack input;
    final Slot inputSlot;
    //final Slot resultSlot;
    public final Container container;

    public PrismaDyerMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, ContainerLevelAccess.NULL);
    }

    public PrismaDyerMenu(int containerId, Inventory inv, final ContainerLevelAccess access){
        super(ModMenuTypes.PRISMA_DYER_MENU.get(), containerId);
        this.input = ItemStack.EMPTY;
        this.slotUpdateListener = () -> { };
        this.container = new SimpleContainer(1) {
            public void setChanged() {
                super.setChanged();
                PrismaDyerMenu.this.slotsChanged(this);
                PrismaDyerMenu.this.slotUpdateListener.run();
            }
        };
        this.access = access;
        this.level = inv.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 13, 16));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    public boolean hasInputItem() {
        return this.inputSlot.hasItem();
    }

    @Override
    public void setSlotCount(int slots) {
        super.setSlotCount(1); //added 1 slot manually.
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.PRISMA_DYER.get());
    }

    public MenuType<?> getType() {
        return ModMenuTypes.PRISMA_DYER_MENU.get();
    }

    public void registerUpdateListener(Runnable listener) {
        this.slotUpdateListener = listener;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((p_40313_, p_40314_) -> {
            this.clearContainer(player, this.container);
        });
    }
}
