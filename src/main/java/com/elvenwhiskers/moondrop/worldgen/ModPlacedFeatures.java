package com.elvenwhiskers.moondrop.worldgen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MAGNOLIA_PLACED_KEY = registerKey("magnolia_placed");
    public static final ResourceKey<PlacedFeature> BLUE_WISTERIA_PLACED_KEY = registerKey("blue_wisteria_placed");
    public static final ResourceKey<PlacedFeature> OW_PRISM_ORE_PLACED_KEY = registerKey("ow_prism_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, MAGNOLIA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAGNOLIA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.MAGNOLIA_SAPLING.get()));

        register(context, BLUE_WISTERIA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_WISTERIA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.BLUE_WISTERIA_SAPLING.get()));

        register(context, OW_PRISM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OW_PRISM_ORE_KEY),
                ModOrePlacements.commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, name));

    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
