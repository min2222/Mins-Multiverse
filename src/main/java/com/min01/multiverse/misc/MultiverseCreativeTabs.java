package com.min01.multiverse.misc;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.item.MultiverseItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MultiverseCreativeTabs 
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinsMultiverse.MODID);

    public static final RegistryObject<CreativeModeTab> MINS_MULTIVERSE = CREATIVE_MODE_TAB.register("minsmultiverse", () -> CreativeModeTab.builder()
    		.title(Component.translatable("itemGroup.minsmultiverse"))
    		.icon(() -> new ItemStack(MultiverseItems.ORB_OF_OROCHI.get()))
    		.displayItems((enabledFeatures, output) -> 
    		{
    			for(RegistryObject<Item> items : MultiverseItems.ITEMS.getEntries())
    			{
    				output.accept(items.get());
    			}
    		}).build());
}
