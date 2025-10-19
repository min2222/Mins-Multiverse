package com.min01.multiverse.network;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityOrochi.OrochiChain;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

public class OrochiChainSyncPacket 
{
    private UUID entityUUID;

    public OrochiChainSyncPacket(UUID entityUUID) 
    {
        this.entityUUID = entityUUID;
    }

    public OrochiChainSyncPacket(FriendlyByteBuf buf)
    {
        this.entityUUID = buf.readUUID();
    }

    public void encode(FriendlyByteBuf buf) 
    {
        buf.writeUUID(this.entityUUID);
    }
    
    public static class Handler 
    {
        public static boolean onMessage(OrochiChainSyncPacket message, Supplier<NetworkEvent.Context> ctx) 
        {
            ctx.get().enqueueWork(() ->
            {
				List<OrochiChain> list = new ArrayList<>();
				for(ServerLevel level : ServerLifecycleHooks.getCurrentServer().getAllLevels())
				{
					Entity entity = level.getEntity(message.entityUUID);
					if(entity instanceof EntityOrochi orochi)
					{
						list.addAll(orochi.chains);
					}
				}
				MultiverseUtil.getClientLevel(t -> 
				{
					EntityOrochi orochi = MultiverseUtil.getEntityByUUID(t, message.entityUUID);
					orochi.chains.addAll(list);
				});
            });
            ctx.get().setPacketHandled(true);
			return true;
        }
    }
}