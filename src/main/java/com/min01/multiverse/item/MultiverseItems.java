package com.min01.multiverse.item;

import java.util.function.Supplier;

import com.min01.multiverse.MinsMultiverse;
import com.min01.multiverse.entity.MultiverseEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MultiverseItems 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MinsMultiverse.MODID);
	
	public static final RegistryObject<Item> CREEPEEL_SPAWN_EGG = registerSpawnEgg("creepeel_spawn_egg", () -> MultiverseEntities.CREEPEEL.get(), 894731, 0);
	
	public static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties)
	{
		return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
	}
	
	public static <T extends Mob> RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<T>> entity, int color1, int color2) 
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties()));
	}
}
