package invmod.item;

import net.minecraft.world.level.entity.player.Player;
import net.minecraft.world.level.item.Item;
import net.minecraft.core.EnumActionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ItemRiftFlux extends Item {

	private final String name = "riftFlux";

	public ItemRiftFlux() {
		// this.setRegistryName(this.name);
		// GameRegistry.register(this);
		this.setMaxDamage(0);
		// this.setUnlocalizedName(Reference.MODID + "_" + this.name);
		this.setMaxStackSize(64);
		// this.setCreativeTab(mod_Invasion.tabInvmod);
	}

	/*
	 * @Override public EnumActionResult onItemUseFirst(ItemStack itemstack,
	 * Player entityplayer, Level world, BlockPos blockPos, Direction side,
	 * float hitX, float hitY, float hitZ, InteractionHand hand) { return
	 * EnumActionResult.FAIL; }
	 */
	@Override
	public EnumActionResult onItemUseFirst(Player player, Level world, BlockPos pos, Direction side, float hitX,
			float hitY, float hitZ, InteractionHand hand) {
		return EnumActionResult.FAIL;
	}

	public String getName() {
		return this.name;
	}
}