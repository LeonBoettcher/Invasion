package invmod.entity.ai;

import invmod.entity.monster.EntityIMMob;
import net.minecraft.world.level.entity.LivingEntity;

public class EntityAILeaderTarget extends EntityAISimpleTarget {
	private final EntityIMMob theEntity;

	public EntityAILeaderTarget(EntityIMMob entity, Class<? extends LivingEntity> targetType, float distance) {
		this(entity, targetType, distance, true);
	}

	public EntityAILeaderTarget(EntityIMMob entity, Class<? extends LivingEntity> targetType, float distance,
			boolean needsLos) {
		super(entity, targetType, distance, needsLos);
		this.theEntity = entity;
	}

	@Override
	public boolean shouldExecute() {
		if (!this.theEntity.readyToRally()) {
			return false;
		}
		return super.shouldExecute();
	}
}