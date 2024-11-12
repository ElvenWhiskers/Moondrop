package com.elvenwhiskers.moondrop.datagen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Moondrop.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AEGIS_BLOCK.get())
                //.add(ModBlocks.RAW_BLACK_OPAL_BLOCK.get())
                .add(ModBlocks.AEGIS_ORE.get())
                //.add(ModBlocks.BLACK_OPAL_STAIRS.get())
                //.add(ModBlocks.BLACK_OPAL_SLAB.get())
                .add(ModBlocks.BRIGHTSTONE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.MOSSY_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.CRACKED_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.CHISELED_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.COBBLED_BRIGHTSTONE.get())
                .add(ModBlocks.MOSSY_COBBLED_BRIGHTSTONE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.MAGNOLIA_LOG.get())
                .add(ModBlocks.MAGNOLIA_WOOD.get())
                .add(ModBlocks.STRIPPED_MAGNOLIA_LOG.get())
                .add(ModBlocks.STRIPPED_MAGNOLIA_WOOD.get())
                .add(ModBlocks.MAGNOLIA_PLANKS.get())
                .add(ModBlocks.MAGNOLIA_SLAB.get())
                .add(ModBlocks.MAGNOLIA_STAIRS.get())
                .add(ModBlocks.MAGNOLIA_PRESSURE_PLATE.get())
                .add(ModBlocks.MAGNOLIA_BUTTON.get())
                .add(ModBlocks.MAGNOLIA_FENCE.get())
                .add(ModBlocks.MAGNOLIA_FENCE_GATE.get())
                .add(ModBlocks.MAGNOLIA_WALL.get())
                .add(ModBlocks.MAGNOLIA_TRAPDOOR.get())
                .add(ModBlocks.MAGNOLIA_DOOR.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AEGIS_ORE.get());

        //this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                //.add(ModBlocks.BLACK_OPAL_END_ORE.get())

        tag(BlockTags.FENCES).add(ModBlocks.MAGNOLIA_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.MAGNOLIA_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.MAGNOLIA_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MAGNOLIA_LOG.get())
                .add(ModBlocks.MAGNOLIA_WOOD.get())
                .add(ModBlocks.STRIPPED_MAGNOLIA_LOG.get())
                .add(ModBlocks.STRIPPED_MAGNOLIA_WOOD.get());
    }
}
