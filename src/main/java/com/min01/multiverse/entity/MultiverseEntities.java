package com.min01.multiverse.entity;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityCreepeel;
import com.min01.multiverse.entity.living.EntityKingofSin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MultiverseEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinsMultiverse.MODID);
	
	public static final RegistryObject<EntityType<EntityCreepeel>> CREEPEEL = registerEntity("creepeel", createBuilder(EntityCreepeel::new, MobCategory.WATER_AMBIENT).sized(1.0F, 1.0F));
	public static final RegistryObject<EntityType<EntityKingofSin>> KING_OF_SIN = registerEntity("king_of_sin", createBuilder(EntityKingofSin::new, MobCategory.MONSTER).sized(1.375F, 3.5625F));
	
	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(MinsMultiverse.MODID, name).toString()));
	}
}
