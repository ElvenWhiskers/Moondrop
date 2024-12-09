package com.elvenwhiskers.moondrop.screen.custom;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.recipe.ColorerRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

public class PrismaDyerScreen extends AbstractContainerScreen<PrismaDyerMenu> {
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, "textures/gui/container/prisma_dyer/prisma_dyer.png");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, "textures/gui/container/prisma_dyer/recipe_selected.png");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, "textures/gui/container/prisma_dyer/recipe_highlighted.png");
    private static final ResourceLocation RECIPE_EMPTY = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, "textures/gui/container/prisma_dyer/recipe_empty.png");
    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, "textures/gui/container/prisma_dyer/scroller_sprite.png");
    private static final ResourceLocation SCROLLER_SPRITE_DISABLED = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, "textures/gui/container/prisma_dyer/scroller_sprite_disabled.png");

    private float scrollOffs; // Normalized scroll position (0.0 to 1.0)
    private int startIndex;   // First visible recipe index
    private boolean scrolling; // True if actively dragging the scrollbar
    private boolean displayRecipes;
    private int selectedRecipeIndex = -1; // Track the selected recipe index

    public PrismaDyerScreen(PrismaDyerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        menu.registerUpdateListener(this::containerChanged);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(BG_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // Render scrollbar
        ResourceLocation resourcelocation = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_SPRITE_DISABLED;
        int scrollY = (int) (41.0F * this.scrollOffs); // Scale scrollbar position
        guiGraphics.blit(resourcelocation, x + 122, y + 14 + scrollY, 0, 0, 12, 15, 12, 15);

        this.renderButtons(guiGraphics, x, y);
        this.renderRecipes(guiGraphics, x, y);
    }

    private void renderButtons(GuiGraphics guiGraphics, int baseWidth, int baseHeight) {
        int xSpacing = 16;
        int ySpacing = 18;
        int maxColumns = 5;
        int xStart = 38;
        int yStart = 14;

        for (int i = this.startIndex; i < this.startIndex + 15 && i < this.menu.getNumRecipes(); i++) {
            int localIndex = i - this.startIndex;
            int row = localIndex / maxColumns;
            int column = localIndex % maxColumns;

            int xPos = xStart + (column * xSpacing);
            int yPos = yStart + (row * ySpacing);

            if (i == this.menu.getSelectedRecipeIndex()) {
                guiGraphics.blit(RECIPE_SELECTED_SPRITE, baseWidth + xPos, baseHeight + yPos, 0, 0, 16, 18, 16, 18);
            } else {
                guiGraphics.blit(RECIPE_HIGHLIGHTED_SPRITE, baseWidth + xPos, baseHeight + yPos, 0, 0, 16, 18, 16, 18);
            }
        }
    }

    private void handleRecipeClick(double mouseX, double mouseY, int button) {
        if (button != 0) return;

        int xSpacing = 16;
        int ySpacing = 18;
        int maxColumns = 5;
        int xStart = 38;
        int yStart = 14;

        for (int i = this.startIndex; i < this.startIndex + 15 && i < this.menu.getNumRecipes(); i++) {
            int row = (i - this.startIndex) / maxColumns;
            int column = (i - this.startIndex) % maxColumns;
            int xPos = this.leftPos + xStart + (column * xSpacing);
            int yPos = this.topPos + yStart + (row * ySpacing);

            if (mouseX >= xPos && mouseY >= yPos && mouseX < xPos + 16 && mouseY < yPos + 18) {
                this.menu.clickMenuButton(this.minecraft.player, i);
                return;
            }
        }
    }

    private void renderRecipes(GuiGraphics guiGraphics, int baseWidth, int baseHeight) {
        List<RecipeHolder<ColorerRecipe>> list = this.menu.getRecipes();
        int xSpacing = 16;
        int ySpacing = 18;
        int maxColumns = 5;
        int xStart = 38;
        int yStart = 15;

        for (int i = this.startIndex; i < this.startIndex + 15 && i < list.size(); i++) {
            int localIndex = i - this.startIndex;
            int row = localIndex / maxColumns;
            int column = localIndex % maxColumns;

            int xPos = xStart + (column * xSpacing);
            int yPos = yStart + (row * ySpacing);

            guiGraphics.renderItem(list.get(i).value().getResultItem(this.minecraft.level.registryAccess()), baseWidth + xPos, baseHeight + yPos);
        }
    }

    private boolean isScrollBarActive() {
        return this.menu.getNumRecipes() > 15;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isScrollBarActive()) {
            int scrollX = this.leftPos + 122;
            int scrollY = this.topPos + 14;
            if (mouseX >= scrollX && mouseX < scrollX + 12 && mouseY >= scrollY && mouseY < scrollY + 54) {
                this.scrolling = true;
                return true;
            }
        }

        handleRecipeClick(mouseX, mouseY, button);
        return super.mouseClicked(mouseX, mouseY, button);
    }


    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.scrolling) {
            this.scrollOffs = Math.clamp((float)(mouseY - this.topPos - 7) / 54.0F, 0.0F, 1.0F);
            this.startIndex = (int)(this.scrollOffs * this.menu.getNumRecipes());
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + 5 - 1) / 5 - 3; // Subtract 3 for visible rows
    }


    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (this.isScrollBarActive()) {
            int maxRows = this.getOffscreenRows(); // Total rows that can be scrolled
            float scrollAmount = (float) scrollY / maxRows;
            this.scrollOffs = Mth.clamp(this.scrollOffs - scrollAmount, 0.0F, 1.0F);
            this.startIndex = (int) (this.scrollOffs * maxRows + 0.5) * 5; // Multiply by 5 for row-based scrolling
        }
        return true;
    }

    private void containerChanged() {
        this.displayRecipes = this.menu.hasInputItem();
    }

}
