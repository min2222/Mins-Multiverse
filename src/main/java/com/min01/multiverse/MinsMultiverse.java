package com.min01.multiverse;

import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.item.MultiverseItems;
import com.min01.multiverse.misc.MultiverseCreativeTabs;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MinsMultiverse.MODID)
public class MinsMultiverse
{
	public static final String MODID = "minsmultiverse";
	
	public MinsMultiverse() 
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		MultiverseEntities.ENTITY_TYPES.register(bus);
		MultiverseItems.ITEMS.register(bus);
		MultiverseCreativeTabs.CREATIVE_MODE_TAB.register(bus);
	}
}
