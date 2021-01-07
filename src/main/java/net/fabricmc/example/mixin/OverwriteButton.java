package net.fabricmc.example.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

@Mixin(AbstractButtonBlock.class)
public abstract class OverwriteButton {

    // These shadows are bad, but do I look like I'm about to fix them? This is ButtonCraft.
    @Shadow private boolean wooden;
    private static BooleanProperty POWERED;

    @Shadow private void powerOn(BlockState state, World world, BlockPos pos) {}
    @Shadow private void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {}

	protected int getPressTicks() {
        return this.wooden ? 30 : 20;
     }

   protected int getType(){return 0;}

   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if ((Boolean)state.get(POWERED)) {
         return ActionResult.CONSUME;
      } else {
         // ADDED
         if(!world.isClient){
               double rand = Math.random();
               int type = this.getType();
               /*
               if(rand <= 0.01) player.addExperience(-100); // 1% lose 100
               else if(rand <= 0.11) player.addExperience(10); // 10% gain 10
               */
               if(this.wooden){ // EXPECTED -.3
                  if(rand <= 0.2) player.addExperience(-5); // 20% lose 5
                  else if(rand <= 0.3) player.addExperience(7); //10% gain 7
               } else if(type == 0){ // EXPECTED 0
                  if(rand <= 0.01) player.addExperience(-100); // 1% lose 100
                  else if(rand <= 0.11) player.addExperience(10); // 10% gain 10
               } else if(type == 1){ // EXPECTED +5
                  if(rand <= 0.01) player.addExperience(-1000); // 1% lose 1000
                  else if(rand <= 0.16) player.addExperience(100); // 15% gain 100
               } else{ // EXPECTED +25
                  if(rand <= 0.01) player.addExperience(-10000); // 1% lose 1000
                  else if(rand <= 0.5) player.addExperience(250); // 15% gain 100
               }
         }
         // ADDED
         this.powerOn(state, world, pos);
         this.playClickSound(player, world, pos, true);
         return ActionResult.success(world.isClient);
      }
   }
     
}
