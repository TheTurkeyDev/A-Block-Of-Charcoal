package dev.theturkey.charcoalBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

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
		theBlock = new Block(BlockBehaviour.Properties.of(Material.DIRT).strength(5.0F, 6.0F));
		theBlock.setRegistryName(MODID, "charcoal_block");
		e.getRegistry().register(theBlock);
	}

	@SubscribeEvent
	public static void onItemRegistry(RegistryEvent.Register<Item> e)
	{
		Item theItemBlock = new BlockItem(theBlock, (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS))
		{
			@Override
			public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType)
			{
				return 14400;
			}
		};
		theItemBlock.setRegistryName(theBlock.getRegistryName());
		e.getRegistry().register(theItemBlock);
	}
}