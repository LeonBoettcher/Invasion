package invmod.item;

import net.minecraft.world.level.level.block.state.BlockState;
import net.minecraft.world.level.entity.LivingEntity;
import net.minecraft.world.level.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundEvents;
import net.minecraft.world.level.item.EnumAction;
import net.minecraft.world.level.item.ItemStack;
import net.minecraft.world.level.item.ItemSword;
import net.minecraft.core.ActionResult;
import net.minecraft.core.EnumActionResult;
import net.minecraft.core.InteractionHand;
import net.minecraft.core.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ItemInfusedSword extends ItemSword {

	public final String name = "infusedSword";
	// <3 minecraftforum ;)

	public ItemInfusedSword() {
		super(ToolMaterial.DIAMOND);
		// this.setRegistryName(this.name);
		// GameRegistry.register(this);
		// amount of entity hits it takes to recharge sword.
		this.setMaxDamage(40);
		// this.setUnlocalizedName(this.name);
		// this.setCreativeTab(mod_Invasion.tabInvmod);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, LivingEntity entityliving, LivingEntity entityliving1) {
		if (this.isDamaged(itemstack)) {
			this.setDamage(itemstack, this.getDamage(itemstack) - 1);

		}
		return true;
	}

	// Iirc this is already handled by the sword base class itself
	/*
	 * @Override public float getStrVsBlock(ItemStack par1ItemStack, BlockState
	 * par2Block) { if (par2Block == Blocks.WEB) { return 15.0F; }
	 * 
	 * Material material = par2Block.getMaterial(); return (material !=
	 * Material.PLANTS) && (material != Material.VINE) && (material !=
	 * Material.CORAL) && (material != Material.LEAVES) && (material !=
	 * Material.SPONGE) && (material != Material.CACTUS) ? 1.0F : 1.5F; }
	 */

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 0;
	}

	/*
	 * @Override public ActionResult<ItemStack> onItemRightClick(ItemStack
	 * itemstack, Level world, Player entityplayer, InteractionHand hand) {
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(Level wolrdIn, Player player, InteractionHand handIn) {
		ItemStack itemstack = player.getHeldItem(handIn);
		if (itemstack.getItemDamage() == 0) {
			// if player isSneaking then refill hunger else refill health
			if (player.isSneaking()) {
				player.getFoodStats().addStats(6, 0.5f);
				// world.playSoundAtEntity(entityplayer, "random.burp", 0.5F,
				// world.rand.nextFloat() * 0.1F + 0.9F);
				player.playSound(SoundEvents.ENTITY_PLAYER_BURP, 0.5f, wolrdIn.rand.nextFloat() * 0.1f + 0.9f);
			} else {
				player.heal(6.0F);
				// spawn heart particles around the player
				wolrdIn.spawnParticle(ParticleTypes.HEART, player.posX + 1.5D, player.posY, player.posZ, 0.0D, 0.0D,
						0.0D);
				wolrdIn.spawnParticle(ParticleTypes.HEART, player.posX - 1.5D, player.posY, player.posZ, 0.0D, 0.0D,
						0.0D);
				wolrdIn.spawnParticle(ParticleTypes.HEART, player.posX, player.posY, player.posZ + 1.5D, 0.0D, 0.0D,
						0.0D);
				wolrdIn.spawnParticle(ParticleTypes.HEART, player.posX, player.posY, player.posZ - 1.5D, 0.0D, 0.0D,
						0.0D);
			}

			itemstack.setItemDamage(this.getMaxDamage());
		}

		return new ActionResult<>(EnumActionResult.PASS, itemstack);
	}

	@Override
	public boolean canHarvestBlock(BlockState state) {
		return state.getBlock() == Blocks.WEB;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, Level worldIn, BlockState state, BlockPos pos,
			LivingEntity playerIn) {
		return true;
	}

}