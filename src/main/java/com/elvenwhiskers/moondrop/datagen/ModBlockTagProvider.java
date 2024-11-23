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
                .add(ModBlocks.AEGIS_ORE.get())
                .add(ModBlocks.PRISM_ORE.get())
                .add(ModBlocks.BRIGHT_PRISM_ORE.get())
                .add(ModBlocks.BRIGHTSTONE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.MOSSY_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.CRACKED_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.CHISELED_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.COBBLED_BRIGHTSTONE.get())
                .add(ModBlocks.MOSSY_COBBLED_BRIGHTSTONE.get())
                .add(ModBlocks.BRIGHTSTONE_STAIRS.get())
                .add(ModBlocks.BRIGHTSTONE_SLAB.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_PRESSURE_PLATE.get())
                .add(ModBlocks.BRIGHTSTONE_BUTTON.get())
                .add(ModBlocks.BRIGHTSTONE_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_DOOR.get())
                .add(ModBlocks.BRIGHTSTONE_TRAPDOOR.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_STAIRS.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_SLAB.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_BUTTON.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_DOOR.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR.get())
                .add(ModBlocks.PASTEL_BABY_BLUE_BRICKS.get())
                .add(ModBlocks.PASTEL_BABY_BLUE_COBBLE.get())
                .add(ModBlocks.PRISM_BLOCK.get());

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
                .add(ModBlocks.MAGNOLIA_DOOR.get())

                .add(ModBlocks.LARKSPUR_LOG.get())
                .add(ModBlocks.LARKSPUR_WOOD.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_LOG.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_WOOD.get())
                .add(ModBlocks.LARKSPUR_PLANKS.get())
                .add(ModBlocks.LARKSPUR_SLAB.get())
                .add(ModBlocks.LARKSPUR_STAIRS.get())
                .add(ModBlocks.LARKSPUR_PRESSURE_PLATE.get())
                .add(ModBlocks.LARKSPUR_BUTTON.get())
                .add(ModBlocks.LARKSPUR_FENCE.get())
                .add(ModBlocks.LARKSPUR_FENCE_GATE.get())
                .add(ModBlocks.LARKSPUR_WALL.get())
                .add(ModBlocks.LARKSPUR_TRAPDOOR.get())
                .add(ModBlocks.LARKSPUR_DOOR.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.KAOLIN_BLOCK.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.PRISM_ORE.get())
                .add(ModBlocks.BRIGHT_PRISM_ORE.get())
                .add(ModBlocks.BRIGHTSTONE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.MOSSY_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.CRACKED_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.CHISELED_BRIGHTSTONE_BRICKS.get())
                .add(ModBlocks.COBBLED_BRIGHTSTONE.get())
                .add(ModBlocks.MOSSY_COBBLED_BRIGHTSTONE.get())
                .add(ModBlocks.BRIGHTSTONE_STAIRS.get())
                .add(ModBlocks.BRIGHTSTONE_SLAB.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_PRESSURE_PLATE.get())
                .add(ModBlocks.BRIGHTSTONE_BUTTON.get())
                .add(ModBlocks.BRIGHTSTONE_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_STAIRS.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_SLAB.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_BUTTON.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_DOOR.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR.get())
                .add(ModBlocks.PASTEL_BABY_BLUE_BRICKS.get())
                .add(ModBlocks.PASTEL_BABY_BLUE_COBBLE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AEGIS_ORE.get());

        //this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                //.add(ModBlocks.BLACK_OPAL_END_ORE.get())

        tag(BlockTags.FENCES)
                .add(ModBlocks.MAGNOLIA_FENCE.get())
                .add(ModBlocks.LARKSPUR_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MAGNOLIA_FENCE_GATE.get())
                .add(ModBlocks.LARKSPUR_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.MAGNOLIA_WALL.get())
                .add(ModBlocks.LARKSPUR_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MAGNOLIA_LOG.get())
                .add(ModBlocks.MAGNOLIA_WOOD.get())
                .add(ModBlocks.STRIPPED_MAGNOLIA_LOG.get())
                .add(ModBlocks.STRIPPED_MAGNOLIA_WOOD.get())

                .add(ModBlocks.LARKSPUR_LOG.get())
                .add(ModBlocks.LARKSPUR_WOOD.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_LOG.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.LARKSPUR_PLANKS.get())
                .add(ModBlocks.MAGNOLIA_PLANKS.get());

        this.tag(BlockTags.STONE_BRICKS)
                .add(ModBlocks.BRIGHTSTONE_BRICKS.get());

        this.tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAGNOLIA_BUTTON.get())
                .add(ModBlocks.LARKSPUR_BUTTON.get());

        this.tag(BlockTags.STONE_BUTTONS)
                .add(ModBlocks.BRIGHTSTONE_BUTTON.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_BUTTON.get());

        this.tag(BlockTags.BUTTONS)
                .add(ModBlocks.BRIGHTSTONE_BUTTON.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_BUTTON.get())
                .add(ModBlocks.MAGNOLIA_BUTTON.get())
                .add(ModBlocks.LARKSPUR_BUTTON.get());

        this.tag(BlockTags.DOORS)
                .add(ModBlocks.MAGNOLIA_DOOR.get())
                .add(ModBlocks.LARKSPUR_DOOR.get())
                .add(ModBlocks.BRIGHTSTONE_DOOR.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_DOOR.get());

        this.tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MAGNOLIA_DOOR.get())
                .add(ModBlocks.LARKSPUR_DOOR.get());

        this.tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MAGNOLIA_STAIRS.get())
                .add(ModBlocks.LARKSPUR_STAIRS.get());

        this.tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MAGNOLIA_SLAB.get())
                .add(ModBlocks.LARKSPUR_SLAB.get());

        this.tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MAGNOLIA_FENCE.get())
                .add(ModBlocks.LARKSPUR_FENCE.get());

        this.tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.MAGNOLIA_PRESSURE_PLATE.get())
                .add(ModBlocks.LARKSPUR_PRESSURE_PLATE.get())
                .add(ModBlocks.BRIGHTSTONE_PRESSURE_PLATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE.get());

        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAGNOLIA_PRESSURE_PLATE.get())
                .add(ModBlocks.LARKSPUR_PRESSURE_PLATE.get());

        this.tag(BlockTags.STONE_PRESSURE_PLATES)
                .add(ModBlocks.BRIGHTSTONE_PRESSURE_PLATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE.get());

        this.tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAGNOLIA_TRAPDOOR.get())
                .add(ModBlocks.LARKSPUR_TRAPDOOR.get());

        this.tag(BlockTags.SAPLINGS)
                .add(ModBlocks.MAGNOLIA_SAPLING.get())
                .add(ModBlocks.LARKSPUR_SAPLING.get());

        this.tag(BlockTags.LOGS)
                .add(ModBlocks.MAGNOLIA_LOG.get())
                .add(ModBlocks.LARKSPUR_LOG.get());

        this.tag(BlockTags.STAIRS)
                .add(ModBlocks.MAGNOLIA_STAIRS.get())
                .add(ModBlocks.LARKSPUR_STAIRS.get())
                .add(ModBlocks.BRIGHTSTONE_STAIRS.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_STAIRS.get());

        this.tag(BlockTags.SLABS)
                .add(ModBlocks.MAGNOLIA_SLAB.get())
                .add(ModBlocks.LARKSPUR_SLAB.get())
                .add(ModBlocks.BRIGHTSTONE_SLAB.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_SLAB.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.MAGNOLIA_WALL.get())
                .add(ModBlocks.LARKSPUR_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_WALL.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_WALL.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.MAGNOLIA_LEAVES.get())
                .add(ModBlocks.LARKSPUR_LEAVES.get());

        this.tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.MAGNOLIA_TRAPDOOR.get())
                .add(ModBlocks.LARKSPUR_TRAPDOOR.get())
                .add(ModBlocks.BRIGHTSTONE_TRAPDOOR.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.MAGNOLIA_FENCE.get())
                .add(ModBlocks.LARKSPUR_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE.get());

        this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModBlocks.MAGNOLIA_LOG.get())
                .add(ModBlocks.LARKSPUR_LOG.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MAGNOLIA_FENCE_GATE.get())
                .add(ModBlocks.LARKSPUR_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_FENCE_GATE.get())
                .add(ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE.get());
    }
}
