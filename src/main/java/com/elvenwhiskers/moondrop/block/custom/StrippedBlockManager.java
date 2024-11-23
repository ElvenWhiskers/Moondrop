package com.elvenwhiskers.moondrop.block.custom;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class StrippedBlockManager {
    private static final Map<Block, Supplier<Block>> strippedBlocks = new HashMap<>();

    static{
        addStrippedBlock(ModBlocks.MAGNOLIA_LOG, ModBlocks.STRIPPED_MAGNOLIA_LOG);
        addStrippedBlock(ModBlocks.MAGNOLIA_WOOD, ModBlocks.STRIPPED_MAGNOLIA_WOOD);
        addStrippedBlock(ModBlocks.LARKSPUR_LOG, ModBlocks.STRIPPED_LARKSPUR_LOG);
        addStrippedBlock(ModBlocks.LARKSPUR_WOOD, ModBlocks.STRIPPED_LARKSPUR_WOOD);
        addStrippedBlock(ModBlocks.WISTERIA_LOG, ModBlocks.STRIPPED_WISTERIA_LOG);
        addStrippedBlock(ModBlocks.WISTERIA_WOOD, ModBlocks.STRIPPED_WISTERIA_WOOD);
    }

    private static void addStrippedBlock(DeferredBlock<Block> logBlock, DeferredBlock<Block> strippedLogBlock){
        strippedBlocks.put(logBlock.get(), strippedLogBlock);
    }

    @Nullable
    public static Block getStrippedBlock(Block logBlock) {
        return strippedBlocks.get(logBlock).get();
    }

}
