package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.world.World;

@Mixin(ExperienceOrbEntity.class)
public abstract class RemoveXPOrb extends Entity {
    public RemoveXPOrb(EntityType<?> type, World world) {
        super(type, world);
    }

    public void tick() {
        this.remove();
    }
}
