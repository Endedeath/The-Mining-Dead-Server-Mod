package com.tmdmc.mod.entity;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityInfected extends EntityBase {

	static final Random rand = new Random();
	boolean initDone = false, jumper = false;
	double strengthMod = 1, healthMod = 1, speedMod = 1;
	float sizeMod = 1;
	
	public EntityInfected(World world) {
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
		jumper = rand.nextBoolean();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((rand.nextFloat() + 0.8) * 20);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((rand.nextFloat() + 0.8) * 0.3);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((rand.nextFloat() + 0.8) * 4);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(!initDone)
		{
			if (jumper)
				this.addPotionEffect(new PotionEffect(Potion.jump.id, 5000, 1));
		}
		initDone = true;
	}
}
