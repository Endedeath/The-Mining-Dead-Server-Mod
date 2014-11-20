package com.tmdmc.mod.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityTank extends EntityBase {

	boolean initDone = false;
	
	public EntityTank(World world) {
		super(world);
		this.setSize(0.8F, 2.5F);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((rand.nextFloat() + 0.5) * 30);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((rand.nextFloat() + 0.5) * 0.25);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((rand.nextFloat() + 0.8) * 6);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(!initDone)
		{
			this.addPotionEffect(new PotionEffect(Potion.jump.id, 5000, 1));
		}
		initDone = true;
	}
}
