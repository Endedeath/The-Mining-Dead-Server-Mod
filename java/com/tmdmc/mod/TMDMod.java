package com.tmdmc.mod;

import com.tmdmc.mod.entity.EntityBase;
import com.tmdmc.mod.side.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = TMDMod.MODID, version = TMDMod.VERSION)
public class TMDMod
{
    public static final String MODID = "tmd";
    public static final String VERSION = "NoVersioning";
    
    @Instance(MODID)
    public static TMDMod instance = null;
    
    @SidedProxy(clientSide="com.tmdmc.mod.side.client.ClientProxy", serverSide="com.tmdmc.mod.side.client.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	EntityBase.register();
    	proxy.registerRenderers();
    }
}
