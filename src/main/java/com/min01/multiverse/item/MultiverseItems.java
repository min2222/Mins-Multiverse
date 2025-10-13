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
	
	public static final RegistryObject<Item> CUPIDITAS = ITEMS.register("cupiditas", () -> new SevenSinsSwordItem());
	public static final RegistryObject<Item> DESIDIA = ITEMS.register("desidia", () -> new SevenSinsSwordItem());
	public static final RegistryObject<Item> GULA = ITEMS.register("gula", () -> new SevenSinsSwordItem());
	public static final RegistryObject<Item> INVIDIA = ITEMS.register("invidia", () -> new SevenSinsSwordItem());
	public static final RegistryObject<Item> IRAM = ITEMS.register("iram", () -> new SevenSinsSwordItem());
	public static final RegistryObject<Item> LUXURIA = ITEMS.register("luxuria", () -> new SevenSinsSwordItem());
	public static final RegistryObject<Item> SUPERBIA = ITEMS.register("superbia", () -> new SevenSinsSwordItem());
	
	public static final RegistryObject<Item> CREEPEEL_SPAWN_EGG = registerSpawnEgg("creepeel_spawn_egg", () -> MultiverseEntities.CREEPEEL.get(), 894731, 0);
	public static final RegistryObject<Item> OROCHI_SPAWN_EGG = registerSpawnEgg("orochi_spawn_egg", () -> MultiverseEntities.OROCHI.get(), 16777215, 13042435);
	//public static final RegistryObject<Item> KING_OF_SIN_SPAWN_EGG = registerSpawnEgg("king_of_sin_spawn_egg", () -> MultiverseEntities.KING_OF_SIN.get(), 0, 0);
	//public static final RegistryObject<Item> RAKTA_SPAWN_EGG = registerSpawnEgg("rakta_spawn_egg", () -> MultiverseEntities.RAKTA.get(), 0, 0);
	
	public static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties)
	{
		return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
	}
	
	public static <T extends Mob> RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<T>> entity, int color1, int color2) 
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties()));
	}
}
