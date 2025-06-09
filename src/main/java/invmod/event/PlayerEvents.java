package invmod.event;

import java.util.Map;

import invmod.mod_invasion;
import invmod.util.ModLogger;
import net.minecraft.world.level.entity.player.Player;
import net.minecraft.core.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEvents {

	@SubscribeEvent
	public void playerLoginEvent(EntityJoinWorldEvent entityJoinWorldEvent) {
		ModLogger.logDebug("Player logged in.");

		for (Map.Entry entry : mod_invasion.deathList.entrySet()) {
			for (Level world : DimensionManager.getWorlds()) {
				Player player = world.getPlayerEntityByName((String) entry.getKey());
				if (player != null) {
					player.attackEntityFrom(DamageSource.MAGIC, 500.0F);
					player.setDead();
					mod_invasion.deathList.remove(player.getDisplayName());
					mod_invasion.broadcastToAll("Nexus energies caught up to " + player.getDisplayName());
				}
			}
		}

	}
}
