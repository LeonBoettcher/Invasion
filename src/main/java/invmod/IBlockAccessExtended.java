package invmod;

import net.minecraft.core.MathHelper;
import net.minecraft.core.Vec3d;
import net.minecraft.world.level.IBlockAccess;

public interface IBlockAccessExtended extends IBlockAccess {

	public int getLayeredData(int paramInt1, int paramInt2, int paramInt3);

	public default int getLayeredData(double x, double y, double z) {
		return this.getLayeredData(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
	}

	public default int getLayeredData(Vec3d vec) {
		return this.getLayeredData(vec.x, vec.y, vec.z);
	}

	public void setData(double x, double y, double z, Integer paramInteger);

}