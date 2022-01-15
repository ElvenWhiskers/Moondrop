package com.elvenwhiskers.moondrop.block;

import com.elvenwhiskers.moondrop.MoonDrop;
import com.elvenwhiskers.moondrop.block.custom.ModFlammableRotatedPillarBlock;
import com.elvenwhiskers.moondrop.item.ModCreativeModeTab;
import com.elvenwhiskers.moondrop.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MoonDrop.MOD_ID);


    public static final RegistryObject<Block> AEGIS_BLOCK = registerBlock("aegis_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> AEGIS_ORE = registerBlock("aegis_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> DEEPSLATE_AEGIS_ORE = registerBlock("deepslate_aegis_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> RAW_AEGIS_BLOCK = registerBlock("raw_aegis_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> PINK_STARFRUIT_LOG = registerBlock("pink_starfruit_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> PINK_STARFRUIT_WOOD = registerBlock("pink_starfruit_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> STRIPPED_PINK_STARFRUIT_LOG = registerBlock("stripped_pink_starfruit_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> STRIPPED_PINK_STARFRUIT_WOOD = registerBlock("stripped_pink_starfruit_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ModCreativeModeTab.MOONDROP_TAB);

    public static final RegistryObject<Block> PINK_STARFRUIT_PLANKS = registerBlock("pink_starfruit_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            }, ModCreativeModeTab.MOONDROP_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
