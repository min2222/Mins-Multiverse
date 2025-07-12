package com.min01.multiverse.obj;

import java.util.ArrayList;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GroupObject
{
    public String name;
    public ArrayList<Face> faces = new ArrayList<Face>();
    public int glDrawingMode;

    public GroupObject()
    {
        this("");
    }

    public GroupObject(String name)
    {
        this(name, -1);
    }

    public GroupObject(String name, int glDrawingMode)
    {
        this.name = name;
        this.glDrawingMode = glDrawingMode;
    }

    @OnlyIn(Dist.CLIENT)
    public void render(VertexConsumer tessellator)
    {
        if(this.faces.size() > 0)
        {
            for(Face face : this.faces)
            {
                face.addFaceForRender(tessellator);
            }
        }
    }
}