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

        //placeholder, replace
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AEGIS_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AEGIS_INGOT.get())
                .unlockedBy("has_aegis_ingot", has(ModItems.AEGIS_INGOT.get())).save(pRecipeOutput);

        //another placeholder.
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 9)
                .requires(ModBlocks.AEGIS_BLOCK.get())
                .unlockedBy("has_aegis_block", has(ModBlocks.AEGIS_BLOCK.get())).save(pRecipeOutput);

        //Simple Compacting 4's
        simpleFour(pRecipeOutput, ModBlocks.KAOLIN_BLOCK, ModItems.KAOLIN, 1);
        simpleFour(pRecipeOutput, ModBlocks.PRISM_BLOCK, ModItems.PRISM_SHARDS, 1);

        //Smelting catagory.
        oreSmelting(pRecipeOutput, AEGIS_SMELTABLES, RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 0.25f, 200, "aegis_ingot");
        oreBlasting(pRecipeOutput, AEGIS_SMELTABLES, RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 0.25f, 100, "aegis_ingot");

        //Dyes
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.RED_DYE, ModItems.PASTEL_RED_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.ORANGE_DYE, ModItems.PASTEL_ORANGE_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.YELLOW_DYE, ModItems.PASTEL_YELLOW_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.LIME_DYE, ModItems.PASTEL_LIME_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.GREEN_DYE, ModItems.PASTEL_GREEN_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.CYAN_DYE, ModItems.PASTEL_CYAN_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.LIGHT_BLUE_DYE, ModItems.PASTEL_BABY_BLUE_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.PURPLE_DYE, ModItems.PASTEL_PURPLE_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.MAGENTA_DYE, ModItems.PASTEL_MAGENTA_DYE.get());
        simpleDyes(pRecipeOutput, RecipeCategory.MISC, Items.PINK_DYE, ModItems.PASTEL_PINK_DYE.get());

        //coloredBlocks
        coloredBlocks(pRecipeOutput, RecipeCategory.MISC, ModItems.PASTEL_BABY_BLUE_DYE, Blocks.BRICKS, ModBlocks.PASTEL_BABY_BLUE_BRICKS.get());
        coloredBlocks(pRecipeOutput, RecipeCategory.MISC, ModItems.PASTEL_BABY_BLUE_DYE, Blocks.COBBLESTONE, ModBlocks.PASTEL_BABY_BLUE_COBBLE.get());

        //LOG PARTS ***
        //1. Adds all log varients, sticks, planks from logs, stripped, but not shapes.
        treeParts(pRecipeOutput, RecipeCategory.MISC,
                ModBlocks.MAGNOLIA_LOG.get(),
                ModBlocks.MAGNOLIA_WOOD.get(),
                ModBlocks.MAGNOLIA_PLANKS.get(),
                ModBlocks.STRIPPED_MAGNOLIA_LOG.get(),
                ModBlocks.STRIPPED_MAGNOLIA_WOOD.get());
        treeParts(pRecipeOutput, RecipeCategory.MISC,
                ModBlocks.LARKSPUR_LOG.get(),
                ModBlocks.LARKSPUR_WOOD.get(),
                ModBlocks.LARKSPUR_PLANKS.get(),
                ModBlocks.STRIPPED_LARKSPUR_LOG.get(),
                ModBlocks.STRIPPED_LARKSPUR_WOOD.get());


        //2. Next add SHAPES, UGH, this was initally a pain
        allShapeParts(pRecipeOutput,
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
        allShapeParts(pRecipeOutput,
                ModBlocks.LARKSPUR_PLANKS.get(),
                ModBlocks.LARKSPUR_BUTTON.get(),
                ModBlocks.LARKSPUR_DOOR.get(),
                ModBlocks.LARKSPUR_FENCE.get(),
                ModBlocks.LARKSPUR_FENCE_GATE.get(),
                ModBlocks.LARKSPUR_PRESSURE_PLATE.get(),
                ModBlocks.LARKSPUR_SLAB.get(),
                ModBlocks.LARKSPUR_STAIRS.get(),
                ModBlocks.LARKSPUR_TRAPDOOR.get(),
                ModBlocks.LARKSPUR_WALL.get());


        //STONE PARTS ***
        //1.
        allShapeParts(pRecipeOutput,
                ModBlocks.BRIGHTSTONE.get(),
                ModBlocks.BRIGHTSTONE_BUTTON.get(),
                ModBlocks.BRIGHTSTONE_DOOR.get(),
                ModBlocks.BRIGHTSTONE_FENCE.get(),
                ModBlocks.BRIGHTSTONE_FENCE_GATE.get(),
                ModBlocks.BRIGHTSTONE_PRESSURE_PLATE.get(),
                ModBlocks.BRIGHTSTONE_SLAB.get(),
                ModBlocks.BRIGHTSTONE_STAIRS.get(),
                ModBlocks.BRIGHTSTONE_TRAPDOOR.get(),
                ModBlocks.BRIGHTSTONE_WALL.get());

        allShapeParts(pRecipeOutput,
                ModBlocks.BRIGHTSTONE_BRICKS.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_BUTTON.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_DOOR.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_FENCE.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_SLAB.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_STAIRS.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_WALL.get());

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

    //Naturally this smelts ore from a furnace
    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    //This Blasts ore from a blasting furnace
    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    //this is the base method for the two methods above.
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, Moondrop.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    //As the name says it's a simple dye recipe method
    protected static void simpleDyes(RecipeOutput pFinishedRecipe, RecipeCategory pCategory, ItemLike pDye, ItemLike pResultDye){
        ShapedRecipeBuilder.shaped(pCategory, pResultDye, 2)
                .pattern("AB")
                .define('A', pDye)
                .define('B', Items.WHITE_DYE)
                .unlockedBy("has_" + getItemName(pDye), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pDye).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pResultDye) + "_from_" + getItemName(pDye) + "_and_white");
    }

    //This is a simple block dying function
    protected static void coloredBlocks(RecipeOutput pFinishedRecipe, RecipeCategory pCategory, ItemLike pDye, ItemLike pInputBlock, ItemLike pResultBlocks){
        ShapedRecipeBuilder.shaped(pCategory, pResultBlocks, 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', pInputBlock)
                .define('B', pDye)
                .unlockedBy("has_" + getItemName(pDye), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pDye).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pResultBlocks) + "_from_" + getItemName(pInputBlock));
    }

    //This compacts 4 inputs into configurable result.
    protected static void simpleFour(RecipeOutput pFinishedRecipe, ItemLike pResult, ItemLike pInput, int num){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pResult, num)
                .pattern("AA")
                .pattern("AA")
                .define('A', pInput)
                .unlockedBy("has_" + getItemName(pInput), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pInput).build()))
                .save(pFinishedRecipe, Moondrop.MODID + ":" + getItemName(pResult) + "_from_" + getItemName(pInput));
    }

    //Custom tree parts for making all the tree recipes.
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
    protected static void allShapeParts(RecipeOutput pFinishedRecipe, ItemLike pPlanks, ItemLike pButton, ItemLike pDoor, ItemLike pFence, ItemLike pFenceGate, ItemLike pPressurePlate, ItemLike pSlab, ItemLike pStairs, ItemLike pTrap, ItemLike pWall){
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
