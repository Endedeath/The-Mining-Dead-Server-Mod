package com.tmdmc.mod.entity;

import java.util.Random;

import com.tmdmc.mod.TMDMod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class EntityBase extends EntityMob {
	
	protected EntityBase(World world)
	{
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	public static void register()
	{
		register(EntityRunner.class, "entityRunner");
		register(EntityInfected.class, "entityInfected");
		register(EntityGlass.class, "entityGlass");
		register(EntityTank.class, "entityTank");
		register(EntityHealthy.class, "entityHealthy");
		register(EntityUnhealthy.class, "entityUnhealthy");
		register(EntitySuicide.class, "entitySuicide");
		register(EntityInfector.class, "entityInfector");
		register(EntityBomber.class, "entityBomber");
	}
	
	public static void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityRunner.class, new Renderer("tmd:textures/models/infected.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityInfected.class, new Renderer("tmd:textures/models/infected.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlass.class, new Renderer("tmd:textures/models/glass.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityTank.class, new Renderer("tmd:textures/models/infected.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityHealthy.class, new Renderer("tmd:textures/models/infected.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityUnhealthy.class, new Renderer("tmd:textures/models/infected.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntitySuicide.class, new Renderer("tmd:textures/models/suicide.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityInfector.class, new Renderer("tmd:textures/models/infected.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityBomber.class, new RendererCustom("tmd:textures/models/bomber.png", new ModelCreeper()));
	}
	
	@SuppressWarnings("unchecked")
	private static void register(Class<? extends Entity> entityClass, String name)
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		long seed = name.hashCode();
		Random rand = new Random(seed);
		int primaryColor = rand.nextInt() * 16777215;
		int secondaryColor = rand.nextInt() * 16777215;
 
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, TMDMod.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
	}
	
	public static class Renderer extends RenderBiped {
		public final ResourceLocation textureLocation;

		public Renderer(String textureLocation) {
			super(new ModelBiped(), 0.5F);
			this.textureLocation = new ResourceLocation(textureLocation);
		}

		@Override
		protected ResourceLocation getEntityTexture(Entity par1Entity)
		{
			return textureLocation;
		}
	}
	
	public static class RendererCustom extends RenderLiving {
		public final ResourceLocation textureLocation;

		public RendererCustom(String textureLocation, ModelBase model) {
			super(model, 0.5F);
			this.textureLocation = new ResourceLocation(textureLocation);
		}

		@Override
		protected ResourceLocation getEntityTexture(Entity par1Entity)
		{
			return textureLocation;
		}
	}
}