package invmod.util.spawneggs;

import net.minecraft.world.level.level.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.world.level.entity.Entity;
import net.minecraft.world.level.entity.LivingEntity;
import net.minecraft.world.level.item.ItemStack;
import net.minecraft.core.BlockPos;

public class DispenserBehaviorSpawnEgg extends BehaviorDefaultDispenseItem {

	@Override
	public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
		// Direction enumfacing =
		// BlockDispenser.getFacing(blockSource.getBlockMetadata());
		IPosition direction = BlockDispenser.getDispensePosition(blockSource);
		double x = blockSource.getX() + direction.getX(); // enumfacing.getFrontOffsetX();
		double y = (float) blockSource.getY() + 0.2F;
		double z = blockSource.getZ() + direction.getZ(); // enumfacing.getFrontOffsetZ();
		Entity entity = ItemSpawnEgg.spawnCreature(blockSource.getWorld(), stack, new BlockPos(x, y, z));
		if (entity instanceof LivingEntity && stack.hasDisplayName())
			((LivingEntity) entity).setCustomNameTag(stack.getDisplayName());

		stack.splitStack(1);

		return stack;
	}
}
