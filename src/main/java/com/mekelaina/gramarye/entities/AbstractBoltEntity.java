package com.mekelaina.gramarye.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public abstract class AbstractBoltEntity extends DamagingProjectileEntity implements IRendersAsItem {

    public LivingEntity  shootingEntity;
    private int ticksAlive;
    private int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;

    protected AbstractBoltEntity(EntityType<? extends AbstractBoltEntity> entityType, World world) {
        super(entityType, world);
    }

    /*protected AbstractBoltEntity();*/

   /* public AbstractBoltEntity(EntityType<? extends AbstractBoltEntity> EntityType, double x, double y, double z, double xAccIn, double yAccIn, double zAccIn, World world) {
        this(EntityType, world);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        double d0 = (double) MathHelper.sqrt(xAccIn * xAccIn + yAccIn * yAccIn + zAccIn * zAccIn);
        this.accelerationX = xAccIn / d0 * 0.1D;
        this.accelerationY = yAccIn / d0 * 0.1D;
        this.accelerationZ = zAccIn / d0 * 0.1D;
    }

    public AbstractBoltEntity(EntityType<? extends AbstractBoltEntity> entityType, LivingEntity shootingEntityIn, double xAccIn, double yAccIn, double zAccIn, World world) {
        this(entityType, world);
        this.shootingEntity = shootingEntityIn;
        this.setLocationAndAngles(shootingEntityIn.posX, shootingEntityIn.posY, shootingEntityIn.posZ, shootingEntityIn.rotationYaw, shootingEntityIn.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.setMotion(Vec3d.ZERO);
        xAccIn = xAccIn + this.rand.nextGaussian() * 0.4D;
        yAccIn = yAccIn + this.rand.nextGaussian() * 0.4D;
        zAccIn = zAccIn + this.rand.nextGaussian() * 0.4D;
        double d0 = (double) MathHelper.sqrt(xAccIn * xAccIn + yAccIn * yAccIn + zAccIn * zAccIn);
        this.accelerationX = xAccIn / d0 * 0.1D;
        this.accelerationY = yAccIn / d0 * 0.1D;
        this.accelerationZ = zAccIn / d0 * 0.1D;
    }*/

    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double d0 = this.getBoundingBox().getAverageEdgeLength() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.removed) && this.world.isBlockLoaded(new BlockPos(this))) {
            super.tick();

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.func_221266_a(this, true, this.ticksInAir >= 25, this.shootingEntity, RayTraceContext.BlockMode.COLLIDER);
            if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            Vec3d vec3d = this.getMotion();
            this.posX += vec3d.x;
            this.posY += vec3d.y;
            this.posZ += vec3d.z;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();
            if (this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    float f1 = 0.25F;
                    this.world.addParticle(ParticleTypes.BUBBLE, this.posX - vec3d.x * 0.25D, this.posY - vec3d.y * 0.25D, this.posZ - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
                }

                f = 0.8F;
            }

            this.setMotion(vec3d.add(this.accelerationX, this.accelerationY, this.accelerationZ).scale((double)f));
            this.world.addParticle(this.getParticle(), this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.setPosition(this.posX, this.posY, this.posZ);
        } else {
            this.remove();
        }
    }


    protected IParticleData getParticle() {
        return ParticleTypes.SMOKE;
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor() {
        return 0.95F;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected abstract void onImpact(RayTraceResult result);

    public void writeAdditional(CompoundNBT compound) {
        Vec3d vec3d = this.getMotion();
        compound.put("direction", this.newDoubleNBTList(new double[]{vec3d.x, vec3d.y, vec3d.z}));
        compound.put("power", this.newDoubleNBTList(new double[]{this.accelerationX, this.accelerationY, this.accelerationZ}));
        compound.putInt("life", this.ticksAlive);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        if (compound.contains("power", 9)) {
            ListNBT listnbt = compound.getList("power", 6);
            if (listnbt.size() == 3) {
                this.accelerationX = listnbt.getDouble(0);
                this.accelerationY = listnbt.getDouble(1);
                this.accelerationZ = listnbt.getDouble(2);
            }
        }

        this.ticksAlive = compound.getInt("life");
        if (compound.contains("direction", 9) && compound.getList("direction", 6).size() == 3) {
            ListNBT listnbt1 = compound.getList("direction", 6);
            this.setMotion(listnbt1.getDouble(0), listnbt1.getDouble(1), listnbt1.getDouble(2));
        } else {
            this.remove();
        }

    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return true;
    }

    public float getCollisionBorderSize() {
        return 1.0F;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.markVelocityChanged();
            if (source.getTrueSource() != null) {
                Vec3d vec3d = source.getTrueSource().getLookVec();
                this.setMotion(vec3d);
                this.accelerationX = vec3d.x * 0.1D;
                this.accelerationY = vec3d.y * 0.1D;
                this.accelerationZ = vec3d.z * 0.1D;
                if (source.getTrueSource() instanceof LivingEntity) {
                    this.shootingEntity = (LivingEntity)source.getTrueSource();
                }

                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness() {
        return 1.0F;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBrightnessForRender() {
        return 15728880;
    }

    public IPacket<?> createSpawnPacket() {
        int i = this.shootingEntity == null ? 0 : this.shootingEntity.getEntityId();
        return new SSpawnObjectPacket(this.getEntityId(), this.getUniqueID(), this.posX, this.posY, this.posZ, this.rotationPitch, this.rotationYaw, this.getType(), i, new Vec3d(this.accelerationX, this.accelerationY, this.accelerationZ));
    }
}
