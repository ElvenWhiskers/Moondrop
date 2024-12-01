package com.elvenwhiskers.moondrop.worldgen.tree;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.worldgen.tree.custom.WisteriaFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Moondrop.MODID);

    // Register the Wisteria Foliage Placer Type
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<WisteriaFoliagePlacer>> WISTERIA_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("wisteria_foliage_placer", () ->
                    new FoliagePlacerType<>(WisteriaFoliagePlacer.CODEC));  // Use the MapCodec

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);  // Register the foliage placer types with the event bus
    }
}