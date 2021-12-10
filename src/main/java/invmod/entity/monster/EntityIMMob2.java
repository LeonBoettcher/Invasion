package invmod.entity.monster;

import invmod.tileentity.TileEntityNexus;
import net.minecraft.entity.EntityCreature;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityIMMob2 extends EntityCreature {

	private static final DataParameter<Integer> TIER = EntityDataManager.createKey(EntityIMMob2.class,
			DataSerializers.VARINT);
	private static final DataParameter<Integer> TEXTURE = EntityDataManager.createKey(EntityIMMob2.class,
			DataSerializers.VARINT);

	private TileEntityNexus nexus;

	public EntityIMMob2(World worldIn) {
		super(worldIn);
	}

	public EntityIMMob2(World worldIn, TileEntityNexus nexus) {
		super(worldIn);
		this.nexus = nexus;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(TIER, 1);
		this.getDataManager().register(TEXTURE, 0);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();

	}

	public TileEntityNexus getNexus() {
		return this.nexus;
	}

	public void setTier(int tier) {
		this.getDataManager().set(TIER, tier);
	}

	public int getTier() {
		return this.getDataManager().get(TIER);
	}

	public int getTextureID() {
		return this.getDataManager().get(TEXTURE);
	}

}
