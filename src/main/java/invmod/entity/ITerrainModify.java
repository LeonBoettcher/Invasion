package invmod.entity;

import invmod.INotifyTask;

public interface ITerrainModify {
	public boolean isReadyForTask(INotifyTask paramINotifyTask);

	public boolean requestTask(ModifyBlockEntry[] paramArrayOfModifyBlockEntry, INotifyTask paramINotifyTask1,
			INotifyTask paramINotifyTask2);

	public ModifyBlockEntry getLastBlockModified();
}