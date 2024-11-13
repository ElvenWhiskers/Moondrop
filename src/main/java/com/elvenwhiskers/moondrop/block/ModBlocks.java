package com.elvenwhiskers.moondrop.block;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.custom.ModCauldronBlock;
import com.elvenwhiskers.moondrop.block.custom.ModFlammableLeaves;
import com.elvenwhiskers.moondrop.block.custom.ModFlammablePlanks;
import com.elvenwhiskers.moondrop.block.custom.ModFlammableRotatedPillarBlock;
import com.elvenwhiskers.moondrop.item.ModItems;
import com.elvenwhiskers.moondrop.worldgen.tree.ModTreeGrowers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Moondrop.MODID);

    public static final DeferredBlock<Block> AEGIS_BLOCK = registerBlock("aegis_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> AEGIS_ORE = registerBlock("aegis_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,5),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> MOONDROP_CAULDRON = registerBlock("moondrop_cauldron",
            () -> new ModCauldronBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<Block> MAGNOLIA_LOG = registerBlock("magnolia_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> MAGNOLIA_WOOD = registerBlock("magnolia_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_MAGNOLIA_LOG = registerBlock("stripped_magnolia_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_MAGNOLIA_WOOD = registerBlock("stripped_magnolia_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<Block> MAGNOLIA_PLANKS = registerBlock("magnolia_planks",
            () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> MAGNOLIA_LEAVES = registerBlock("magnolia_leaves",
            () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<Block> MAGNOLIA_SLAB = registerBlock("magnolia_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<Block> MAGNOLIA_STAIRS = registerBlock("magnolia_stairs",
            () -> new StairBlock(ModBlocks.MAGNOLIA_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> MAGNOLIA_PRESSURE_PLATE = registerBlock("magnolia_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> MAGNOLIA_BUTTON = registerBlock("magnolia_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties.of().strength(4f).noCollission()));
    public static final DeferredBlock<Block> MAGNOLIA_FENCE = registerBlock("magnolia_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> MAGNOLIA_FENCE_GATE = registerBlock("magnolia_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> MAGNOLIA_WALL = registerBlock("magnolia_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> MAGNOLIA_DOOR = registerBlock("magnolia_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(4f).noOcclusion()));
    public static final DeferredBlock<Block> MAGNOLIA_TRAPDOOR = registerBlock("magnolia_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(4f).noOcclusion()));
    public static final DeferredBlock<Block> MAGNOLIA_SAPLING = registerBlock("magnolia_sapling",
            () -> new SaplingBlock(ModTreeGrowers.MAGNOLIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> BRIGHTSTONE = registerBlock("brightstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS = registerBlock("brightstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> MOSSY_BRIGHTSTONE_BRICKS = registerBlock("mossy_brightstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> CRACKED_BRIGHTSTONE_BRICKS = registerBlock("cracked_brightstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS)));
    public static final DeferredBlock<Block> CHISELED_BRIGHTSTONE_BRICKS = registerBlock("chiseled_brightstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS)));
    public static final DeferredBlock<Block> COBBLED_BRIGHTSTONE = registerBlock("cobbled_brightstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final DeferredBlock<Block> MOSSY_COBBLED_BRIGHTSTONE = registerBlock("mossy_cobbled_brightstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));

    public static final DeferredBlock<Block> BRIGHTSTONE_STAIRS = registerBlock("brightstone_stairs",
            () -> new StairBlock(ModBlocks.BRIGHTSTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_SLAB = registerBlock("brightstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<Block> BRIGHTSTONE_FENCE = registerBlock("brightstone_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_FENCE_GATE = registerBlock("brightstone_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_DOOR = registerBlock("brightstone_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f).noOcclusion()));
    public static final DeferredBlock<Block> BRIGHTSTONE_TRAPDOOR = registerBlock("brightstone_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f).noOcclusion()));
    public static final DeferredBlock<Block> BRIGHTSTONE_PRESSURE_PLATE = registerBlock("brightstone_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_BUTTON = registerBlock("brightstone_button",
            () -> new ButtonBlock(BlockSetType.STONE, 10, BlockBehaviour.Properties.of().strength(4f).noCollission()));
    public static final DeferredBlock<Block> BRIGHTSTONE_WALL = registerBlock("brightstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f)));

    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_STAIRS = registerBlock("brightstone_bricks_stairs",
            () -> new StairBlock(ModBlocks.BRIGHTSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_SLAB = registerBlock("brightstone_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_FENCE = registerBlock("brightstone_bricks_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_FENCE_GATE = registerBlock("brightstone_bricks_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_DOOR = registerBlock("brightstone_bricks_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f).noOcclusion()));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_TRAPDOOR = registerBlock("brightstone_bricks_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f).noOcclusion()));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_PRESSURE_PLATE = registerBlock("brightstone_bricks_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(4f)));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_BUTTON = registerBlock("brightstone_bricks_button",
            () -> new ButtonBlock(BlockSetType.STONE, 10, BlockBehaviour.Properties.of().strength(4f).noCollission()));
    public static final DeferredBlock<Block> BRIGHTSTONE_BRICKS_WALL = registerBlock("brightstone_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f)));





    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
