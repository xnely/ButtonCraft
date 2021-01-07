package net.fabricmc.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class NegativeXP extends LivingEntity {

    protected NegativeXP(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract boolean isCreative();
    @Shadow public abstract void addScore(int score);


    @Shadow public float experienceProgress;
    @Shadow public int totalExperience;
    @Shadow public int experienceLevel;
    @Shadow private int lastPlayedLevelUpSoundTime;


    //Allow negative xp
    @Overwrite public void addExperience(int experience) {
        this.addScore(experience);
        this.experienceProgress += (float)experience / (float)this.getNextLevelExperience();
        this.totalExperience = MathHelper.clamp(this.totalExperience + experience, Integer.MIN_VALUE, Integer.MAX_VALUE); //Integer.MIN_VALUE was 0

  
        while(this.experienceProgress < 0.0F) {
           float f = this.experienceProgress * (float)this.getNextLevelExperience();
           if (this.experienceLevel > 0) {
              this.addExperienceLevels(-1);
              this.experienceProgress = 1.0F + f / (float)this.getNextLevelExperience();
           } else {
              this.addExperienceLevels(-1);
              this.experienceProgress = 1.0F + f / (float)this.getNextLevelExperience();
           }
        }
  
        while(this.experienceProgress >= 1.0F) {
           this.experienceProgress = (this.experienceProgress - 1.0F) * (float)this.getNextLevelExperience();
           this.addExperienceLevels(1);
           this.experienceProgress /= (float)this.getNextLevelExperience();
        }
  
     }

     @Overwrite public void addExperienceLevels(int levels) {
        this.experienceLevel += levels;
        
        /* Would reset progress to zero, preventing negative Xp
        if (this.experienceLevel < 0) {
           this.experienceLevel = 0;
           this.experienceProgress = 0.0F;
           this.totalExperience = 0;
        }*/
  
        if (levels > 0 && this.experienceLevel % 5 == 0 && (float)this.lastPlayedLevelUpSoundTime < (float)this.age - 100.0F) {
           float f = this.experienceLevel > 30 ? 1.0F : (float)this.experienceLevel / 30.0F;
           this.world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, this.getSoundCategory(), f * 0.75F, 1.0F);
           this.lastPlayedLevelUpSoundTime = this.age;
        }
  
     }

     @Overwrite public int getNextLevelExperience() {
        if (this.experienceLevel >= 30) {
           return 112 + (this.experienceLevel - 30) * 9;
        } else if(this.experienceLevel < 0){ // Added so that negative levels don't scale like positive ones, to demoralize the player
            return 7;
        } else {
           return this.experienceLevel >= 15 ? 37 + (this.experienceLevel - 15) * 5 : 7 + this.experienceLevel * 2;
        }
     }
}
