package mcjty.witila.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class Renderer {

    public static void renderFocusedBlockInfo(RenderGameOverlayEvent evt) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        MovingObjectPosition mouseOver = Minecraft.getMinecraft().objectMouseOver;
        if (mouseOver != null && mouseOver.getBlockPos() != null) {
            IBlockState state = player.worldObj.getBlockState(mouseOver.getBlockPos());
            Block block = state.getBlock();
            int meta = block.getMetaFromState(state);

            renderBlockInfo(state, block, meta);

        }
    }

    private static void renderBlockInfo(IBlockState state, Block block, int meta) {
        GlStateManager.pushAttrib();

        Gui.drawRect(10, 10, 160, 44, 0x66222222);

        FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
        ItemStack s = new ItemStack(block, 1, meta);
        if (s.getItem() != null) {
            ResourceLocation nameForObject = Block.blockRegistry.getNameForObject(block);
            int y = 16;
            fontrenderer.drawString(s.getDisplayName(), 20, y, 0xffffffff, true); y += 14;
            fontrenderer.drawString(nameForObject.getResourceDomain(), 20, y, 0xff0066ff, true);
        }

        GlStateManager.popAttrib();
    }
}
