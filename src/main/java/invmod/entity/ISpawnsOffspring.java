package invmod.entity;

import net.minecraft.world.level.entity.Entity;

public abstract interface ISpawnsOffspring {
	public abstract Entity[] getOffspring(Entity paramEntity);
}