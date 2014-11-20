package com.tmdmc.mod.entity;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityUnhealthy extends EntityBase {

	static final Random rand = new Random();
	double strengthMod = 1, healthMod = 1, speedMod = 1;
	float sizeMod = 1;
	
	public EntityUnhealthy(World world) {
		super(world);
		sizeMod = rand.nextFloat();
		sizeMod += 0.5F;
		if (rand.nextInt(7) == 1) sizeMod = 0.3F;
		else if (rand.nextInt(5) == 1) sizeMod = 0.5F;
		this.setSize(sizeMod * 0.6F, sizeMod * 1.8F);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((rand.nextFloat() + 0.8) * 15);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((rand.nextFloat() + 0.8) * 0.3);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((rand.nextFloat() + 0.8) * 3);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}
}
