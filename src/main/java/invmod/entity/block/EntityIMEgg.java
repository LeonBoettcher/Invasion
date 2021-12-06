package invmod.entity.block;

import invmod.SoundHandler;
import invmod.mod_invasion;
import invmod.entity.monster.EntityIMMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;


public class EntityIMEgg extends EntityIMMob
{
	//private static int META_HATCHED = 30;
	private static final DataParameter<Byte> META_HATCHED = EntityDataManager.<Byte>createKey(EntityIMEgg.class, DataSerializers.BYTE);
	private int hatchTime;
	private int ticks;
	private boolean hatched;
	private Entity parent;
	private Entity[] contents;

	public EntityIMEgg(World world)
	{
		super(world);
		this.getDataManager().register(META_HATCHED, Byte.valueOf((byte)0));
	}

	public EntityIMEgg(Entity parent, Entity[] contents, int hatchTime)
	{
		super(parent.world);
		this.parent = parent;
		this.contents = contents;
		this.hatchTime = hatchTime;
		this.setBurnsInDay(false);
		this.hatched = false;
		this.ticks = 0;
		this.setBaseMoveSpeedStat(0.01F);

		this.getDataManager().register(META_HATCHED, Byte.valueOf((byte)0));

		this.setMaxHealthAndHealth(mod_invasion.getMobHealth(this));
		this.setName("Spider Egg");
		this.setGender(0);
		this.setPosition(parent.posX, parent.posY, parent.posZ);
		this.setSize(0.5F, 0.8F);
	}

	@Override
	public String getSpecies()
	{
		return null;
	}

	@Override
	public int getTier()
	{
		return 0;
	}

	@Override
	public boolean isHostile()
	{
		return false;
	}

	@Override
	public boolean isNeutral()
	{
		return false;
	}

	@Override
	public boolean isThreatTo(Entity entity)
	{
		if ((entity instanceof EntityPlayer))
		{
			return true;
		}
		return false;
	}

	@Override
	public Entity getAttackingTarget()
	{
		return null;
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		if (!this.world.isRemote)
		{
			this.ticks += 1;
			if (this.hatched)
			{
				if (this.ticks > this.hatchTime + 40)
					this.setDead();
			}
			else if (this.ticks > this.hatchTime)
			{
				this.hatch();
			}
		}
		else if ((!this.hatched)
			&& (this.getDataManager().get(META_HATCHED) == 1))
		{
			//this.world.playSoundAtEntity(this, "invmod:egghatch", 1.0F, 1.0F);
			this.playSound(SoundHandler.egghatch1, 1f, 1f);
			this.hatched = true;
		}
	}

	private void hatch()
	{
		//this.world.playSoundAtEntity(this, "invmod:egghatch", 1.0F, 1.0F);
		this.playSound(SoundHandler.egghatch1, 1f, 1f);
		this.hatched = true;
		if (!this.world.isRemote)
		{
			this.getDataManager().set(META_HATCHED, Byte.valueOf((byte)1));
			if (this.contents != null)
			{
				for (Entity entity : this.contents)
				{
					entity.setPosition(this.posX, this.posY, this.posZ);
					this.world.spawnEntity(entity);
				}
			}
		}
	}

	@Override
	public String toString()
	{
		return "IMSpider-egg";
	}
}