package com.elvenwhiskers.moondrop.datagen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.awt.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Moondrop.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AEGIS_BLOCK);
        blockWithItem(ModBlocks.AEGIS_ORE);

        //1. First add new log type here: (Axis)
        logSet(ModBlocks.MAGNOLIA_LOG.get(), ModBlocks.MAGNOLIA_WOOD.get(), ModBlocks.STRIPPED_MAGNOLIA_LOG.get(), ModBlocks.STRIPPED_MAGNOLIA_WOOD.get());
        logSet(ModBlocks.LARKSPUR_LOG.get(), ModBlocks.LARKSPUR_WOOD.get(), ModBlocks.STRIPPED_LARKSPUR_LOG.get(), ModBlocks.STRIPPED_LARKSPUR_WOOD.get());

        //2. Then add new log type here as well: (Log shapes)
        blockWithItem(ModBlocks.MAGNOLIA_PLANKS);
        blockItem(ModBlocks.MAGNOLIA_LOG);
        blockItem(ModBlocks.MAGNOLIA_WOOD);
        blockItem(ModBlocks.STRIPPED_MAGNOLIA_LOG);
        blockItem(ModBlocks.STRIPPED_MAGNOLIA_WOOD);

        blockWithItem(ModBlocks.LARKSPUR_PLANKS);
        blockItem(ModBlocks.LARKSPUR_LOG);
        blockItem(ModBlocks.LARKSPUR_WOOD);
        blockItem(ModBlocks.STRIPPED_LARKSPUR_LOG);
        blockItem(ModBlocks.STRIPPED_LARKSPUR_WOOD);

        //3. Then add plank shapes for easier all around shaping. Must go AFTER planks, otherwise ERROR!:
        plankShapes(ModBlocks.MAGNOLIA_PLANKS.get(),
                        ModBlocks.MAGNOLIA_STAIRS.get(),
                        ModBlocks.MAGNOLIA_SLAB.get(),
                        ModBlocks.MAGNOLIA_BUTTON.get(),
                        ModBlocks.MAGNOLIA_PRESSURE_PLATE.get(),
                        ModBlocks.MAGNOLIA_FENCE.get(),
                        ModBlocks.MAGNOLIA_FENCE_GATE.get(),
                        ModBlocks.MAGNOLIA_WALL.get());
        doorBlockWithRenderType((DoorBlock)ModBlocks.MAGNOLIA_DOOR.get(), modLoc("block/magnolia_door_bottom"), modLoc("block/magnolia_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.MAGNOLIA_TRAPDOOR.get(), modLoc("block/magnolia_trapdoor"), true, "cutout");
        plankShapes(ModBlocks.LARKSPUR_PLANKS.get(),
                    ModBlocks.LARKSPUR_STAIRS.get(),
                    ModBlocks.LARKSPUR_SLAB.get(),
                    ModBlocks.LARKSPUR_BUTTON.get(),
                    ModBlocks.LARKSPUR_PRESSURE_PLATE.get(),
                    ModBlocks.LARKSPUR_FENCE.get(),
                    ModBlocks.LARKSPUR_FENCE_GATE.get(),
                    ModBlocks.LARKSPUR_WALL.get());
        doorBlockWithRenderType((DoorBlock)ModBlocks.LARKSPUR_DOOR.get(), modLoc("block/larkspur_door_bottom"), modLoc("block/larkspur_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.LARKSPUR_TRAPDOOR.get(), modLoc("block/larkspur_trapdoor"), true, "cutout");

        //4. Add remaining blocks.
        blockItem(ModBlocks.MAGNOLIA_STAIRS);
        blockItem(ModBlocks.MAGNOLIA_SLAB);
        blockItem(ModBlocks.MAGNOLIA_PRESSURE_PLATE);
        blockItem(ModBlocks.MAGNOLIA_FENCE_GATE);
        blockItem(ModBlocks.MAGNOLIA_TRAPDOOR, "_bottom");
        leavesBlock(ModBlocks.MAGNOLIA_LEAVES);
        saplingBlock(ModBlocks.MAGNOLIA_SAPLING);

        blockItem(ModBlocks.LARKSPUR_STAIRS);
        blockItem(ModBlocks.LARKSPUR_SLAB);
        blockItem(ModBlocks.LARKSPUR_PRESSURE_PLATE);
        blockItem(ModBlocks.LARKSPUR_FENCE_GATE);
        blockItem(ModBlocks.LARKSPUR_TRAPDOOR, "_bottom");
        leavesBlock(ModBlocks.LARKSPUR_LEAVES);
        saplingBlock(ModBlocks.LARKSPUR_SAPLING);




        //FOR STONE VARIENTS!: This is actually just regular for now.
        //1. Add here.
        blockWithItem(ModBlocks.BRIGHTSTONE);
        blockWithItem(ModBlocks.BRIGHTSTONE_BRICKS);
        blockWithItem(ModBlocks.MOSSY_BRIGHTSTONE_BRICKS);
        blockWithItem(ModBlocks.CRACKED_BRIGHTSTONE_BRICKS);
        blockWithItem(ModBlocks.CHISELED_BRIGHTSTONE_BRICKS);
        blockWithItem(ModBlocks.COBBLED_BRIGHTSTONE);
        blockWithItem(ModBlocks.MOSSY_COBBLED_BRIGHTSTONE);

        blockPastelBlueWithItem(ModBlocks.PASTEL_BABY_BLUE_BRICKS);
        blockPastelBlueWithItem(ModBlocks.PASTEL_BABY_BLUE_COBBLE);

        //2. Add shapes here
        plankShapes(ModBlocks.BRIGHTSTONE.get(),
                ModBlocks.BRIGHTSTONE_STAIRS.get(),
                ModBlocks.BRIGHTSTONE_SLAB.get(),
                ModBlocks.BRIGHTSTONE_BUTTON.get(),
                ModBlocks.BRIGHTSTONE_PRESSURE_PLATE.get(),
                ModBlocks.BRIGHTSTONE_FENCE.get(),
                ModBlocks.BRIGHTSTONE_FENCE_GATE.get(),
                ModBlocks.BRIGHTSTONE_WALL.get());
        doorBlockWithRenderType((DoorBlock)ModBlocks.BRIGHTSTONE_DOOR.get(), modLoc("block/brightstone_door_bottom"), modLoc("block/brightstone_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.BRIGHTSTONE_TRAPDOOR.get(), modLoc("block/brightstone_trapdoor"), true, "cutout");
        plankShapes(ModBlocks.BRIGHTSTONE_BRICKS.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_STAIRS.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_SLAB.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_BUTTON.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_FENCE.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE.get(),
                ModBlocks.BRIGHTSTONE_BRICKS_WALL.get());
        doorBlockWithRenderType((DoorBlock)ModBlocks.BRIGHTSTONE_BRICKS_DOOR.get(), modLoc("block/brightstone_bricks_door_bottom"), modLoc("block/brightstone_bricks_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR.get(), modLoc("block/brightstone_bricks_trapdoor"), true, "cutout");

        //3. item varients
        blockItem(ModBlocks.BRIGHTSTONE_STAIRS);
        blockItem(ModBlocks.BRIGHTSTONE_SLAB);
        blockItem(ModBlocks.BRIGHTSTONE_PRESSURE_PLATE);
        blockItem(ModBlocks.BRIGHTSTONE_FENCE_GATE);
        blockItem(ModBlocks.BRIGHTSTONE_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.BRIGHTSTONE_BRICKS_STAIRS);
        blockItem(ModBlocks.BRIGHTSTONE_BRICKS_SLAB);
        blockItem(ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE);
        blockItem(ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE);
        blockItem(ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR, "_bottom");


    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().cubeAll(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("moondrop:block/" + deferredBlock.getId().getPath()));
    }

    private void blockPastelBlueWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cubeAll(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), deferredBlock.getId().withPath("block/pastels/baby_blue/" + deferredBlock.getId().getPath())));
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("moondrop:block/" + deferredBlock.getId().getPath()));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("moondrop:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void plankShapes(Block plankType, Block stairType, Block slabType, Block buttonType, Block pressureType, Block fenceType, Block fenceGateType, Block wallType){
        stairsBlock((StairBlock) stairType, blockTexture(plankType));
        slabBlock(((SlabBlock) slabType), blockTexture(plankType), blockTexture(plankType));
        buttonBlock((ButtonBlock) buttonType, blockTexture(plankType));
        pressurePlateBlock((PressurePlateBlock) pressureType, blockTexture(plankType));
        fenceBlock((FenceBlock) fenceType, blockTexture(plankType));
        fenceGateBlock((FenceGateBlock) fenceGateType, blockTexture(plankType));
        wallBlock((WallBlock) wallType, blockTexture(plankType));
    }

    private void logSet(Block pLogType, Block pWoodType, Block pStLogType, Block pStWoodType){
        logBlock((RotatedPillarBlock) pLogType);
        axisBlock(((RotatedPillarBlock) pWoodType), blockTexture(pLogType), blockTexture(pLogType));
        logBlock((RotatedPillarBlock) pStLogType);
        axisBlock(((RotatedPillarBlock) pStWoodType), blockTexture(pStLogType), blockTexture(pStLogType));
    }


}
