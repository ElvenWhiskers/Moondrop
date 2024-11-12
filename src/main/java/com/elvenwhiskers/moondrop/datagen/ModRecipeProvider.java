package com.elvenwhiskers.moondrop.datagen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> AEGIS_SMELTABLES = List.of(ModItems.RAW_AEGIS, ModBlocks.AEGIS_ORE);
        List<ItemLike> BRIGHTSTONE_SMELTABLES = List.of(ModBlocks.COBBLED_BRIGHTSTONE);
        List<ItemLike> CRACKED_BRIGHTSTONE_SMELTABLES = List.of(ModBlocks.BRIGHTSTONE_BRICKS);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AEGIS_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AEGIS_INGOT.get())
                .unlockedBy("has_aegis_ingot", has(ModItems.AEGIS_INGOT.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 9)
                .requires(ModBlocks.AEGIS_BLOCK.get())
                .unlockedBy("has_aegis_block", has(ModBlocks.AEGIS_BLOCK.get())).save(pRecipeOutput);

        oreSmelting(pRecipeOutput, AEGIS_SMELTABLES, RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 0.25f, 200, "aegis_ingot");
        oreBlasting(pRecipeOutput, AEGIS_SMELTABLES, RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 0.25f, 100, "aegis_ingot");

        //stairBuilder(ModBlocks.BLACK_OPAL_STAIRS.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
        //        .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);
        //slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_OPAL_SLAB.get(), ModItems.BLACK_OPAL.get());

        //pressurePlate(pRecipeOutput, ModBlocks.BLACK_OPAL_PRESSURE_PLATE.get(), ModItems.BLACK_OPAL.get());
        //buttonBuilder(ModBlocks.BLACK_OPAL_BUTTON.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
        //        .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);

        //fenceBuilder(ModBlocks.BLACK_OPAL_FENCE.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
        //        .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);
        //fenceGateBuilder(ModBlocks.BLACK_OPAL_FENCE_GATE.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
        //       .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);
        //wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_OPAL_WALL.get(), ModItems.BLACK_OPAL.get());

        //doorBuilder(ModBlocks.BLACK_OPAL_DOOR.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
        //        .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);
        //trapdoorBuilder(ModBlocks.BLACK_OPAL_TRAPDOOR.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
        //        .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);

        //LOG PARTS ***
        //1. Adds all log varients, sticks, planks from logs, stripped, but not shapes.
        treeParts(pRecipeOutput, RecipeCategory.MISC,
                ModBlocks.MAGNOLIA_LOG.get(),
                ModBlocks.MAGNOLIA_WOOD.get(),
                ModBlocks.MAGNOLIA_PLANKS.get(),
                ModBlocks.STRIPPED_MAGNOLIA_LOG.get(),
                ModBlocks.STRIPPED_MAGNOLIA_WOOD.get());


        //2. Next add SHAPES, UGH, this was initally a pain
        plankShapeParts(pRecipeOutput,
                ModBlocks.MAGNOLIA_PLANKS.get(),
                ModBlocks.MAGNOLIA_BUTTON.get(),
                ModBlocks.MAGNOLIA_DOOR.get(),
                ModBlocks.MAGNOLIA_FENCE.get(),
                ModBlocks.MAGNOLIA_FENCE_GATE.get(),
                ModBlocks.MAGNOLIA_PRESSURE_PLATE.get(),
                ModBlocks.MAGNOLIA_SLAB.get(),
                ModBlocks.MAGNOLIA_STAIRS.get(),
                ModBlocks.MAGNOLIA_TRAPDOOR.get(),
                ModBlocks.MAGNOLIA_WALL.get());


        //STONE PARTS ***
        //1.

        //cob to stone, stone to stone brick, stone brick to cracked, stone brick to mossy, cobb to mossy, stone birck to chiseled.

        oreSmelting(pRecipeOutput, BRIGHTSTONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.BRIGHTSTONE.asItem(), 0.25f, 200, "brightstone"); //cob toi stone
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BRIGHTSTONE_BRICKS.get(), 4)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.BRIGHTSTONE.get())
                .unlockedBy("has_brightstone", has(ModBlocks.BRIGHTSTONE.get())).save(pRecipeOutput); //stone to brick
        oreSmelting(pRecipeOutput, CRACKED_BRIGHTSTONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.CRACKED_BRIGHTSTONE_BRICKS.asItem(), 0.25f, 200, "cracked_brightstone"); //bricks to cracked
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MOSSY_BRIGHTSTONE_BRICKS.get(), 4)
                .pattern("BA")
                .define('B', ModBlocks.BRIGHTSTONE_BRICKS.get())
                .define('A', Blocks.MOSS_BLOCK)
                .unlockedBy("has_brightstone", has(ModBlocks.BRIGHTSTONE.get())).save(pRecipeOutput); //brick to mossy
        //2 brick slabs makes chiseled bricks. dont have those yet.
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MOSSY_COBBLED_BRIGHTSTONE.get(), 4)
                .pattern("BA")
                .define('B', ModBlocks.MOSSY_COBBLED_BRIGHTSTONE.get())
                .define('A', Blocks.MOSS_BLOCK)
                .unlockedBy("has_brightstone", has(ModBlocks.COBBLED_BRIGHTSTONE.get())).save(pRecipeOutput); //cob to mossy



    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, Moondrop.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void treeParts(RecipeOutput pFinishedRecipe, RecipeCategory pCategory, ItemLike pLog, ItemLike pWood, ItemLike pPlanks, ItemLike pSTLog, ItemLike pSTWood){
        //makes 4 planks from log
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pLog)
                .unlockedBy("has_" + getItemName(pLog), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pLog).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pLog));

        //makes planks from wood
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pWood)
                .unlockedBy("has_" + getItemName(pWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pWood).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pWood));

        //makes sticks from planks
        ShapedRecipeBuilder.shaped(pCategory, Items.STICK, 4)
                .pattern("A")
                .pattern("A")
                .define('A', pPlanks)
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + "sticks_from_" + getItemName(pPlanks));

        //makes planks from STRIPPED log
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pSTLog)
                .unlockedBy("has_" + getItemName(pSTLog), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pSTLog).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pSTLog));

        //makes planks from STRIPPED wood
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pSTWood)
                .unlockedBy("has_" + getItemName(pSTWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pSTWood).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pSTWood));

        //makes wood from logs
        ShapedRecipeBuilder.shaped(pCategory, pWood, 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', pLog)
                .unlockedBy("has_" + getItemName(pWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pWood).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pWood) + "_from_" + getItemName(pLog));

        //makes stripped wood from stripped logs
        ShapedRecipeBuilder.shaped(pCategory, pSTWood, 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', pSTLog)
                .unlockedBy("has_" + getItemName(pSTWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pSTWood).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pSTWood) + "_from_" + getItemName(pSTLog));
    }

    //Makes shapesss
    protected static void plankShapeParts(RecipeOutput pFinishedRecipe, ItemLike pPlanks, ItemLike pButton, ItemLike pDoor, ItemLike pFence, ItemLike pFenceGate, ItemLike pPressurePlate, ItemLike pSlab, ItemLike pStairs, ItemLike pTrap, ItemLike pWall){
        buttonBuilder(pButton, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pButton) + "_from_" + getItemName(pPlanks));
        doorBuilder(pDoor, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pDoor) + "_from_" + getItemName(pPlanks));
        fenceBuilder(pFence, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pFence) + "_from_" + getItemName(pPlanks));
        fenceGateBuilder(pFenceGate, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pFenceGate) + "_from_" + getItemName(pPlanks));
        pressurePlate(pFinishedRecipe, pPressurePlate, pPlanks);
        slabBuilder(RecipeCategory.MISC , pSlab, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pSlab) + "_from_" + getItemName(pPlanks));
        stairBuilder(pStairs, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pStairs) + "_from_" + getItemName(pPlanks));
        trapdoorBuilder(pTrap, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pTrap) + "_from_" + getItemName(pPlanks));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pWall, 6)
                .pattern("ABA")
                .pattern("ABA")
                .define('A', pPlanks)
                .define('B', pSlab)
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pPlanks).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pWall) + "_from_" + getItemName(pPlanks));

    }
}
