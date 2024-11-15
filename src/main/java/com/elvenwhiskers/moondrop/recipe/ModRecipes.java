package com.elvenwhiskers.moondrop.recipe;

import com.elvenwhiskers.moondrop.Moondrop;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
        DeferredRegister.create(Registries.RECIPE_SERIALIZER, Moondrop.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Moondrop.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ColorerRecipe>> COLORER_SERIALIZER =
            SERIALIZERS.register("coloring", ColorerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<ColorerRecipe>> COLORER_TYPE =
            TYPES.register("coloring", () -> new RecipeType<ColorerRecipe>() {
                @Override
                public String toString() {
                    int i = 0; //testing purposes
                    return "coloring";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}