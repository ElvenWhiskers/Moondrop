package com.elvenwhiskers.moondrop.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ColorerRecipe implements Recipe<SingleRecipeInput> {
    protected final Ingredient ingredient;
    protected final ItemStack result;
    private final RecipeType<?> type;
    private final RecipeSerializer<?> serializer;
    protected final String group;

    public ColorerRecipe(RecipeType<?> type, RecipeSerializer<?> serializer, String group, Ingredient ingredient, ItemStack result){
        this.type = type;
        this.serializer = serializer;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public boolean matches(SingleRecipeInput singleRecipeInput, Level level) {
        return ingredient.test(singleRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    public static class Serializer implements RecipeSerializer<ColorerRecipe> {
        private static final MapCodec<ColorerRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                        )
                        .apply(instance, (group, ingredient, result) ->
                                new ColorerRecipe(ModRecipes.COLORER_TYPE.get(), ModRecipes.COLORER_SERIALIZER.get(), group, ingredient, result))
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, ColorerRecipe> STREAM_CODEC = StreamCodec.of(
                ColorerRecipe.Serializer::toNetwork, ColorerRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<ColorerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ColorerRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        @Nullable
        public static ColorerRecipe fromNetwork(@Nonnull RegistryFriendlyByteBuf pBuffer) {
            String group = pBuffer.readUtf();
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(pBuffer);

            ItemStack result = ItemStack.STREAM_CODEC.decode(pBuffer);
            return new ColorerRecipe(ModRecipes.COLORER_TYPE.get(), ModRecipes.COLORER_SERIALIZER.get(), group, ingredient, result);
        }

        public static void toNetwork(@Nonnull RegistryFriendlyByteBuf pBuffer, ColorerRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            Ingredient.CONTENTS_STREAM_CODEC.encode(pBuffer, pRecipe.ingredient);

            if (pRecipe.result.isEmpty()) {
                throw new IllegalArgumentException("Cannot serialize an empty ItemStack!");
            }
            ItemStack.STREAM_CODEC.encode(pBuffer, pRecipe.result);
        }
    }
}
