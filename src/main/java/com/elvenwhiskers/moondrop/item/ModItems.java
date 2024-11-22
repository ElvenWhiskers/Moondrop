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

    public static final DeferredItem<Item> KAOLIN = ITEMS.registerSimpleItem("kaolin");

    public static final DeferredItem<Item> PASTEL_RED_DYE = ITEMS.registerSimpleItem("pastel_red_dye");
    public static final DeferredItem<Item> PASTEL_ORANGE_DYE = ITEMS.registerSimpleItem("pastel_orange_dye");
    public static final DeferredItem<Item> PASTEL_YELLOW_DYE = ITEMS.registerSimpleItem("pastel_yellow_dye");
    public static final DeferredItem<Item> PASTEL_LIME_DYE = ITEMS.registerSimpleItem("pastel_lime_dye");
    public static final DeferredItem<Item> PASTEL_GREEN_DYE = ITEMS.registerSimpleItem("pastel_green_dye");
    public static final DeferredItem<Item> PASTEL_CYAN_DYE = ITEMS.registerSimpleItem("pastel_cyan_dye");
    public static final DeferredItem<Item> PASTEL_BABY_BLUE_DYE = ITEMS.registerSimpleItem("pastel_baby_blue_dye");
    public static final DeferredItem<Item> PASTEL_PURPLE_DYE = ITEMS.registerSimpleItem("pastel_purple_dye");
    public static final DeferredItem<Item> PASTEL_MAGENTA_DYE = ITEMS.registerSimpleItem("pastel_magenta_dye");
    public static final DeferredItem<Item> PASTEL_PINK_DYE = ITEMS.registerSimpleItem("pastel_pink_dye");


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
