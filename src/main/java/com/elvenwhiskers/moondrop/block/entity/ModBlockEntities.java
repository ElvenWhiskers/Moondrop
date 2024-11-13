package com.elvenwhiskers.moondrop.block.entity;

import com.elvenwhiskers.moondrop.Moondrop;
import com.elvenwhiskers.moondrop.block.ModBlocks;
import com.elvenwhiskers.moondrop.block.entity.custom.MDCauldronBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Moondrop.MODID);


    public static final Supplier<BlockEntityType<MDCauldronBlockEntity>> MDCAULDRON_BE =
            BLOCK_ENTITIES.register("mdcauldron_be", () -> BlockEntityType.Builder.of(
                    MDCauldronBlockEntity::new, ModBlocks.MOONDROP_CAULDRON.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
