package com.elvenwhiskers.moondrop.recipe;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;

public abstract class ColorerRecipeInput extends SingleItemRecipe {

    public ColorerRecipeInput(RecipeType<?> type, RecipeSerializer<?> serializer, String group, Ingredient ingredient, ItemStack result) {
        super(type, serializer, group, ingredient, result);
    }
}

