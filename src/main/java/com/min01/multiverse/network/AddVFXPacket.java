package com.min01.multiverse.network;

import java.util.UUID;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import com.min01.multiverse.event.ClientEventHandlerForge;
import com.min01.multiverse.vfx.Effect;
import com.min01.multiverse.vfx.EffectSlash;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class AddVFXPacket 
{
	public final Effect effect;
	public final UUID uuid;

	public AddVFXPacket(Effect effect, UUID uuid) 
	{
		this.effect = effect;
		this.uuid = uuid;
	}

	public AddVFXPacket(FriendlyByteBuf buf)
	{
		EffectSlash effect = new EffectSlash(0);
		effect.read(buf.readNbt());
		this.effect = effect;
		this.uuid = buf.readUUID();
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeNbt(this.effect.write());
		buf.writeUUID(this.uuid);
	}

	public static class Handler 
	{
		public static boolean onMessage(AddVFXPacket message, Supplier<NetworkEvent.Context> ctx)
		{
			ctx.get().enqueueWork(() ->
			{
				if(ctx.get().getDirection().getReceptionSide().isClient()) 
				{
					ClientEventHandlerForge.EFFECTS.add(Pair.of(message.effect, message.uuid));
				}
			});
			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}
