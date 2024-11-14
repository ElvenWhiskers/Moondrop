package com.elvenwhiskers.moondrop.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record ColorerRecipe(Ingredient inputItem, ItemStack output) implements Recipe<ColorerRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(ColorerRecipeInput colorerRecipeInput, Level level) {
        if(level.isClientSide()){
            return false;
        }
        return inputItem.test(colorerRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(ColorerRecipeInput colorerRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COLORER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.COLORER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<ColorerRecipe>{
        public static final MapCodec<ColorerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ColorerRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ColorerRecipe::output)
        ).apply(inst, ColorerRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, ColorerRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, ColorerRecipe::inputItem,
                        ItemStack.STREAM_CODEC, ColorerRecipe::output,
                        ColorerRecipe::new);

        @Override
        public MapCodec<ColorerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ColorerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}