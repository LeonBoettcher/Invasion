package invmod.entity.ai;

import net.minecraft.world.level.entity.LivingEntity;
import net.minecraft.world.level.entity.ai.EntityAIBase;

public class EntityAIWatchTarget extends EntityAIBase {
	private LivingEntity theEntity;

	public EntityAIWatchTarget(LivingEntity entity) {
		this.theEntity = entity;
	}

	@Override
	public boolean shouldExecute() {
		return this.theEntity.getAttackTarget() != null;
	}

	@Override
	public void updateTask() {
		this.theEntity.getLookHelper().setLookPositionWithEntity(this.theEntity.getAttackTarget(), 2.0F, 2.0F);
	}
}