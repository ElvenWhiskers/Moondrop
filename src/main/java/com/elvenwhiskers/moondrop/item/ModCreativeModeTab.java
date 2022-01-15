package com.elvenwhiskers.moondrop.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MOONDROP_TAB =  new CreativeModeTab("moondroptab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.AEGIS_INGOT.get());
        }
    };
}
