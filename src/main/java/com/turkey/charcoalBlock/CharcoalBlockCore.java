package com.turkey.charcoalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(CharcoalBlockCore.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CharcoalBlockCore
{
	public static final String MODID = "charcoalblock";

	public static Block theBlock;
	public static Item theItemBlock;

	public CharcoalBlockCore()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void fuelEvent(FurnaceFuelBurnTimeEvent event)
	{
		if(event.getItemStack().getItem().equals(theItemBlock))
			event.setBurnTime(16000);
	}

	@SubscribeEvent
	public void onBlockRegistry(RegistryEvent.Register<Block> e)
	{
		theBlock = new Block(Block.Properties.create(Material.GROUND).hardnessAndResistance(5));
		theBlock.setRegistryName(MODID, "charcoal_block");
		e.getRegistry().register(theBlock);
	}

	@SubscribeEvent
	public void onItemRegistry(RegistryEvent.Register<Item> e)
	{
		theItemBlock = new ItemBlock(theBlock, (new Item.Properties()).group(ItemGroup.MATERIALS));
		theItemBlock.setRegistryName(theBlock.getRegistryName());
		e.getRegistry().register(theItemBlock);
	}
}