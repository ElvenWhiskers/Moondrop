package com.elvenwhiskers.moondrop.item;


import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Moondrop.MODID);

    public static final Supplier<CreativeModeTab> MOONDROP_TAB =
            CREATIVE_MODE_TABS.register("moondrop_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup.moondrop.moondrop_tab"))
                    .icon(() -> new ItemStack(ModItems.AEGIS_INGOT.get()))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AEGIS_INGOT);
                        output.accept(ModItems.RAW_AEGIS);

                        output.accept(ModItems.KAOLIN);

                        output.accept(ModItems.PASTEL_RED_DYE);
                        output.accept(ModItems.PASTEL_ORANGE_DYE);
                        output.accept(ModItems.PASTEL_YELLOW_DYE);
                        output.accept(ModItems.PASTEL_LIME_DYE);
                        output.accept(ModItems.PASTEL_GREEN_DYE);
                        output.accept(ModItems.PASTEL_CYAN_DYE);
                        output.accept(ModItems.PASTEL_BABY_BLUE_DYE);
                        output.accept(ModItems.PASTEL_PURPLE_DYE);
                        output.accept(ModItems.PASTEL_MAGENTA_DYE);
                        output.accept(ModItems.PASTEL_PINK_DYE);

                        output.accept(ModBlocks.AEGIS_BLOCK);
                        output.accept(ModBlocks.AEGIS_ORE);

                        output.accept(ModBlocks.MOONDROP_CAULDRON);

                        output.accept(ModBlocks.MAGNOLIA_LOG);
                        output.accept(ModBlocks.MAGNOLIA_WOOD);
                        output.accept(ModBlocks.STRIPPED_MAGNOLIA_LOG);
                        output.accept(ModBlocks.STRIPPED_MAGNOLIA_WOOD);
                        output.accept(ModBlocks.MAGNOLIA_PLANKS);
                        output.accept(ModBlocks.MAGNOLIA_SLAB);
                        output.accept(ModBlocks.MAGNOLIA_STAIRS);
                        output.accept(ModBlocks.MAGNOLIA_PRESSURE_PLATE);
                        output.accept(ModBlocks.MAGNOLIA_BUTTON);
                        output.accept(ModBlocks.MAGNOLIA_FENCE);
                        output.accept(ModBlocks.MAGNOLIA_FENCE_GATE);
                        output.accept(ModBlocks.MAGNOLIA_WALL);
                        output.accept(ModBlocks.MAGNOLIA_DOOR);
                        output.accept(ModBlocks.MAGNOLIA_TRAPDOOR);
                        output.accept(ModBlocks.MAGNOLIA_LEAVES);
                        output.accept(ModBlocks.MAGNOLIA_SAPLING);

                        output.accept(ModBlocks.LARKSPUR_LOG);
                        output.accept(ModBlocks.LARKSPUR_WOOD);
                        output.accept(ModBlocks.STRIPPED_LARKSPUR_LOG);
                        output.accept(ModBlocks.STRIPPED_LARKSPUR_WOOD);
                        output.accept(ModBlocks.LARKSPUR_PLANKS);
                        output.accept(ModBlocks.LARKSPUR_SLAB);
                        output.accept(ModBlocks.LARKSPUR_STAIRS);
                        output.accept(ModBlocks.LARKSPUR_PRESSURE_PLATE);
                        output.accept(ModBlocks.LARKSPUR_BUTTON);
                        output.accept(ModBlocks.LARKSPUR_FENCE);
                        output.accept(ModBlocks.LARKSPUR_FENCE_GATE);
                        output.accept(ModBlocks.LARKSPUR_WALL);
                        output.accept(ModBlocks.LARKSPUR_DOOR);
                        output.accept(ModBlocks.LARKSPUR_TRAPDOOR);
                        output.accept(ModBlocks.LARKSPUR_LEAVES);
                        output.accept(ModBlocks.LARKSPUR_SAPLING);

                        output.accept(ModBlocks.BRIGHTSTONE);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.MOSSY_BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.CRACKED_BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.CHISELED_BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.COBBLED_BRIGHTSTONE);
                        output.accept(ModBlocks.MOSSY_COBBLED_BRIGHTSTONE);

                        output.accept(ModBlocks.BRIGHTSTONE_STAIRS);
                        output.accept(ModBlocks.BRIGHTSTONE_SLAB);
                        output.accept(ModBlocks.BRIGHTSTONE_FENCE);
                        output.accept(ModBlocks.BRIGHTSTONE_FENCE_GATE);
                        output.accept(ModBlocks.BRIGHTSTONE_DOOR);
                        output.accept(ModBlocks.BRIGHTSTONE_TRAPDOOR);
                        output.accept(ModBlocks.BRIGHTSTONE_PRESSURE_PLATE);
                        output.accept(ModBlocks.BRIGHTSTONE_BUTTON);
                        output.accept(ModBlocks.BRIGHTSTONE_WALL);

                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_STAIRS);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_SLAB);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_FENCE);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_DOOR);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_BUTTON);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS_WALL);

                        output.accept(ModBlocks.KAOLIN_BLOCK);

                        output.accept(ModBlocks.PASTEL_BABY_BLUE_BRICKS);
                        output.accept(ModBlocks.PASTEL_BABY_BLUE_COBBLE);
                    })).build());




    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
