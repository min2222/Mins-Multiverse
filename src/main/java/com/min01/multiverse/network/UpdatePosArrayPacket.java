package com.min01.multiverse.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.multiverse.entity.IPosArray;
import com.min01.multiverse.misc.MultiverseEntityDataSerializers;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

public class UpdatePosArrayPacket 
{
	private final UUID entityUUID;
	private final int array;
	private final Vec3 pos;

	public UpdatePosArrayPacket(Entity entity, Vec3 pos, int array) 
	{
		this.entityUUID = entity.getUUID();
		this.pos = pos;
		this.array = array;
	}

	public UpdatePosArrayPacket(FriendlyByteBuf buf)
	{
		this.entityUUID = buf.readUUID();
		this.pos = MultiverseEntityDataSerializers.readVec3(buf);
		this.array = buf.readInt();
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.entityUUID);
		MultiverseEntityDataSerializers.writeVec3(buf, this.pos);
		buf.writeInt(this.array);
	}

	public static class Handler 
	{
		public static boolean onMessage(UpdatePosArrayPacket message, Supplier<NetworkEvent.Context> ctx)
		{
			ctx.get().enqueueWork(() ->
			{
				if(ctx.get().getDirection().getReceptionSide().isServer())
				{
					Entity entity = MultiverseUtil.getEntityByUUID(ctx.get().getSender().level, message.entityUUID);
					if(entity instanceof IPosArray mob) 
					{
						mob.getPosArray()[message.array] = message.pos;
					}
				}
				else
				{
					MultiverseUtil.getClientLevel(level -> 
					{
						Entity entity = MultiverseUtil.getEntityByUUID(level, message.entityUUID);
						if(entity instanceof IPosArray mob) 
						{
							mob.getPosArray()[message.array] = message.pos;
						}
					});
				}
			});
			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}
