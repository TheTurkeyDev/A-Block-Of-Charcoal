package com.turkey.charcoalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(CharcoalBlockCore.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CharcoalBlockCore
{
	public static final String MODID = "charcoalblock";

	private static Block theBlock;

	public CharcoalBlockCore()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onBlockRegistry(RegistryEvent.Register<Block> e)
	{
		theBlock = new Block(Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5.0F, 6.0F));
		theBlock.setRegistryName(MODID, "charcoal_block");
		e.getRegistry().register(theBlock);
	}

	@SubscribeEvent
	public static void onItemRegistry(RegistryEvent.Register<Item> e)
	{
		Item theItemBlock = new BlockItem(theBlock, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS))
		{
			@Override
			public int getBurnTime(ItemStack itemStack)
			{
				return 14400;
			}
		};
		theItemBlock.setRegistryName(theBlock.getRegistryName());
		e.getRegistry().register(theItemBlock);
	}
}