package com.min01.multiverse.event;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.entity.living.EntityCreepeel;
import com.min01.multiverse.entity.living.EntityRakta;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MinsMultiverse.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler
{
	@SubscribeEvent
	public static void onFMLCommonSetup(FMLCommonSetupEvent event)
	{
		
	}
	
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) 
    {
    	event.put(MultiverseEntities.CREEPEEL.get(), EntityCreepeel.createAttributes().build());
    	event.put(MultiverseEntities.RAKTA.get(), EntityRakta.createAttributes().build());
    }
}
