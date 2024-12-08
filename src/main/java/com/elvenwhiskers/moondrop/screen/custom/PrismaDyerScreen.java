package com.elvenwhiskers.moondrop.screen.custom;

import com.elvenwhiskers.moondrop.Moondrop;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PrismaDyerScreen extends AbstractContainerScreen<PrismaDyerMenu> {
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/prisma_dyer.png");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/recipe_selected.png");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"textures/gui/container/prisma_dyer/recipe_highlighted.png");
    private int startIndex;

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
        this.renderButtons(guiGraphics, x, y);
    }

    private void renderButtons(GuiGraphics guiGraphics, int baseWidth, int baseheight){
        int xPos = 38;
        int yPos = 14;
        for(int i = 0; i < this.menu.getNumRecipes(); i++){
            guiGraphics.blit(RECIPE_HIGHLIGHTED_SPRITE,baseWidth + xPos, baseheight + yPos, 0, 0, 16, 18, 16, 18);
            xPos = xPos + 16;
            yPos = yPos + 18;
        }
    }

    //not sure what this part does.
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
