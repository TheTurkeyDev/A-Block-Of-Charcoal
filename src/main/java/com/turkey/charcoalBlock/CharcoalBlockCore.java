package com.turkey.charcoalBlock;

import org.apache.logging.log4j.Logger;

import com.google.gson.JsonElement;
import com.theprogrammingturkey.gobblecore.IModCore;
import com.theprogrammingturkey.gobblecore.blocks.BaseBlock;
import com.theprogrammingturkey.gobblecore.blocks.BlockLoader;
import com.theprogrammingturkey.gobblecore.blocks.BlockManager;
import com.theprogrammingturkey.gobblecore.blocks.IBlockHandler;
import com.theprogrammingturkey.gobblecore.managers.CraftingManager;
import com.theprogrammingturkey.gobblecore.managers.WebHookManager;
import com.theprogrammingturkey.gobblecore.managers.WebHookManager.ModWebHook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CharcoalBlockCore.MODID, version = CharcoalBlockCore.VERSION, name = CharcoalBlockCore.Name, dependencies = "required-after:gobblecore")
public class CharcoalBlockCore implements IModCore
{
	public static final String MODID = "charcoalblock";
	public static final String Name = "A Block of Charcoal";
	public static final String VERSION = "1.0";

	public static Logger logger;

	public static BaseBlock theBlock;

	public static CreativeTabs baseModTab = new CreativeTabs(MODID)
	{
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(theBlock);
		}
	};

	public CharcoalBlockCore()
	{
		BlockManager.registerBlockHandler(new IBlockHandler()
		{
			@Override
			public void registerBlocks(BlockLoader loader)
			{
				loader.setCreativeTab(baseModTab);
				loader.registerBlock(theBlock = new BaseBlock("charcoal_block", 5), theBlock.getBlockName());
			}

			@Override
			public void registerModels(BlockLoader loader)
			{
				ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

				loader.registerBlockModel(mesher, theBlock, 0, theBlock.getBlockName());
			}
		}, this);
	}

	@EventHandler
	public void load(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		
		WebHookManager.registerHook(new ModWebHook(this)
		{
			@Override
			public void onResponse(JsonElement json)
			{
			}
		});
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

		CraftingManager.register3x3CompressionRecipe(new ItemStack(Items.COAL, 1, 1), new ItemStack(theBlock, 1), true);
	}

	@Override
	public String getModID()
	{
		return MODID;
	}

	@Override
	public String getName()
	{
		return Name;
	}

	@Override
	public String getVersion()
	{
		return VERSION;
	}
}