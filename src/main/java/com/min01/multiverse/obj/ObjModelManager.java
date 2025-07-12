package com.min01.multiverse.obj;

import java.util.concurrent.Executors;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.min01.multiverse.MinsMultiverse;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Created by Furia on 2016/02/06.
 */
@OnlyIn(Dist.CLIENT)
public class ObjModelManager 
{
    private static final class SingletonHolder 
    {
        private static final ObjModelManager INSTANCE = new ObjModelManager();
    }

    public static ObjModelManager getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    WavefrontObject defaultModel;
    LoadingCache<ResourceLocation, WavefrontObject> cache;
    public static final ResourceLocation DEFAULT_MODEL = new ResourceLocation(MinsMultiverse.MODID, "models/obj/sword1.obj");
    public static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(MinsMultiverse.MODID, "textures/entity/sword.png");

    private ObjModelManager()
    {
        this.defaultModel = new WavefrontObject(DEFAULT_MODEL);
        this.cache = CacheBuilder.newBuilder().build(CacheLoader.asyncReloading(new CacheLoader<ResourceLocation, WavefrontObject>() 
        {
        	@Override
        	public WavefrontObject load(ResourceLocation key) throws Exception 
        	{
        		try
        		{
        			return new WavefrontObject(key);
        		}
        		catch(Exception e)
        		{
        			return ObjModelManager.this.defaultModel;
        		}
        	}
        }, Executors.newCachedThreadPool()));
    }

    @SubscribeEvent
    public void reload(TextureStitchEvent.Post event)
    {
    	this.cache.invalidateAll();
        this.defaultModel = new WavefrontObject(DEFAULT_MODEL);
    }

    public WavefrontObject getModel(ResourceLocation loc) 
    {
        if(loc != null)
        {
            try 
            {
                return this.cache.get(loc);
            } 
            catch(Exception e) 
            {
                e.printStackTrace();
            }
        }
        return this.defaultModel;
    }
}
