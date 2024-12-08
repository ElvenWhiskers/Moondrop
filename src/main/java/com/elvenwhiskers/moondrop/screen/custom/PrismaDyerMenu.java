package com.elvenwhiskers.moondrop.screen.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.recipe.ColorerRecipe;
import com.elvenwhiskers.moondrop.recipe.ModRecipes;
import com.elvenwhiskers.moondrop.screen.ModMenuTypes;
import com.google.common.collect.Lists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class PrismaDyerMenu extends ModAbstractContainerMenu {
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

    public PrismaDyerMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, ContainerLevelAccess.NULL);
    }

    public PrismaDyerMenu(int containerId, Inventory inv, final ContainerLevelAccess access){
        super(ModMenuTypes.PRISMA_DYER_MENU.get(), containerId);
        this.selectedRecipeIndex = DataSlot.standalone();
        this.recipes = Lists.newArrayList();
        this.input = ItemStack.EMPTY;
        this.slotUpdateListener = () -> { };
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
        });

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addDataSlot(this.selectedRecipeIndex);
    }

    public boolean hasInputItem() {
        return this.inputSlot.hasItem();
    }

    public int getNumRecipes() { return this.recipes.size(); }

    public int getSelectedRecipeIndex() { return this.selectedRecipeIndex.get(); }

    @Override
    public void setSlotCount(int slots) {
        super.setSlotCount(2); //adding slots manually.
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

    public List<RecipeHolder<ColorerRecipe>> getRecipes()  { return this.recipes; }

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
        this.access.execute((p_40313_, p_40314_) -> {
            this.clearContainer(player, this.container);
        });
    }
}
