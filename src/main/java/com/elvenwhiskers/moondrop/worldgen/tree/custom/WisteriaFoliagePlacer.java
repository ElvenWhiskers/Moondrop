package com.elvenwhiskers.moondrop.worldgen.tree.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.util.ModTags;
import com.elvenwhiskers.moondrop.worldgen.tree.ModFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Function;

public class WisteriaFoliagePlacer extends FoliagePlacer {
    protected final int height;

    public static final MapCodec<WisteriaFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance)
                    .and(Codec.intRange(0, 16).fieldOf("height").forGetter(fp -> fp.height))
                    .apply(instance, WisteriaFoliagePlacer::new)
    );

    public WisteriaFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.WISTERIA_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, int maxFreeTreeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int foliageRadius, int offset) {
        // Base position for foliage attachment
        BlockPos basePos = foliageAttachment.pos();

        // First Pass: Create tree shape and basic leaves (Layers 1 to 5)
        createTreeShape(levelSimulatedReader, foliageSetter, randomSource, basePos);

        // Second Pass: Place Hanging Leaves
        placeHangingLeaves(levelSimulatedReader, foliageSetter, randomSource, basePos);

        // Third Pass: Place the head block at the bottom of each vine body block
        int searchRadius = Math.max(foliageRadius, 5); // Adjust as needed
        int searchHeight = 16; // Restrict height to avoid excess computation
        placeVineHeads(levelSimulatedReader, foliageAttachment, searchRadius, searchHeight);
    }

    // Method to place the head piece at the bottom of hanging vines
    private void placeVineHeads(LevelSimulatedReader levelSimulatedReader, FoliageAttachment foliageAttachment, int radius, int height) {
        if (!(levelSimulatedReader instanceof LevelAccessor level)) {
            return; // Ensure we have access to LevelAccessor
        }

        // Base position for foliage attachment
        BlockPos basePos = foliageAttachment.pos();

        // Define bounds for the search area based on the tree's foliage radius and height
        BlockPos start = basePos.offset(-radius, -height, -radius);
        BlockPos end = basePos.offset(radius, height, radius);

        // Iterate through the defined bounding box
        for (BlockPos pos : BlockPos.betweenClosed(start, end)) {
            // Check if the current block is a vine body block
            if (level.getBlockState(pos).is(ModBlocks.HANGING_BLUE_WISTERIA_VINES_BASE.get())) {
                BlockPos belowPos = pos.below(); // Get the position below this vine block
                if (level.getBlockState(belowPos).isAir()) {
                    // Place the vine head block at the position below
                    BlockState headState = ModBlocks.HANGING_BLUE_WISTERIA_VINES_HEAD.get().defaultBlockState();
                    level.setBlock(belowPos, headState, Block.UPDATE_ALL);
                }
            }
        }
    }


    // First pass: Create all layers of the tree (including basic leaves)
    private void createTreeShape(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, BlockPos basePos) {
        // Layer 1: Wisteria Leaves
        int wisteriaCircleRange = 1;
        for (int dx = -wisteriaCircleRange; dx <= wisteriaCircleRange; dx++) {
            for (int dz = -wisteriaCircleRange; dz <= wisteriaCircleRange; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= wisteriaCircleRange) {
                    BlockPos leafPos = basePos.offset(dx, 2, dz); // +2 Y for Layer 1
                    placeLeafAtWithWisteriaLeaves(levelSimulatedReader, foliageSetter, randomSource, leafPos);
                }
            }
        }

        // Layer 2: Half Blue Wisteria Leaves
        int radiusLayer2 = 2;
        int expandedRadius = 3;
        for (int dx = -expandedRadius; dx <= expandedRadius; dx++) {
            for (int dz = -expandedRadius; dz <= expandedRadius; dz++) {
                double distance = Math.sqrt(dx * dx + dz * dz);
                if (distance <= radiusLayer2 + 0.5) {
                    BlockPos leafPos = basePos.offset(dx, 1, dz); // +1 Y for Layer 2
                    placeLeafAtWithHalfBlueLeaves(levelSimulatedReader, foliageSetter, randomSource, leafPos);
                }
            }
        }

        // Layer 3: Beefed-Up Blue Wisteria Leaves
        int radiusLayer3 = 3;
        int expandedRadiusLayer3 = 4;
        for (int dx = -expandedRadiusLayer3; dx <= expandedRadiusLayer3; dx++) {
            for (int dz = -expandedRadiusLayer3; dz <= expandedRadiusLayer3; dz++) {
                double distance = Math.sqrt(dx * dx + dz * dz);
                if (distance <= radiusLayer3 + 0.5) {
                    BlockPos leafPos = basePos.offset(dx, 0, dz); // No Y offset for Layer 3
                    placeLeafAtWithBlueLeaves(levelSimulatedReader, foliageSetter, randomSource, leafPos);
                }
            }
        }

        // Layer 4: Beefed-Up Blue Wisteria Leaves with Hollow Bowl (Carving out only 5 blocks in the center)
        int radiusLayer4 = 4; // Same radius as Layer 3 (don't expand beyond Layer 3)
        int missingLeavesLayer4 = 10; // 10% chance for missing leaves

        // Loop over the radius to place leaves, maintaining the same outer boundary as Layer 3
        for (int dx = -radiusLayer4; dx <= radiusLayer4; dx++) {
            for (int dz = -radiusLayer4; dz <= radiusLayer4; dz++) {
                double distance = Math.sqrt(dx * dx + dz * dz); // Calculate distance from center

                // Check if the current position is within the outer radius (same as Layer 3)
                if (distance <= radiusLayer4) {
                    // Skip the center and cross-shape (5 blocks in total)
                    if ((dx == 0 && dz == 0) || (Math.abs(dx) == 1 && dz == 0) || (dx == 0 && Math.abs(dz) == 1)) {
                        continue; // Skip placing leaves at the center and cross-shape
                    }

                    // Random chance for missing leaves (10% chance)
                    if (Math.random() > 1.0 - (double) missingLeavesLayer4 / 100.0) {
                        continue; // Skip placing this leaf
                    }

                    // Place regular Blue Wisteria Leaves
                    BlockPos leafPos = basePos.offset(dx, -1, dz); // -1 Y for Layer 4 (same as Layer 3)
                    placeLeafAtWithBlueLeaves(levelSimulatedReader, foliageSetter, randomSource, leafPos);
                }
            }
        }

        // Layer 5: Beefed-Up Blue Wisteria Leaves (Thinner Hollow Bowl)
        int radiusLayer5 = 3;
        int expandedRadiusLayer5 = 4;
        int missingLeavesLayer5 = 20; // Increased randomness (now 20% chance)

        for (int dx = -expandedRadiusLayer5; dx <= expandedRadiusLayer5; dx++) {
            for (int dz = -expandedRadiusLayer5; dz <= expandedRadiusLayer5; dz++) {
                double distance = Math.sqrt(dx * dx + dz * dz);
                if (distance <= radiusLayer5 + 0.5 && distance > 2.5) {
                    // Check if there is air above the leaf position before placing the leaf
                    BlockPos leafPos = basePos.offset(dx, -2, dz); // -2 Y for Layer 5
                    BlockPos abovePos = leafPos.above(); // Position directly above the leaf

                    if (levelSimulatedReader.isStateAtPosition(abovePos, state -> state.isAir())) {
                        continue; // Skip placing this leaf if there's air above
                    }

                    // Increased randomness for missing leaves (20% chance)
                    if (Math.random() > 1.0 - (double)missingLeavesLayer5 / 100.0) {
                        continue; // Skip placing this leaf
                    }

                    placeLeafAtWithBlueLeaves(levelSimulatedReader, foliageSetter, randomSource, leafPos);
                }
            }
        }
    }

    private void placeHangingLeaves(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, BlockPos basePos) {
        // Maximum radius of the tree's leaves (this will match the largest leaf layer)
        int maxLeafRadius = 4; // This is the expanded radius of Layer 5, the largest radius

        // Loop over the empty spots underneath Layer 3, expanded to match the radius of the leaves
        for (int dx = -maxLeafRadius; dx <= maxLeafRadius; dx++) {
            for (int dz = -maxLeafRadius; dz <= maxLeafRadius; dz++) {
                // Skip the log position (0, 0)
                if (dx == 0 && dz == 0) {
                    continue;
                }

                // Calculate the starting position for the hanging leaves (just below Layer 3)
                BlockPos leafPos = basePos.offset(dx, 0, dz); // Leaf position is at the level of Layer 3 (Y=0)
                BlockPos belowPos = leafPos.below(); // Check below this position for valid placement

                // Skip placing hanging leaves on logs
                if (isLogOrOverridden(levelSimulatedReader, belowPos)) {
                    continue;
                }

                // Check if the block above the leaf position is valid (Blue Wisteria Leaves or Hanging Blue Wisteria Vine Base)
                BlockPos abovePos = belowPos.above(); // Position directly above the hanging leaf

                // Only place hanging leaves if the block above is either Blue Wisteria Leaves or Hanging Blue Wisteria Vines Base
                if (isLeafOrHangingLeaf(levelSimulatedReader, abovePos)) {
                    // Slightly increased chance for first placement (20% chance for first placement)
                    if (randomSource.nextFloat() < 0.2) {
                        placeHangingLeaf(levelSimulatedReader, foliageSetter, randomSource, belowPos);
                    }
                }
            }
        }

        // Loop through a larger range below Layer 3 to increase the variance in hanging leaf length
        for (int dx = -maxLeafRadius; dx <= maxLeafRadius; dx++) { // Expanded range to max leaf radius
            for (int dz = -maxLeafRadius; dz <= maxLeafRadius; dz++) {
                // We check all spots from below Layer 3 down to a random deeper spot
                for (int yOffset = 1; yOffset <= randomSource.nextInt(5, 10); yOffset++) { // Random hanging length between 5 and 10 blocks
                    BlockPos posToCheck = basePos.offset(dx, -yOffset, dz); // Get the position below each layer
                    BlockPos abovePos = posToCheck.above(); // Checking if there's a leaf block or hanging leaf above this position

                    // Only place hanging leaves if it's not a log and the block above is valid (Blue Wisteria Leaves or Hanging Leaves)
                    if (!isLogOrOverridden(levelSimulatedReader, posToCheck) && isLeafOrHangingLeaf(levelSimulatedReader, abovePos)) {
                        // High chance to place hanging leaf if there's already one nearby (40% chance if a hanging leaf exists)
                        float chance = 0.1f; // Default low chance

                        // Increase the chance if there's already a hanging leaf directly above the position
                        if (isLeafOrHangingLeaf(levelSimulatedReader, posToCheck.above())) {
                            chance = 0.4f; // Higher chance if a hanging leaf is present
                        }

                        // Only place hanging leaves with the calculated chance
                        if (randomSource.nextFloat() < chance) {
                            placeHangingLeaf(levelSimulatedReader, foliageSetter, randomSource, posToCheck);
                        }
                    }
                }
            }
        }
    }


    // Helper method to check if the block is a log or should not be overridden
    private boolean isLogOrOverridden(LevelSimulatedReader levelSimulatedReader, BlockPos pos) {
        // Prevent hanging leaves from being placed on logs or any non-leaf blocks
        return levelSimulatedReader.isStateAtPosition(pos, state -> state.is(BlockTags.LOGS));
    }


    private void placeHangingLeaf(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, BlockPos pos) {
        BlockState hangingLeafState = ModBlocks.HANGING_BLUE_WISTERIA_VINES_BASE.get().defaultBlockState();

        if (levelSimulatedReader instanceof LevelAccessor level) {
            level.setBlock(pos, hangingLeafState, Block.UPDATE_ALL);
        }
    }


    // Method to place Blue Wisteria Leaves
    private void placeLeafAtWithBlueLeaves(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, BlockPos pos) {
        BlockState leafState = ModBlocks.BLUE_WISTERIA_LEAVES.get().defaultBlockState();
        foliageSetter.set(pos, leafState);
    }

    // Method to place regular Wisteria Leaves
    private void placeLeafAtWithWisteriaLeaves(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, BlockPos pos) {
        BlockState leafState = ModBlocks.WISTERIA_LEAVES.get().defaultBlockState();
        foliageSetter.set(pos, leafState);
    }

    // Method to place regular Wisteria Leaves
    private void placeLeafAtWithHangingWisteriaLeaves(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, BlockPos pos) {
        BlockState leafState = ModBlocks.HANGING_BLUE_WISTERIA_VINES_HEAD.get().defaultBlockState();
        foliageSetter.set(pos, leafState);
    }

    // Method to place Half Blue Wisteria Leaves
    private void placeLeafAtWithHalfBlueLeaves(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, BlockPos pos) {
        BlockState leafState = ModBlocks.WISTERIA_HALF_BLUE_LEAVES.get().defaultBlockState();
        foliageSetter.set(pos, leafState);
    }

    // Helper method to check if a block is either a regular leaf or hanging leaf
    private boolean isLeafOrHangingLeaf(LevelSimulatedReader levelSimulatedReader, BlockPos pos) {
        // Use isStateAtPosition to check if the block at the given position is a leaf or hanging leaf block
        return levelSimulatedReader.isStateAtPosition(pos, state ->
                state.is(ModBlocks.BLUE_WISTERIA_LEAVES.get()) || state.is(ModBlocks.HANGING_BLUE_WISTERIA_VINES_BASE.get())
        );
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}

