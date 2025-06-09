package invmod;

import static invmod.util.InjectionUtil.Null;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import invmod.block.BlockNexus;
import net.minecraft.world.level.level.block.Block;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.item.Item;
import net.minecraft.world.level.item.BlockItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MODID)
public class ModBlocks {

	@ObjectHolder("blocknexus")
	public static final BlockNexus NEXUS_BLOCK = Null();

	@EventBusSubscriber(modid = Reference.MODID)
	public static class BlockRegistrationHandler {

		public static final List<BlockItem> ITEM_BLOCKS = new ArrayList<BlockItem>();

		@SubscribeEvent(priority = EventPriority.HIGH)
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final Block[] blocks = { setBlockName(new BlockNexus(), "blocknexus") };

			IForgeRegistry<Block> registry = event.getRegistry();

			for (Block block : blocks) {
				registry.register(block);
			}
		}

		private static Block setBlockName(Block block, String name) {
			return setBlockNameAndTab(block, name, mod_invasion.tabInvmod);
		}

		private static Block setBlockNameAndTab(Block block, String name, @Nullable CreativeModeTab tab) {
			Block b = block.setTranslationKey(name).setRegistryName(Reference.MODID, name).setCreativeTab(tab);
			return b;
		}

		@SubscribeEvent
		public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
			final BlockItem[] itemBlocks = { createItemBlock(NEXUS_BLOCK) };

			IForgeRegistry<Item> registry = event.getRegistry();

			for (BlockItem itemBlock : itemBlocks) {
				registry.register(itemBlock);
				ITEM_BLOCKS.add(itemBlock);
			}
		}

		private static BlockItem createItemBlock(Block block) {
			// return (BlockItem) new
			// BlockItem(block).setUnlocalizedName(block.getLocalizedName())
			// .setRegistryName(block.getRegistryName());
			BlockItem retItem = new BlockItem(block);

			/*
			 * if(block == null) { System.out.println("BLOCK IS NULL"); }
			 */

			// Cause of error: The block is null ?!?!?

			retItem.setTranslationKey(block.getTranslationKey());
			retItem.setRegistryName(block.getRegistryName());
			retItem.setCreativeTab(mod_invasion.tabInvmod);

			return retItem;
		}

	}

}
