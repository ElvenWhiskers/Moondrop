package com.elvenwhiskers.moondrop.datagen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.item.ModItems;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, Moondrop.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.MAGNOLIA_LOG.get().asItem())
                .add(ModBlocks.MAGNOLIA_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_MAGNOLIA_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_MAGNOLIA_WOOD.get().asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.MAGNOLIA_PLANKS.get().asItem());

        tag(ItemTags.STONE_BRICKS)
                .add(ModBlocks.BRIGHTSTONE_BRICKS.get().asItem());

        tag(ItemTags.FENCE_GATES)
                .add(ModBlocks.MAGNOLIA_FENCE_GATE.get().asItem());

        tag(ItemTags.FENCES)
                .add(ModBlocks.MAGNOLIA_FENCE.get().asItem());

        tag(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.MAGNOLIA_DOOR.get().asItem());

        tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.MAGNOLIA_STAIRS.get().asItem());

        tag(ItemTags.SLABS)
                .add(ModBlocks.MAGNOLIA_SLAB.get().asItem());

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAGNOLIA_PRESSURE_PLATE.get().asItem());

        tag(ItemTags.TRAPDOORS)
                .add(ModBlocks.MAGNOLIA_TRAPDOOR.get().asItem());

        tag(ItemTags.SAPLINGS)
                .add(ModBlocks.MAGNOLIA_SAPLING.get().asItem());

        tag(ItemTags.LOGS)
                .add(ModBlocks.MAGNOLIA_LOG.get().asItem());

        tag(ItemTags.WALLS)
                .add(ModBlocks.MAGNOLIA_WALL.get().asItem());


    }
}
