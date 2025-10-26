package com.min01.multiverse.item;

import com.min01.multiverse.entity.MultiverseEntities;
import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityOrochi.ChainType;
import com.min01.multiverse.util.MultiverseUtil;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class CallOfOrochiItem extends Item
{
	public CallOfOrochiItem() 
	{
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) 
	{
		ItemStack stack = p_41433_.getItemInHand(p_41434_);
		p_41433_.startUsingItem(p_41434_);
		return InteractionResultHolder.pass(stack);
	}
	
	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int p_41415_) 
	{
		if(!entity.isPassenger() || !(entity.getVehicle() instanceof EntityOrochi))
		{
			Vec3 lookPos = MultiverseUtil.getLookPos(new Vec2(0, entity.getYHeadRot()), entity.position(), 0, 0, -20);
			Vec3 groundPos = MultiverseUtil.getGroundPos(level, lookPos.x, lookPos.y, lookPos.z, 2);	
			EntityOrochi orochi = new EntityOrochi(MultiverseEntities.OROCHI.get(), level);
			orochi.setPos(groundPos);
			orochi.setSnake(true);
			orochi.setChainType(ChainType.CARRY);
			orochi.setWantedPos(entity.position());
			orochi.setAnchorPos(groundPos);
			orochi.setOwner(entity);
			level.addFreshEntity(orochi);
		}
		if(entity.getVehicle() instanceof EntityOrochi orochi && orochi.isSnake() && orochi.getOwner() == entity)
		{
			entity.stopRiding();
			orochi.setWantedPos(orochi.position().subtract(0, 200, 0));
		}
		if(entity instanceof Player player)
		{
			player.getCooldowns().addCooldown(this, 20);
		}
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) 
	{
		return UseAnim.BOW;
	}
	
	@Override
	public int getUseDuration(ItemStack p_41454_)
	{
		return 72000;
	}
}
