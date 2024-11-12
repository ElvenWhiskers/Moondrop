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

                        output.accept(ModBlocks.AEGIS_BLOCK);
                        output.accept(ModBlocks.AEGIS_ORE);

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

                        output.accept(ModBlocks.BRIGHTSTONE);
                        output.accept(ModBlocks.BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.MOSSY_BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.CRACKED_BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.CHISELED_BRIGHTSTONE_BRICKS);
                        output.accept(ModBlocks.COBBLED_BRIGHTSTONE);
                        output.accept(ModBlocks.MOSSY_COBBLED_BRIGHTSTONE);
                    })).build());




    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
