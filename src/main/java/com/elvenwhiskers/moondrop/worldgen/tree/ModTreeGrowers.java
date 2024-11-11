package com.elvenwhiskers.moondrop.worldgen.tree;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower MAGNOLIA = new TreeGrower(Moondrop.MODID + ":magnolia",
            Optional.empty(), Optional.of(ModConfiguredFeatures.MAGNOLIA_KEY), Optional.empty());
}
