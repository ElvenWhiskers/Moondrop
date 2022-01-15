package com.elvenwhiskers.moondrop.world.feature.tree;

import com.elvenwhiskers.moondrop.world.feature.ModConfiguredFeature;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PinkStarfruitTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeature.PINK_STARFRUIT_TREE;
    }
}
