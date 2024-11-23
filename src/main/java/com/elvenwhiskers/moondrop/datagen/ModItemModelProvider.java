package com.elvenwhiskers.moondrop.datagen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Moondrop.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.AEGIS_INGOT.get());
        basicItem(ModItems.RAW_AEGIS.get());
        basicItem(ModItems.PASTEL_RED_DYE.get());
        basicItem(ModItems.PASTEL_ORANGE_DYE.get());
        basicItem(ModItems.PASTEL_YELLOW_DYE.get());
        basicItem(ModItems.PASTEL_LIME_DYE.get());
        basicItem(ModItems.PASTEL_GREEN_DYE.get());
        basicItem(ModItems.PASTEL_CYAN_DYE.get());
        basicItem(ModItems.PASTEL_BABY_BLUE_DYE.get());
        basicItem(ModItems.PASTEL_PURPLE_DYE.get());
        basicItem(ModItems.PASTEL_MAGENTA_DYE.get());
        basicItem(ModItems.PASTEL_PINK_DYE.get());
        basicItem(ModItems.KAOLIN.get());
        basicItem(ModItems.PRISM_SHARDS.get());

        buttonItem(ModBlocks.MAGNOLIA_BUTTON, ModBlocks.MAGNOLIA_PLANKS);
        fenceItem(ModBlocks.MAGNOLIA_FENCE, ModBlocks.MAGNOLIA_PLANKS);
        wallItem(ModBlocks.MAGNOLIA_WALL, ModBlocks.MAGNOLIA_PLANKS);
        basicItem(ModBlocks.MAGNOLIA_DOOR.asItem());
        saplingItem(ModBlocks.MAGNOLIA_SAPLING);

        buttonItem(ModBlocks.LARKSPUR_BUTTON, ModBlocks.LARKSPUR_PLANKS);
        fenceItem(ModBlocks.LARKSPUR_FENCE, ModBlocks.LARKSPUR_PLANKS);
        wallItem(ModBlocks.LARKSPUR_WALL, ModBlocks.LARKSPUR_PLANKS);
        basicItem(ModBlocks.LARKSPUR_DOOR.asItem());
        saplingItem(ModBlocks.LARKSPUR_SAPLING);

        buttonItem(ModBlocks.BRIGHTSTONE_BUTTON, ModBlocks.BRIGHTSTONE);
        fenceItem(ModBlocks.BRIGHTSTONE_FENCE, ModBlocks.BRIGHTSTONE);
        wallItem(ModBlocks.BRIGHTSTONE_WALL, ModBlocks.BRIGHTSTONE);
        basicItem(ModBlocks.BRIGHTSTONE_DOOR.asItem());

        buttonItem(ModBlocks.BRIGHTSTONE_BRICKS_BUTTON, ModBlocks.BRIGHTSTONE_BRICKS);
        fenceItem(ModBlocks.BRIGHTSTONE_BRICKS_FENCE, ModBlocks.BRIGHTSTONE_BRICKS);
        wallItem(ModBlocks.BRIGHTSTONE_BRICKS_WALL, ModBlocks.BRIGHTSTONE_BRICKS);
        basicItem(ModBlocks.BRIGHTSTONE_BRICKS_DOOR.asItem());



    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,"block/" + item.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(Moondrop.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

}
