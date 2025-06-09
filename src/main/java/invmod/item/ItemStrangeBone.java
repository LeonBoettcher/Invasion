package invmod.item;

import invmod.ModBlocks;
import invmod.entity.ally.EntityIMWolf;
import invmod.tileentity.TileEntityNexus;
import net.minecraft.world.level.entity.LivingEntity;
import net.minecraft.world.level.entity.passive.EntityWolf;
import net.minecraft.world.level.entity.player.Player;
import net.minecraft.world.level.item.ItemStack;
import net.minecraft.core.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.core.MathHelper;
import net.minecraft.core.text.TextComponentTranslation;

public class ItemStrangeBone extends ModItem {

	public ItemStrangeBone() {
		super("strangeBone");
		this.setMaxStackSize(1);
	}

	@Override
	public int getDamage(ItemStack stack) {
		return 0;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemStack, Player player, LivingEntity targetEntity,
			InteractionHand hand) {
		if ((!targetEntity.world.isRemote) && ((targetEntity instanceof EntityWolf))
				&& (!(targetEntity instanceof EntityIMWolf))) {
			EntityWolf wolf = (EntityWolf) targetEntity;

			if (wolf.isTamed()) {
				TileEntityNexus nexus = null;
				int x = MathHelper.floor(wolf.posX);
				int y = MathHelper.floor(wolf.posY);
				int z = MathHelper.floor(wolf.posZ);

				for (int i = -7; i < 8; i++) {
					for (int j = -4; j < 5; j++) {
						for (int k = -7; k < 8; k++) {
							BlockPos newBlockPos = new BlockPos(x, y, z).add(i, j, k);
							if (wolf.world.getBlockState(newBlockPos)
									.getBlock() == /* BlocksAndItems.blockNexus */ModBlocks.NEXUS_BLOCK) {
								nexus = (TileEntityNexus) wolf.world.getTileEntity(newBlockPos);
								break;
							}
						}
					}
				}

				if (nexus != null) {
					EntityIMWolf newWolf = new EntityIMWolf(wolf, nexus);
					wolf.world.spawnEntity(newWolf);
					wolf.setDead();
					itemStack.shrink(1);
				} else {
					player.sendMessage(new TextComponentTranslation("The wolf doesn't like this strange bone."));
				}
			}

			return true;
		}

		return false;
	}

}