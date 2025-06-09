package invmod.block;

import java.util.Random;

import invmod.ModBlocks;
import invmod.mod_invasion;
import invmod.tileentity.TileEntityNexus;
import invmod.util.config.Config;
import net.minecraft.world.level.level.block.Block;
import net.minecraft.world.level.level.material.Material;
import net.minecraft.world.level.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.level.block.state.BlockState;
import net.minecraft.world.level.level.block.state.StateDefinition;
import net.minecraft.world.level.entity.player.Player;
import net.minecraft.world.level.item.BlockItem;
import net.minecraft.world.level.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.InteractionHand;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.level.Level;
import net.minecraft.world.level.phys.BlockHitResult;
import net.minecraft.world.level.level.block.RenderShape;

public class BlockNexus extends Block {

	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public final String name = "blocknexus";
	public final BlockItem itemBlock;

	public BlockNexus(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, Boolean.valueOf(false)));
		this.itemBlock = new BlockItem(this, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ACTIVE);
	}

	@Override
	public BlockState getStateFromMeta(int meta) {
		return this.defaultBlockState().setValue(ACTIVE, Boolean.valueOf((meta & 1) > 0));
	}

	@Override
	public int getMetaFromState(BlockState state) {
		return state.getValue(ACTIVE).booleanValue() ? 1 : 0;
	}

	@Override
	public boolean onBlockActivated(Level worldIn, BlockPos pos, BlockState state, Player playerIn,
			InteractionHand hand, Direction facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isClientSide) {
			return true;
		}
		BlockEntity tileentity = worldIn.getBlockEntity(pos);
		if (tileentity instanceof TileEntityNexus) {
			playerIn.openMenu((TileEntityNexus) tileentity);
		}
		return true;
	}

	@Override
	public void randomDisplayTick(BlockState blockState, Level worldIn, BlockPos pos, Random rand) {
		if (blockState.getValue(ACTIVE).booleanValue()) {
			double d0 = (double) pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d1 = (double) pos.getY() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double) pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public BlockEntity createTileEntity(Level world, BlockState state) {
		return new TileEntityNexus();
	}

	public static void setBlockView(boolean active, Level worldIn, BlockPos blockPos) {
		BlockState iblockstate = worldIn.getBlockState(blockPos);
		if (iblockstate.getBlock() instanceof BlockNexus) {
			worldIn.setBlock(blockPos, iblockstate.setValue(ACTIVE, Boolean.valueOf(active)), 3);
		}
	}

	@Override
	public float getPlayerRelativeBlockHardness(BlockState state, Player player, Level world,
			BlockPos blockPos) {
		BlockEntity tileentity = world.getBlockEntity(blockPos);
		if (tileentity instanceof TileEntityNexus) {
			TileEntityNexus nexus = (TileEntityNexus) tileentity;
			if (nexus.isActive()) {
				return -1.0F;
			}
		}
		return super.getPlayerRelativeBlockHardness(state, player, world, blockPos);
	}

	@Override
	public RenderShape getRenderType(BlockState state) {
		return RenderShape.MODEL;
	}

}