package com.elvenwhiskers.moondrop.item;

import com.elvenwhiskers.moondrop.MoonDrop;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MoonDrop.MOD_ID);

    public static final RegistryObject<Item> AEGIS_INGOT = ITEMS.register("aegis_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOONDROP_TAB)));

    public static final RegistryObject<Item> AEGIS_NUGGET = ITEMS.register("aegis_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOONDROP_TAB)));

    public static final RegistryObject<Item> RAW_AEGIS = ITEMS.register("raw_aegis",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MOONDROP_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
