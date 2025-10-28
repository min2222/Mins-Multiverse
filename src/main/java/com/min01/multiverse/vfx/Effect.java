package com.min01.multiverse.vfx;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Effect
{
    public int lifetime;
    public int maxLife;
    public float r;
    public float g;
    public float b;
    public float a;
    public boolean inited;
    public float x;
    public float y;
    public float z;
    public float px;
    public float py;
    public float pz;
    public float vx;
    public float vy;
    public float vz;
    public boolean additive;
    public boolean dead;
    public int dimId;
    
    public Effect() 
    {
        this.lifetime = 0;
        this.maxLife = 0;
        this.r = 0.0F;
        this.g = 0.0F;
        this.b = 0.0F;
        this.a = 0.0F;
        this.inited = false;
        this.x = 0.0F;
        this.y = 0.0F;
        this.z = 0.0F;
        this.px = 0.0F;
        this.py = 0.0F;
        this.pz = 0.0F;
        this.vx = 0.0F;
        this.vy = 0.0F;
        this.vz = 0.0F;
        this.additive = false;
        this.dead = false;
        this.dimId = 0;
    }
    
    public Effect(int id) 
    {
        this.lifetime = 0;
        this.maxLife = 0;
        this.r = 0.0F;
        this.g = 0.0F;
        this.b = 0.0F;
        this.a = 0.0F;
        this.inited = false;
        this.x = 0.0F;
        this.y = 0.0F;
        this.z = 0.0F;
        this.px = 0.0F;
        this.py = 0.0F;
        this.pz = 0.0F;
        this.vx = 0.0F;
        this.vy = 0.0F;
        this.vz = 0.0F;
        this.additive = false;
        this.dead = false;
        this.dimId = 0;
        this.dimId = id;
    }
    
    public Effect setLife(int l) 
    {
        this.maxLife = l;
        this.lifetime = l;
        return this;
    }
    
    public Effect setColor(float r, float g, float b, float a) 
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        return this;
    }
    
    public Effect setPosition(float x, float y, float z) 
    {
        this.px = x;
        this.py = y;
        this.pz = z;
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    
    public Effect setMotion(float vx, float vy, float vz)
    {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        return this;
    }
    
    public Effect setAdditive(boolean additive)
    {
        this.additive = additive;
        return this;
    }
    
    public void update() 
    {
        if(!this.inited) 
        {
            this.inited = true;
        }
        this.px = this.x;
        this.py = this.y;
        this.pz = this.z;
        this.x += this.vx;
        this.y += this.vy;
        this.z += this.vz;
        --this.lifetime;
        if(this.lifetime < 0) 
        {
            this.kill();
        }
    }
    
    public void kill() 
    {
        this.dead = true;
    }
    
    public float getLifeCoeff(float partialTicks)
    {
        return Math.max(0.0F, (this.lifetime - partialTicks) / this.maxLife);
    }
    
    public float getInterpX(float partialTicks)
    {
        return Mth.lerp(partialTicks, this.px, this.x);
    }
    
    public float getInterpY(float partialTicks) 
    {
        return Mth.lerp(partialTicks, this.py, this.y);
    }
    
    public float getInterpZ(float partialTicks) 
    {
        return Mth.lerp(partialTicks, this.pz, this.z);
    }
    
    @OnlyIn(Dist.CLIENT)
    public void renderTotal(PoseStack stack, float partialTicks, MultiBufferSource buffer, Entity entity) 
    {
        double x = Mth.lerp(partialTicks, entity.xOld, entity.getX());
        double y = Mth.lerp(partialTicks, entity.yOld, entity.getY());
        double z = Mth.lerp(partialTicks, entity.zOld, entity.getZ());
        this.renderTotal(stack, partialTicks, buffer, new Vec3(x, y, z));
    }
    
    @OnlyIn(Dist.CLIENT)
    public void renderTotal(PoseStack stack, float partialTicks, MultiBufferSource buffer, Vec3 pos) 
    {
        if(this.inited)
        {
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, this.additive ? GlStateManager.DestFactor.ONE : GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.depthMask(false);
            int dfunc = GL11.glGetInteger(2932);
            RenderSystem.depthFunc(515);
            RenderSystem.disableCull();
            stack.pushPose();
            stack.translate(-pos.x, -pos.y, -pos.z);
            this.render(stack, partialTicks, buffer);
            stack.popPose();
            RenderSystem.depthFunc(dfunc);
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
        }
    }
    
    public void read(CompoundTag tag)
    {
        this.px = this.x;
        this.x = tag.getFloat("x");
        this.py = this.y;
        this.y = tag.getFloat("y");
        this.pz = this.z;
        this.z = tag.getFloat("z");
        this.vx = tag.getFloat("vx");
        this.vy = tag.getFloat("vy");
        this.vz = tag.getFloat("vz");
        this.r = tag.getFloat("r");
        this.g = tag.getFloat("g");
        this.b = tag.getFloat("b");
        this.a = tag.getFloat("a");
        this.maxLife = tag.getInt("maxlife");
        this.lifetime = tag.getInt("life");
        this.dimId = tag.getInt("dim");
        this.additive = tag.getBoolean("additive");
    }
    
    public CompoundTag write() 
    {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("x", (double)this.x);
        tag.putDouble("y", (double)this.y);
        tag.putDouble("z", (double)this.z);
        tag.putDouble("vx", (double)this.vx);
        tag.putDouble("vy", (double)this.vy);
        tag.putDouble("vz", (double)this.vz);
        tag.putFloat("r", this.r);
        tag.putFloat("g", this.g);
        tag.putFloat("b", this.b);
        tag.putFloat("a", this.a);
        tag.putInt("maxlife", this.maxLife);
        tag.putInt("life", this.lifetime);
        tag.putInt("dim", this.dimId);
        tag.putBoolean("additive", this.additive);
        return tag;
    }
    
    @OnlyIn(Dist.CLIENT)
    public void render(PoseStack stack, float partialTicks, MultiBufferSource buffer)
    {
    	
    }
}