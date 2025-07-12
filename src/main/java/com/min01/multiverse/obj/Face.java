package com.min01.multiverse.obj;

import java.awt.Color;
import java.util.function.BiFunction;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Face
{
    public static boolean IS_SMOOTH_SHADE = true;
    public static int LIGHTMAP = 15;
    
    public static void setLightMap(int value)
    {
    	LIGHTMAP = value;
    }
    
    public static void resetLightMap()
    {
    	LIGHTMAP = 15;
    }

    public static final BiFunction<Vector4f, Integer, Integer> ALPHA_NO_OVERRIDE = (v, a) -> a;
    public static final BiFunction<Vector4f, Integer, Integer> ALPHA_OVERRIDE_YZZ = (v, a) -> v.y() == 0 ? 0 : a;
    public static BiFunction<Vector4f, Integer, Integer> ALPHA_OVERRIDE = ALPHA_NO_OVERRIDE;

    public static void setAlphaOverride(BiFunction<Vector4f, Integer, Integer> alphaOverride)
    {
    	ALPHA_OVERRIDE = alphaOverride;
    }
    
    public static void resetAlphaOverride()
    {
    	ALPHA_OVERRIDE = ALPHA_NO_OVERRIDE;
    }

    public static final Vector4f UV_DEFAULT_OPERATOR = new Vector4f(1, 1, 0, 0);
    public static Vector4f UV_OPERATOR = UV_DEFAULT_OPERATOR;

    public static void setUvOperator(float uScale, float vScale, float uOffset, float vOffset) 
    {
    	UV_OPERATOR = new Vector4f(uScale, vScale, uOffset, vOffset);
    }
    
    public static void resetUvOperator()
    {
    	UV_OPERATOR = UV_DEFAULT_OPERATOR;
    }

    public static Color COLOR = Color.WHITE;
    
    public static void setColor(Color color) 
    {
    	COLOR = color;
    }
    
    public static void resetColor() 
    {
    	COLOR = Color.WHITE;
    }

    @SuppressWarnings("deprecation")
	private static final LazyLoadedValue<Matrix4f> DEFAULT_TRANSFORM = new LazyLoadedValue<>(()-> 
	{
		Matrix4f m = new Matrix4f(); 
		return m.identity();
	});

    public static PoseStack MATRIX = null;
    
    public static void setMatrix(PoseStack matrix)
    {
    	MATRIX = matrix;
    }
    
    public static void resetMatrix()
    {
    	MATRIX = null;
    }
    
    public static boolean FORCE_QUAD = false;

    public Vertex[] vertices;
    public Vertex[] vertexNormals;
    public Vertex faceNormal;
    public TextureCoordinate[] textureCoordinates;

    @OnlyIn(Dist.CLIENT)
    public void addFaceForRender(VertexConsumer consumer)
    {
        this.addFaceForRender(consumer, 0.0005F);
    }

    @SuppressWarnings("deprecation")
	@OnlyIn(Dist.CLIENT)
    public void addFaceForRender(VertexConsumer consumer, float textureOffset)
    {
        if(this.faceNormal == null)
        {
        	this.faceNormal = this.calculateFaceNormal();
        }

        float averageU = 0F;
        float averageV = 0F;

        if((this.textureCoordinates != null) && (this.textureCoordinates.length > 0))
        {
            for (int i = 0; i < this.textureCoordinates.length; ++i)
            {
                averageU += this.textureCoordinates[i].u * UV_OPERATOR.x() + UV_OPERATOR.z();
                averageV += this.textureCoordinates[i].v * UV_OPERATOR.y() + UV_OPERATOR.w();
            }

            averageU = averageU / this.textureCoordinates.length;
            averageV = averageV / this.textureCoordinates.length;
        }

        VertexConsumer wr = consumer;

        Matrix4f transform;
        if(MATRIX != null)
        {
            PoseStack.Pose me = MATRIX.last();
            transform = me.pose();
        }
        else
        {
            transform = DEFAULT_TRANSFORM.get();
        }

        if(FORCE_QUAD)
        {
        	this.putVertex(wr,0,transform,textureOffset,averageU,averageV);
        }

        for(int i = 0; i < this.vertices.length; ++i)
        {
        	this.putVertex(wr,i,transform,textureOffset,averageU,averageV);
        }
    }

    void putVertex(VertexConsumer wr, int i, Matrix4f transform, float textureOffset, float averageU, float averageV)
    {
        float offsetU, offsetV;
        wr.vertex(transform, this.vertices[i].x, this.vertices[i].y, this.vertices[i].z);
        wr.color(COLOR.getRed(), COLOR.getGreen(), COLOR.getBlue(), ALPHA_OVERRIDE.apply(new Vector4f(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z, 1.0F), COLOR.getAlpha()));
        if((this.textureCoordinates != null) && (this.textureCoordinates.length > 0))
        {
            offsetU = textureOffset;
            offsetV = textureOffset;
            float textureU = this.textureCoordinates[i].u * UV_OPERATOR.x() + UV_OPERATOR.z();
            float textureV = this.textureCoordinates[i].v * UV_OPERATOR.y() + UV_OPERATOR.w();
            if(textureU > averageU)
            {
                offsetU = -offsetU;
            }
            if(textureV > averageV)
            {
                offsetV = -offsetV;
            }
            wr.uv(textureU + offsetU, textureV + offsetV);
        }
        else
        {
            wr.uv(0, 0);
        }
        wr.overlayCoords(OverlayTexture.NO_OVERLAY);
        wr.uv2(LIGHTMAP);
        Vector3f vector3f;
        if(IS_SMOOTH_SHADE && this.vertexNormals != null)
        {
            Vertex normal = this.vertexNormals[i];
            Vec3 nol = new Vec3(normal.x, normal.y, normal.z);
            vector3f = new Vector3f((float)nol.x, (float)nol.y, (float)nol.z);
        }
        else
        {
            vector3f = new Vector3f(this.faceNormal.x, this.faceNormal.y, this.faceNormal.z);
        }
        vector3f.mul(new Matrix3f(transform));;
        vector3f.normalize();
        wr.normal(vector3f.x(), vector3f.y(), vector3f.z());
        wr.endVertex();
    }

    public Vertex calculateFaceNormal()
    {
        Vec3 v1 = new Vec3(this.vertices[1].x - this.vertices[0].x, this.vertices[1].y - this.vertices[0].y, this.vertices[1].z - this.vertices[0].z);
        Vec3 v2 = new Vec3(this.vertices[2].x - this.vertices[0].x, this.vertices[2].y - this.vertices[0].y, this.vertices[2].z - this.vertices[0].z);
        Vec3 normalVector = null;
        normalVector = v1.cross(v2).normalize();
        return new Vertex((float) normalVector.x, (float) normalVector.y, (float) normalVector.z);
    }
}