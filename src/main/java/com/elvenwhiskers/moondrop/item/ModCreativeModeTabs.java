package com.elvenwhiskers.moondrop.item;


import com.elvenwhiskers.moondrop.Moondrop;
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
                    })).build());




    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
