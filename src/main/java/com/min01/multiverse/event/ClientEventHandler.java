package com.min01.multiverse.event;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.entity.model.ModelDeadman;
import com.min01.multiverse.entity.model.ModelOrochi;
import com.min01.multiverse.entity.model.ModelOrochiBody;
import com.min01.multiverse.entity.model.ModelOrochiHead;
import com.min01.multiverse.entity.model.ModelOrochiTail;
import com.min01.multiverse.entity.renderer.DeadmanRenderer;
import com.min01.multiverse.entity.renderer.NoneRenderer;
import com.min01.multiverse.entity.renderer.OrochiRenderer;
import com.min01.multiverse.obj.ObjModelManager;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MinsMultiverse.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(ObjModelManager.getInstance());
	}
	
	@SubscribeEvent
	public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event)
	{
		
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelOrochi.LAYER_LOCATION, ModelOrochi::createBodyLayer);
    	event.registerLayerDefinition(ModelOrochiTail.LAYER_LOCATION, ModelOrochiTail::createBodyLayer);
    	event.registerLayerDefinition(ModelOrochiBody.LAYER_LOCATION, ModelOrochiBody::createBodyLayer);
    	event.registerLayerDefinition(ModelOrochiHead.LAYER_LOCATION, ModelOrochiHead::createBodyLayer);
    	event.registerLayerDefinition(ModelDeadman.LAYER_LOCATION, ModelDeadman::createBodyLayer);
    }
    
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
    	event.registerEntityRenderer(MultiverseEntities.CAMERA_SHAKE.get(), NoneRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.OROCHI.get(), OrochiRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.DEADMAN.get(), DeadmanRenderer::new);
    }
}
