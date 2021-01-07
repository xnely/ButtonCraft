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
public class OverwriteButton {
    @Shadow
    private boolean wooden;
    private static BooleanProperty POWERED;

    @SuppressWarnings("unused")
	private int getPressTicks() {
        return this.wooden ? 30 : 20;
     }

     public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if ((Boolean)state.get(POWERED)) {
           return ActionResult.CONSUME;
        } else {
            // ADDED
            if(!world.isClient){
                double rand = Math.random();
                /* //OLD way
                if(rand <= .1)player.addExperience(-10);
                else if(rand <= .3) player.addExperience(10);*/
                if(rand <= 0.01) player.addExperience(-100); // 1% lose 100
                else if(rand <= 0.11) player.addExperience(10); // 10% gain 10
            }
           // ADDED
           this.powerOn(state, world, pos);
           this.playClickSound(player, world, pos, true);
           return ActionResult.success(world.isClient);
        }
     }

	@Shadow
     private void powerOn(BlockState state, World world, BlockPos pos) {}
    @Shadow
     private void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {}
     
}
