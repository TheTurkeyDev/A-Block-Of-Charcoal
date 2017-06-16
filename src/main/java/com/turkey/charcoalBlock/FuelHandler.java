package com.turkey.charcoalBlock;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(Item.getItemFromBlock(CharcoalBlockCore.theBlock) == fuel.getItem())
			return 16000;
		return 0;
	}	
	
}
