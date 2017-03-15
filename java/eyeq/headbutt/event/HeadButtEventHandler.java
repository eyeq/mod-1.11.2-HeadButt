package eyeq.headbutt.event;

import eyeq.headbutt.HeadButt;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HeadButtEventHandler {
    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        if(world.isRemote) {
            return;
        }
        if(!(entity instanceof EntityPlayer)) {
            return;
        }
        if(event.getSource() != DamageSource.IN_WALL) {
            return;
        }
        EntityPlayer player = (EntityPlayer) entity;
        BlockPos pos = new BlockPos(player.getPositionEyes(1.0F));
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if(block != Blocks.AIR && block.getHarvestLevel(state) <= HeadButt.harvestLevel) {
            world.destroyBlock(pos, true);
        }
    }
}
