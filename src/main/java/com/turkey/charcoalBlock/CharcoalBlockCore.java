package com.turkey.charcoalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = CharcoalBlockCore.MODID, version = CharcoalBlockCore.VERSION, name = CharcoalBlockCore.NAME)
public class CharcoalBlockCore
{
	public static final String MODID = "charcoalblock";
	public static final String NAME = "A Block of Charcoal";
	public static final String VERSION = "1.1";

	public static Block theBlock;

	@EventHandler
	public void load(FMLPreInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT)
		{
			ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

			mesher.register(Item.getItemFromBlock(theBlock), 0, new ModelResourceLocation(MODID + ":charcoal_block", "inventory"));
		}
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		GameRegistry.registerFuelHandler(new IFuelHandler()
		{
			@Override
			public int getBurnTime(ItemStack fuel)
			{
				if(Item.getItemFromBlock(CharcoalBlockCore.theBlock) == fuel.getItem())
					return 16000;
				return 0;
			}
		});

		ResourceLocation group = new ResourceLocation(NAME);
		GameRegistry.addShapedRecipe(new ResourceLocation(MODID, "Charcol Block Compression"), group, new ItemStack(theBlock, 1), "III", "III", "III", 'I', new ItemStack(Items.COAL, 1, 1));
		GameRegistry.addShapelessRecipe(new ResourceLocation(MODID, "Charcol Block Uncompression"), group, new ItemStack(Items.COAL, 9, 1), Ingredient.func_193369_a(new ItemStack(theBlock, 1)));
	}

	@SubscribeEvent
	public void onBlockRegistry(RegistryEvent.Register<Block> e)
	{
		theBlock = new Block(Material.GROUND);
		theBlock.setCreativeTab(CreativeTabs.MATERIALS);
		theBlock.setHardness(5);
		theBlock.setUnlocalizedName("charcoal_block");
		e.getRegistry().register(theBlock);
	}

	@SubscribeEvent
	public void onItemRegistry(RegistryEvent.Register<Item> e)
	{
		ItemBlock ib = new ItemBlock(theBlock);
		ib.setRegistryName(theBlock.getRegistryName());
		e.getRegistry().register(ib);
	}
}