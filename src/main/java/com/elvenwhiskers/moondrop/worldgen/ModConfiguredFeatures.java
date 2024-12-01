package com.elvenwhiskers.moondrop.worldgen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.worldgen.tree.custom.WisteriaFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.apache.commons.codec.language.bm.Rule;

import java.util.List;


public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGNOLIA_KEY = registerKey("magnolia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OW_PRISM_ORE_KEY = registerKey("ow_prism_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_WISTERIA_KEY = registerKey("blue_wisteria");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, MAGNOLIA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAGNOLIA_LOG.get()),
                new StraightTrunkPlacer(5, 2, 0),
                BlockStateProvider.simple(ModBlocks.MAGNOLIA_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(Blocks.DIRT)).build());

        register(context, BLUE_WISTERIA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAGNOLIA_LOG.get()),
                new StraightTrunkPlacer(8, 2, 0),
                BlockStateProvider.simple(ModBlocks.WISTERIA_LEAVES.get()),
                new WisteriaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(Blocks.DIRT)).build());

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        register(context, OW_PRISM_ORE_KEY, Feature.ORE, new OreConfiguration (stoneReplaceables, ModBlocks.PRISM_ORE.get().defaultBlockState(), 9));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>>registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
