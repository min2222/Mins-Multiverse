package com.min01.multiverse.event;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.entity.model.ModelCreepeel;
import com.min01.multiverse.entity.model.ModelKingofSin;
import com.min01.multiverse.entity.renderer.CreepeelRenderer;
import com.min01.multiverse.entity.renderer.KingofSinRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MinsMultiverse.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelCreepeel.LAYER_LOCATION, ModelCreepeel::createBodyLayer);
    	event.registerLayerDefinition(ModelKingofSin.LAYER_LOCATION, ModelKingofSin::createBodyLayer);
    }
    
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
    	event.registerEntityRenderer(MultiverseEntities.CREEPEEL.get(), CreepeelRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.KING_OF_SIN.get(), KingofSinRenderer::new);
    }
}
