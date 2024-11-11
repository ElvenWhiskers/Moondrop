package com.elvenwhiskers.moondrop.datagen;

import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.AEGIS_BLOCK.get());

        this.add(ModBlocks.AEGIS_ORE.get(),
                block -> createOreDrop(ModBlocks.AEGIS_ORE.get(), ModItems.RAW_AEGIS.get()));

        dropSelf(ModBlocks.MAGNOLIA_LOG.get());
        dropSelf(ModBlocks.MAGNOLIA_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_MAGNOLIA_LOG.get());
        dropSelf(ModBlocks.STRIPPED_MAGNOLIA_WOOD.get());
        dropSelf(ModBlocks.MAGNOLIA_PLANKS.get());
        this.add(ModBlocks.MAGNOLIA_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.MAGNOLIA_SLAB.get()));
        dropSelf(ModBlocks.MAGNOLIA_STAIRS.get());
        dropSelf(ModBlocks.MAGNOLIA_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.MAGNOLIA_BUTTON.get());
        dropSelf(ModBlocks.MAGNOLIA_FENCE.get());
        dropSelf(ModBlocks.MAGNOLIA_FENCE_GATE.get());
        dropSelf(ModBlocks.MAGNOLIA_WALL.get());
        dropSelf(ModBlocks.MAGNOLIA_TRAPDOOR.get());
        this.add(ModBlocks.MAGNOLIA_DOOR.get(),
                block -> createDoorTable(ModBlocks.MAGNOLIA_DOOR.get()));
        this.add(ModBlocks.MAGNOLIA_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.MAGNOLIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.MAGNOLIA_SAPLING.get());
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
