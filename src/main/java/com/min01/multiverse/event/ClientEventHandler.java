package com.min01.multiverse.event;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.efkefc.EfkEfcLoader;
import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.entity.model.ModelCreepeel;
import com.min01.multiverse.entity.model.ModelHand;
import com.min01.multiverse.entity.model.ModelKingofSin;
import com.min01.multiverse.entity.model.ModelRakta;
import com.min01.multiverse.entity.renderer.CreepeelRenderer;
import com.min01.multiverse.entity.renderer.KingofSinRenderer;
import com.min01.multiverse.entity.renderer.NoneRenderer;
import com.min01.multiverse.entity.renderer.RaktaRenderer;
import com.min01.multiverse.entity.renderer.ScarletMagicRenderer;
import com.min01.multiverse.entity.renderer.SinRenderer;
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
		/*try
		{
		    AESUtil.encryptFiles(".png");
		}
		catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | NoSuchPaddingException | InvalidKeySpecException | IOException e) 
		{
		    e.printStackTrace();
		}*/
	}
	
	@SubscribeEvent
	public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event)
	{
		event.registerReloadListener(new EfkEfcLoader());
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelCreepeel.LAYER_LOCATION, ModelCreepeel::createBodyLayer);
    	event.registerLayerDefinition(ModelKingofSin.LAYER_LOCATION, ModelKingofSin::createBodyLayer);
    	event.registerLayerDefinition(ModelRakta.LAYER_LOCATION, ModelRakta::createBodyLayer);
    	event.registerLayerDefinition(ModelHand.LAYER_LOCATION, ModelHand::createBodyLayer);
    }
    
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
    	event.registerEntityRenderer(MultiverseEntities.CREEPEEL.get(), CreepeelRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.KING_OF_SIN.get(), KingofSinRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.RAKTA.get(), RaktaRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.SIN.get(), SinRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.SCARLET_MAGIC.get(), ScarletMagicRenderer::new);
    	event.registerEntityRenderer(MultiverseEntities.CAMERA_SHAKE.get(), NoneRenderer::new);
    }
}
