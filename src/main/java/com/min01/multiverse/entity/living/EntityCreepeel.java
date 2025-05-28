package com.min01.multiverse.entity.living;

import com.min01.multiverse.entity.AbstractAnimatableWaterAnimal;
import com.min01.multiverse.misc.WormChain;
import com.min01.multiverse.misc.WormChain.Worm;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityCreepeel extends AbstractAnimatableWaterAnimal
{
	public final Worm wormFrontHuge = new Worm();
	public final Worm wormBackHuge = new Worm();
	
	public final Worm wormFrontLarge = new Worm();
	public final Worm wormBackLarge = new Worm();
	
	public final Worm wormFrontMedium = new Worm();
	public final Worm wormBackMedium = new Worm();
	
	public final Worm wormFrontSmall = new Worm();
	public final Worm wormBackSmall = new Worm();
	
	public EntityCreepeel(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
		this.xpReward = 50;
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 60.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F)
        		.add(Attributes.ATTACK_DAMAGE, 12.0F);
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
    	
    	this.wormFrontHuge.setOldPosAndRot();
    	this.wormBackHuge.setOldPosAndRot();
    	this.wormFrontLarge.setOldPosAndRot();
    	this.wormBackLarge.setOldPosAndRot();
    	this.wormFrontMedium.setOldPosAndRot();
    	this.wormBackMedium.setOldPosAndRot();
    	this.wormFrontSmall.setOldPosAndRot();
    	this.wormBackSmall.setOldPosAndRot();
    	
		float speed = 0.35F;
		
		WormChain.tick(this.wormFrontHuge, this, 0.95F, speed);
		WormChain.tick(this.wormBackHuge, this.wormFrontHuge, 0.88F, speed);
		
		WormChain.tick(this.wormFrontLarge, this.wormBackHuge, 0.8F, speed);
		WormChain.tick(this.wormBackLarge, this.wormFrontLarge, 0.77F, speed);
		
		WormChain.tick(this.wormFrontMedium, this.wormBackLarge, 0.7F, speed);
		WormChain.tick(this.wormBackMedium, this.wormFrontMedium, 0.6F, speed);
		
		WormChain.tick(this.wormFrontSmall, this.wormBackMedium, 0.5F, speed);
		WormChain.tick(this.wormBackSmall, this.wormFrontSmall, 0.5F, speed);
    }
}
