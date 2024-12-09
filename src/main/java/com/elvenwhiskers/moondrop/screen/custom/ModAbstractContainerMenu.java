package com.elvenwhiskers.moondrop.screen.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class ModAbstractContainerMenu extends AbstractContainerMenu {

    private static int TE_INVENTORY_SLOT_COUNT;  // Must be set by each subclass based on its specific slots.

    public ModAbstractContainerMenu(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }

    public void setSlotCount(int slots) {
        TE_INVENTORY_SLOT_COUNT = slots;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = this.slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Define the hotbar and inventory ranges
        int hotbarStart = VANILLA_FIRST_SLOT_INDEX;
        int hotbarEnd = hotbarStart + HOTBAR_SLOT_COUNT - 1;
        int inventoryStart = hotbarEnd + 1;
        int inventoryEnd = inventoryStart + PLAYER_INVENTORY_SLOT_COUNT - 1;

        // Check if the clicked slot is in the hotbar
        if (pIndex >= hotbarStart && pIndex <= hotbarEnd) {
            // Move from hotbar to inventory
            if (!moveItemStackTo(sourceStack, inventoryStart, inventoryEnd + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex >= inventoryStart && pIndex <= inventoryEnd) {
            // Move from inventory to hotbar
            if (!moveItemStackTo(sourceStack, hotbarStart, hotbarEnd + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex >= TE_INVENTORY_FIRST_SLOT_INDEX && pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // Move from tile inventory to player inventory
            if (!moveItemStackTo(sourceStack, inventoryStart, hotbarEnd + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            // Invalid slot index
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }

        // Update the source slot after transferring items
        System.out.println("Returning copyOfSourceStack: " + copyOfSourceStack);
        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }



    public void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < PLAYER_INVENTORY_ROW_COUNT; ++row) {
            for (int col = 0; col < PLAYER_INVENTORY_COLUMN_COUNT; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    public void addPlayerHotbar(Inventory playerInventory) {
        for (int col = 0; col < HOTBAR_SLOT_COUNT; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }
}
