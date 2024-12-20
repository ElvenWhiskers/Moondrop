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
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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
        this.add(ModBlocks.PRISM_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.PRISM_ORE.get(), ModItems.PRISM_SHARDS.get(), 2, 5));
        this.add(ModBlocks.BRIGHT_PRISM_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.BRIGHT_PRISM_ORE.get(), ModItems.PRISM_SHARDS.get(), 2, 5));

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

        dropSelf(ModBlocks.LARKSPUR_LOG.get());
        dropSelf(ModBlocks.LARKSPUR_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_LARKSPUR_LOG.get());
        dropSelf(ModBlocks.STRIPPED_LARKSPUR_WOOD.get());
        dropSelf(ModBlocks.LARKSPUR_PLANKS.get());
        this.add(ModBlocks.LARKSPUR_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.LARKSPUR_SLAB.get()));
        dropSelf(ModBlocks.LARKSPUR_STAIRS.get());
        dropSelf(ModBlocks.LARKSPUR_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LARKSPUR_BUTTON.get());
        dropSelf(ModBlocks.LARKSPUR_FENCE.get());
        dropSelf(ModBlocks.LARKSPUR_FENCE_GATE.get());
        dropSelf(ModBlocks.LARKSPUR_WALL.get());
        dropSelf(ModBlocks.LARKSPUR_TRAPDOOR.get());
        this.add(ModBlocks.LARKSPUR_DOOR.get(),
                block -> createDoorTable(ModBlocks.LARKSPUR_DOOR.get()));
        this.add(ModBlocks.LARKSPUR_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.LARKSPUR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.LARKSPUR_SAPLING.get());

        dropSelf(ModBlocks.WISTERIA_LOG.get());
        dropSelf(ModBlocks.WISTERIA_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_WISTERIA_LOG.get());
        dropSelf(ModBlocks.STRIPPED_WISTERIA_WOOD.get());
        dropSelf(ModBlocks.WISTERIA_PLANKS.get());
        this.add(ModBlocks.WISTERIA_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.WISTERIA_SLAB.get()));
        dropSelf(ModBlocks.WISTERIA_STAIRS.get());
        dropSelf(ModBlocks.WISTERIA_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.WISTERIA_BUTTON.get());
        dropSelf(ModBlocks.WISTERIA_FENCE.get());
        dropSelf(ModBlocks.WISTERIA_FENCE_GATE.get());
        dropSelf(ModBlocks.WISTERIA_WALL.get());
        dropSelf(ModBlocks.WISTERIA_TRAPDOOR.get());
        this.add(ModBlocks.WISTERIA_DOOR.get(),
                block -> createDoorTable(ModBlocks.WISTERIA_DOOR.get()));
        this.add(ModBlocks.WISTERIA_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLUE_WISTERIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.BLUE_WISTERIA_SAPLING.get());

        this.add(ModBlocks.BRIGHTSTONE.get(), customSilkTouchDrop(ModBlocks.BRIGHTSTONE.get(), ModBlocks.COBBLED_BRIGHTSTONE.get()));
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS.get());
        dropSelf(ModBlocks.MOSSY_BRIGHTSTONE_BRICKS.get());
        dropSelf(ModBlocks.CRACKED_BRIGHTSTONE_BRICKS.get());
        dropSelf(ModBlocks.CHISELED_BRIGHTSTONE_BRICKS.get());
        dropSelf(ModBlocks.COBBLED_BRIGHTSTONE.get());
        dropSelf(ModBlocks.MOSSY_COBBLED_BRIGHTSTONE.get());

        this.add(ModBlocks.BRIGHTSTONE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BRIGHTSTONE_SLAB.get()));
        dropSelf(ModBlocks.BRIGHTSTONE_STAIRS.get());
        dropSelf(ModBlocks.BRIGHTSTONE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BRIGHTSTONE_BUTTON.get());
        dropSelf(ModBlocks.BRIGHTSTONE_FENCE.get());
        dropSelf(ModBlocks.BRIGHTSTONE_FENCE_GATE.get());
        dropSelf(ModBlocks.BRIGHTSTONE_WALL.get());
        dropSelf(ModBlocks.BRIGHTSTONE_TRAPDOOR.get());
        this.add(ModBlocks.BRIGHTSTONE_DOOR.get(),
                block -> createDoorTable(ModBlocks.BRIGHTSTONE_DOOR.get()));

        this.add(ModBlocks.BRIGHTSTONE_BRICKS_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BRIGHTSTONE_BRICKS_SLAB.get()));
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS_STAIRS.get());
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS_BUTTON.get());
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS_FENCE.get());
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS_FENCE_GATE.get());
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS_WALL.get());
        dropSelf(ModBlocks.BRIGHTSTONE_BRICKS_TRAPDOOR.get());
        this.add(ModBlocks.BRIGHTSTONE_BRICKS_DOOR.get(),
                block -> createDoorTable(ModBlocks.BRIGHTSTONE_BRICKS_DOOR.get()));

        dropSelf(ModBlocks.MOONDROP_CAULDRON.get());

        dropSelf(ModBlocks.PASTEL_BABY_BLUE_BRICKS.get());
        dropSelf(ModBlocks.PASTEL_BABY_BLUE_COBBLE.get());

        this.add(ModBlocks.PRISM_BLOCK.get(), customSilkTouchClayDrop(ModBlocks.PRISM_BLOCK.get(), ModItems.PRISM_SHARDS.get()));
        this.add(ModBlocks.KAOLIN_BLOCK.get(), customSilkTouchClayDrop(ModBlocks.KAOLIN_BLOCK.get(), ModItems.KAOLIN.get()));


        this.add(ModBlocks.WISTERIA_HALF_BLUE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLUE_WISTERIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.BLUE_WISTERIA_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLUE_WISTERIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.HANGING_BLUE_WISTERIA_VINES_BASE.get()); //make drop head instead
        dropSelf(ModBlocks.HANGING_BLUE_WISTERIA_VINES_HEAD.get());


    }

    protected LootTable.Builder customSilkTouchDrop(Block blockItself, Block silkBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(blockItself).when(this.hasSilkTouch())
                        .otherwise(applyExplosionDecay(silkBlock, LootItem.lootTableItem(silkBlock)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))))));}

    protected LootTable.Builder customSilkTouchClayDrop(Block blockItself, Item silkItem) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(blockItself).when(this.hasSilkTouch())
                        .otherwise(applyExplosionDecay(silkItem, LootItem.lootTableItem(silkItem)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))))));}


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
