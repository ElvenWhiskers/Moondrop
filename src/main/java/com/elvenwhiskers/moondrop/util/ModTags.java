package com.elvenwhiskers.moondrop.util;

import com.elvenwhiskers.moondrop.Moondrop;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> COLORER_TERRACOTTA = createTag("colorer_terracotta");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> COLORER_TERRACOTTA = createTag("colorer_terracotta");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Moondrop.MODID, name));
        }
    }
}
