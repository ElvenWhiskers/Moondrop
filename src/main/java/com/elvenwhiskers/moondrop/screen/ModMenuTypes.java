package com.elvenwhiskers.moondrop.screen;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.screen.custom.ColorerMenu;
import com.elvenwhiskers.moondrop.screen.custom.PrismaDyerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Moondrop.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ColorerMenu>> COLORER_MENU =
            registerMenuType("colorer_menu", ColorerMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<PrismaDyerMenu>> PRISMA_DYER_MENU =
            registerMenuType("prisma_dyer_menu", PrismaDyerMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
