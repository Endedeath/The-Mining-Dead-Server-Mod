package com.tmdmc.mod.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityInfector extends EntityBase {

	boolean initDone = false, jumper = false;
	
	public EntityInfector(World world) {
		super(world);
		this.setSize(0.6F, 1.8F);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		if (rand.nextInt(4) == 1)
			jumper = true;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((rand.nextFloat() + 0.5) * 20);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((rand.nextFloat() + 0.5) * 0.5);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((rand.nextFloat() + 0.8) * 5);
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
	
	@Override
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (p_70652_1_ instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)p_70652_1_);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)p_70652_1_);
        }

        boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        try
        {
        	EntityLivingBase entity = (EntityLivingBase)p_70652_1_;
        	entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 2));
        	entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 60, 2));
        	entity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 60, 2));
        	if (rand.nextInt(4) == 0)
        		entity.addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 2));
        	if (rand.nextInt(4) == 0)
        		entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 80, 2));
        }
        catch (Exception e) {  }
        
        if (flag)
        {
            if (i > 0)
            {
                p_70652_1_.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                p_70652_1_.setFire(j * 4);
            }

            if (p_70652_1_ instanceof EntityLivingBase)
            {
                EnchantmentHelper.func_151384_a((EntityLivingBase)p_70652_1_, this);
            }

            EnchantmentHelper.func_151385_b(this, p_70652_1_);
        }

        return flag;
    }
}
