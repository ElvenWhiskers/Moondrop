package com.elvenwhiskers.moondrop.screen.custom;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.recipe.ColorerRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

public class PrismaDyerScreen extends AbstractContainerScreen<PrismaDyerMenu> {
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/prisma_dyer.png");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/recipe_selected.png");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/recipe_highlighted.png");
    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/scroller_sprite.png");
    private static final ResourceLocation SCROLLER_SPRITE_DISABLED = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/scroller_sprite_disabled.png");
    private boolean displayRecipes;

    public PrismaDyerScreen(PrismaDyerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    //allows it to render at all.
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(BG_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        ResourceLocation resourcelocation = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_SPRITE_DISABLED; //scroller sprite not yet active.
        guiGraphics.blit(resourcelocation, x + 122, y + 14, 0, 0, 12, 15, 12, 15);
        this.renderButtons(guiGraphics, x, y);
        this.renderRecipes(guiGraphics, x, y);
    }

    private void renderButtons(GuiGraphics guiGraphics, int baseWidth, int baseHeight) {
        int xSpacing = 16; // Horizontal spacing between buttons
        int ySpacing = 18; // Vertical spacing between rows
        int maxColumns = 5; // Maximum buttons per row
        int xStart = 38;    // Starting x offset
        int yStart = 14;    // Starting y offset

        for (int i = 0; i < this.menu.getNumRecipes(); i++) {
            // Calculate row and column based on index
            int row = i / maxColumns;
            int column = i % maxColumns;

            // Calculate positions
            int xPos = xStart + (column * xSpacing);
            int yPos = yStart + (row * ySpacing);

            // Render button
            guiGraphics.blit(RECIPE_HIGHLIGHTED_SPRITE, baseWidth + xPos, baseHeight + yPos, 0, 0, 16, 18, 16, 18);
        }
    }

    private void renderRecipes(GuiGraphics guiGraphics, int baseWidth, int baseHeight) {
        List<RecipeHolder<ColorerRecipe>> list = this.menu.getRecipes();
        int xSpacing = 16; // Horizontal spacing between buttons
        int ySpacing = 18; // Vertical spacing between rows
        int maxColumns = 5; // Maximum buttons per row
        int xStart = 38;    // Starting x offset
        int yStart = 15;    // Starting y offset

        for (int i = 0; i < this.menu.getNumRecipes(); i++) {
            // Calculate row and column based on index
            int row = i / maxColumns;
            int column = i % maxColumns;

            // Calculate positions
            int xPos = xStart + (column * xSpacing);
            int yPos = yStart + (row * ySpacing);

            // Render button
            guiGraphics.renderItem(list.get(i).value().getResultItem(this.minecraft.level.registryAccess()), baseWidth + xPos, baseHeight + yPos);
        }
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && this.menu.getNumRecipes() > 12;
    }

    //not sure what this part does.
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
