package invmod.item.trap;

import invmod.entity.block.trap.EntityIMTrap;
import invmod.item.ModItem;
import net.minecraft.world.level.entity.player.Player;
import net.minecraft.world.level.item.ItemStack;
import net.minecraft.core.EnumActionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ItemPoisonTrap extends ModItem {

	public ItemPoisonTrap() {
		super("poisonTrap");
		this.setMaxStackSize(64);
	}

	// @Override
	// public EnumActionResult onItemUseFirst(ItemStack itemstack, Player
	// entityplayer, Level world, BlockPos blockPos,
	// Direction side, float hitX, float hitY, float hitZ, InteractionHand hand)
	// {
	@Override
	public EnumActionResult onItemUseFirst(Player player, Level world, BlockPos pos, Direction side, float hitX,
			float hitY, float hitZ, InteractionHand hand) {
		if (world.isRemote)
			return EnumActionResult.FAIL;
		if (side == Direction.UP) {
			EntityIMTrap trap = new EntityIMTrap(world, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, 3);

			if ((trap.isValidPlacement())
					&& (world.getEntitiesWithinAABB(EntityIMTrap.class, trap.getEntityBoundingBox()).size() == 0)) {
				world.spawnEntity(trap);

				// players in creative mode won't lose the item
				if (!player.capabilities.isCreativeMode) {
					ItemStack item = player.getHeldItem(hand);
					// itemstack.stackSize -= 1;
					item.shrink(1);
				}
			}
			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.FAIL;
	}

}