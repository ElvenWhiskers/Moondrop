package com.elvenwhiskers.moondrop.screen.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.recipe.ColorerRecipe;
import com.elvenwhiskers.moondrop.recipe.ModRecipes;
import com.elvenwhiskers.moondrop.screen.ModMenuTypes;
import com.google.common.collect.Lists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class PrismaDyerMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final DataSlot selectedRecipeIndex;
    private final Level level;
    private List<RecipeHolder<ColorerRecipe>> recipes;
    Runnable slotUpdateListener;
    private ItemStack input;
    final Slot inputSlot;
    final Slot resultSlot;
    public final Container container;
    final ResultContainer resultContainer;
    long lastSoundTime;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_ROW_COUNT * PLAYER_INVENTORY_COLUMN_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    public PrismaDyerMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, ContainerLevelAccess.NULL);
    }

    public PrismaDyerMenu(int containerId, Inventory inv, final ContainerLevelAccess access) {
        super(ModMenuTypes.PRISMA_DYER_MENU.get(), containerId);
        this.selectedRecipeIndex = DataSlot.standalone();
        this.recipes = Lists.newArrayList();
        this.input = ItemStack.EMPTY;
        this.slotUpdateListener = () -> {};
        this.container = new SimpleContainer(1) {
            public void setChanged() {
                super.setChanged();
                PrismaDyerMenu.this.slotsChanged(this);
                PrismaDyerMenu.this.slotUpdateListener.run();
            }
        };
        this.resultContainer = new ResultContainer();
        this.access = access;
        this.level = inv.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 13, 16));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 145, 33) {
            public boolean mayPlace(ItemStack iStack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack resultStack) {
                // Play sound when the item is taken
                access.execute((level, pos) -> {
                    long gameTime = level.getGameTime();
                    if (PrismaDyerMenu.this.lastSoundTime != gameTime) {
                        level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        PrismaDyerMenu.this.lastSoundTime = gameTime;
                    }
                });

                // Consume one input item
                ItemStack inputStack = PrismaDyerMenu.this.inputSlot.remove(1);
                if (!inputStack.isEmpty()) {
                    PrismaDyerMenu.this.setupResultSlot(); // Refresh the result slot
                }

                super.onTake(player, resultStack);
            }


            private List<ItemStack> getRelevantItems() {
                return List.of(PrismaDyerMenu.this.inputSlot.getItem());
            }
        });

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addDataSlot(this.selectedRecipeIndex);
        //setSlotCount(2); // Set the Tile Entity inventory slot count.
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

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stackInSlot = slot.getItem();
        ItemStack originalStack = stackInSlot.copy();

        if (index == 2) { // Result slot
            // Attempt to move result to player inventory
            if (!this.moveItemStackTo(stackInSlot, 3, 39, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(stackInSlot, originalStack);
        } else if (index == 0 || index == 1) { // Input or other slots
            // Attempt to move input/output items between inventory and hotbar
            if (!this.moveItemStackTo(stackInSlot, 3, 39, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            // Attempt to move inventory items to input slots
            if (!this.moveItemStackTo(stackInSlot, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stackInSlot.isEmpty()) {
            slot.set(ItemStack.EMPTY); // Clear the slot if empty
        } else {
            slot.setChanged();
        }

        if (stackInSlot.getCount() == originalStack.getCount()) {
            return ItemStack.EMPTY; // No changes
        }

        slot.onTake(player, stackInSlot);
        return originalStack;
    }


    public boolean hasInputItem() {
        return this.inputSlot.hasItem();
    }

    public int getNumRecipes() {
        return this.recipes.size();
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
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

    public List<RecipeHolder<ColorerRecipe>> getRecipes() {
        return this.recipes;
    }

    @Override
    public void slotsChanged(Container inventory) {
        ItemStack itemstack = this.inputSlot.getItem();
        if (!itemstack.is(this.input.getItem())) {
            this.input = itemstack.copy();
            this.setupRecipeList(inventory, itemstack);
        }
    }

    private void setupRecipeList(Container container, ItemStack stack) {
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.recipes = this.level.getRecipeManager().getRecipesFor(ModRecipes.COLORER_TYPE.get(), createRecipeInput(container), this.level);
        }
    }

    private static SingleRecipeInput createRecipeInput(Container container) {
        return new SingleRecipeInput(container.getItem(0));
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((p_40313_, p_40314_) -> {
            this.clearContainer(player, this.container);
        });
    }

    public boolean clickMenuButton(Player player, int id) {
        if (this.isValidRecipeIndex(id)) {
            this.selectedRecipeIndex.set(id);
            this.setupResultSlot();
        }

        return true;
    }

    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
    }

    void setupResultSlot() {
        if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
            RecipeHolder<ColorerRecipe> recipeholder = this.recipes.get(this.selectedRecipeIndex.get());
            ItemStack result = recipeholder.value().assemble(createRecipeInput(this.container), this.level.registryAccess());
            if (result.isItemEnabled(this.level.enabledFeatures())) {
                this.resultContainer.setRecipeUsed(recipeholder);
                this.resultSlot.set(result);
            } else {
                this.resultSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges(); // Notify the client of the update
    }


    private boolean isValidRecipeIndex(int recipeIndex) {
        return recipeIndex >= 0 && recipeIndex < this.recipes.size();
    }
}
