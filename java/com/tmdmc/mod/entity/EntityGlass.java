package com.tmdmc.mod.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityGlass extends EntityBase {

	boolean initDone = false;
	
	public EntityGlass(World world) {
		super(world);
		this.setSize(0.6F, 1.8F);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((rand.nextFloat() + 0.5) * 1);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((rand.nextFloat() + 0.8) * 7);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(!initDone)
		{
			this.addPotionEffect(new PotionEffect(Potion.jump.id, 5000, 3));
		}
		initDone = true;
	}
}
