package com.elvenwhiskers.moondrop.item;

import com.elvenwhiskers.moondrop.Moondrop;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moondrop.MODID);

    public static final DeferredItem<Item> AEGIS_INGOT = ITEMS.registerSimpleItem("aegis_ingot");
    public static final DeferredItem<Item> RAW_AEGIS = ITEMS.registerSimpleItem("raw_aegis");


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
