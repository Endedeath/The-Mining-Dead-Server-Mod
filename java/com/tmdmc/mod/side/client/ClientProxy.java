package com.tmdmc.mod.side.client;

import com.tmdmc.mod.entity.EntityBase;
import com.tmdmc.mod.side.CommonProxy;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers()
	{
		EntityBase.registerRenderers();
	}
}
