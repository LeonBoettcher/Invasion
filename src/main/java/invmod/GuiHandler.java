package invmod;

import invmod.client.gui.GuiNexus;
import invmod.inventory.container.ContainerNexus;
import invmod.tileentity.TileEntityNexus;
import invmod.util.config.Config;
import net.minecraft.world.level.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getClientGuiElement(int id, Player player, Level world, int x, int y, int z) {

		if (id == Config.NEXUS_GUI_ID)
			return new GuiNexus(player.inventory, (TileEntityNexus) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

	@Override
	public Object getServerGuiElement(int id, Player player, Level world, int x, int y, int z) {
		if (id == Config.NEXUS_GUI_ID)
			return new ContainerNexus(player.inventory, (TileEntityNexus) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}
}