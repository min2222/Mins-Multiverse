package com.min01.multiverse.entity;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.living.EntityCreepeel;
import com.min01.multiverse.entity.living.EntityKingofSin;
import com.min01.multiverse.entity.living.EntityOrochi;
import com.min01.multiverse.entity.living.EntityRakta;
import com.min01.multiverse.entity.projectile.EntityScarletMagic;
import com.min01.multiverse.entity.projectile.EntitySin;

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
	public static final RegistryObject<EntityType<EntityRakta>> RAKTA = registerEntity("rakta", createBuilder(EntityRakta::new, MobCategory.MONSTER).sized(1.125F, 3.75F));
	public static final RegistryObject<EntityType<EntityOrochi>> OROCHI = registerEntity("orochi", createBuilder(EntityOrochi::new, MobCategory.MONSTER).sized(0.6F, 1.8F));
	
	public static final RegistryObject<EntityType<EntitySin>> SIN = registerEntity("sin", EntityType.Builder.<EntitySin>of(EntitySin::new, MobCategory.MISC).sized(1.0F, 1.0F));
	public static final RegistryObject<EntityType<EntityScarletMagic>> SCARLET_MAGIC = registerEntity("scarlet_magic", EntityType.Builder.<EntityScarletMagic>of(EntityScarletMagic::new, MobCategory.MISC).sized(1.0F, 1.0F));
	public static final RegistryObject<EntityType<Entity>> CAMERA_SHAKE = registerEntity("camera_shake", createBuilder(EntityCameraShake::new, MobCategory.MISC).sized(0.0F, 0.0F));
	
	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(MinsMultiverse.MODID, name).toString()));
	}
}
