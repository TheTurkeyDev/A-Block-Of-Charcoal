package com.turkey.charcoalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CharcoalBlock extends Block
{
	public CharcoalBlock() 
	{
		super(Material.ground);
		setHardness(5);
		setStepSound(Block.soundTypeStone);
		setBlockName("Charcoal_Block");
		setCreativeTab(CharcoalBlockCore.baseModTab);
	}
}
