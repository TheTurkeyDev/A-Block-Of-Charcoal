package com.turkey.charcoalBlock;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = CharcoalBlockCore.MODID, version = CharcoalBlockCore.VERSION, name = CharcoalBlockCore.Name)
public class CharcoalBlockCore
{
	public static final String MODID = "charcoalBlock";
	public static final String Name = "A Block of Charcoal";
	public static final String VERSION = "1.0";

	public static Logger logger;

	public static Block theBlock;

	public static CreativeTabs baseModTab = new CreativeTabs(MODID)
	{
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(theBlock);
		}
	};

	@EventHandler
	public void load(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		GameRegistry.registerBlock(theBlock = new CharcoalBlock().setBlockTextureName("charcoalBlock:CharcoalBlock"), "Charcoal_Block");

		GameRegistry.registerFuelHandler(new FuelHandler());
		
		GameRegistry.addRecipe(new ItemStack(theBlock, 1), "AAA", "AAA", "AAA", 'A', new ItemStack(Items.coal, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal, 9, 1), new ItemStack(theBlock, 1));
	}
}