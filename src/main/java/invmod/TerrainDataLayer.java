package invmod;

import invmod.entity.ai.navigator.PathAction;
import invmod.entity.ai.navigator.PathNode;
import net.minecraft.world.level.level.block.state.BlockState;
import net.minecraft.world.level.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.IntHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.IBlockAccess;
import net.minecraft.world.level.WorldType;
import net.minecraft.world.level.biome.Biome;

public class TerrainDataLayer implements IBlockAccessExtended {
	public static final int EXT_DATA_SCAFFOLD_METAPOSITION = 16384;
	private IBlockAccess world;
	private IntHashMap dataLayer;

	public TerrainDataLayer(IBlockAccess world) {
		this.world = world;
		this.dataLayer = new IntHashMap();
	}

	@Override
	public void setData(double x, double y, double z, Integer data) {
		this.dataLayer.addKey(PathNode.makeHash(x, y, z, PathAction.NONE), data);
	}

	@Override
	public int getLayeredData(int x, int y, int z) {
		int key = PathNode.makeHash(x, y, z, PathAction.NONE);
		if (this.dataLayer.containsItem(key)) {
			return ((Integer) this.dataLayer.lookup(key)).intValue();
		}
		return 0;
	}

	public void setAllData(IntHashMap data) {
		this.dataLayer = data;
	}

	@Override
	public BlockState getBlockState(BlockPos blockPos) {
		return this.world.getBlockState(blockPos);
	}

	@Override
	public int getCombinedLight(BlockPos blockPos, int meta) {
		return this.world.getCombinedLight(blockPos, meta);
	}

	@Override
	public boolean isAirBlock(BlockPos blockPos) {
		return this.world.isAirBlock(blockPos);
	}

	@Override
	public Biome getBiome(BlockPos blockPos) {
		return this.world.getBiome(blockPos);
	}

	/*
	 * @Override public boolean extendedLevelsInChunkCache() { return
	 * this.world.extendedLevelsInChunkCache(); }
	 */

	@Override
	public BlockEntity getTileEntity(BlockPos blockPos) {
		return this.world.getTileEntity(blockPos);
	}

	@Override
	public boolean isSideSolid(BlockPos blockPos, Direction side, boolean _default) {
		// return this.world.getBlockState(blockPos).getBlock().getMaterial().isSolid();
		BlockState blockState = this.world.getBlockState(blockPos);
		return blockState.getBlock().getMaterial(blockState).isSolid(); // DarthXenon: Redundant, in my opinion.
	}

	@Override
	public int getStrongPower(BlockPos pos, Direction direction) {
		return this.world.getStrongPower(pos, direction);
	}

	@Override
	public WorldType getWorldType() {
		return this.world.getWorldType();
	}
}