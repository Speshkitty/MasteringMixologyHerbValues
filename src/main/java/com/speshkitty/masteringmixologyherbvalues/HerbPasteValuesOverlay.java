package com.speshkitty.masteringmixologyherbvalues;

import net.runelite.api.gameval.ItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.components.TextComponent;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.awt.*;
import java.util.HashMap;

public class HerbPasteValuesOverlay extends WidgetItemOverlay {
    private final HerbPasteValuesPlugin herbPasteValuesPlugin;
    private final HerbPasteValuesConfig config;
    private final Logger log;

    private static HashMap<Integer, HerbPasteData> herbPasteValues;

    static {
        herbPasteValues = new HashMap<>();
        herbPasteValues.put(ItemID.GUAM_LEAF, new HerbPasteData(10, PasteType.MOX));
        herbPasteValues.put(ItemID.MARENTILL, new HerbPasteData(13, PasteType.MOX));
        herbPasteValues.put(ItemID.TARROMIN, new HerbPasteData(15, PasteType.MOX));
        herbPasteValues.put(ItemID.HARRALANDER, new HerbPasteData(20, PasteType.MOX));
        herbPasteValues.put(ItemID.RANARR_WEED, new HerbPasteData(26, PasteType.LYE));
        herbPasteValues.put(ItemID.TOADFLAX, new HerbPasteData(32, PasteType.LYE));
        herbPasteValues.put(ItemID.IRIT_LEAF, new HerbPasteData(30, PasteType.AGA));
        herbPasteValues.put(ItemID.AVANTOE, new HerbPasteData(30, PasteType.LYE));
        herbPasteValues.put(ItemID.KWUARM, new HerbPasteData(33, PasteType.LYE));
        herbPasteValues.put(ItemID.HUASCA, new HerbPasteData(20, PasteType.AGA));
        herbPasteValues.put(ItemID.SNAPDRAGON, new HerbPasteData(40, PasteType.LYE));
        herbPasteValues.put(ItemID.CADANTINE, new HerbPasteData(34, PasteType.AGA));
        herbPasteValues.put(ItemID.LANTADYME, new HerbPasteData(40, PasteType.AGA));
        herbPasteValues.put(ItemID.DWARF_WEED, new HerbPasteData(42, PasteType.AGA));
        herbPasteValues.put(ItemID.TORSTOL, new HerbPasteData(44, PasteType.AGA));
    }

    @Inject
    HerbPasteValuesOverlay(HerbPasteValuesPlugin herbPasteValuesPlugin, HerbPasteValuesConfig config)
    {
        this.herbPasteValuesPlugin = herbPasteValuesPlugin;
        this.config = config;
        this.log = this.herbPasteValuesPlugin.getLogger();

        showOnInventory();
        showOnBank();
    }

    private Point getTextPosition(Rectangle bounds) {
        int xPos = 0;
        int yPos = 0;

        switch(config.priceDisplayMode()){
            case TOP_LEFT:
                xPos = bounds.x;
                yPos = bounds.y + 10;
                break;
            case TOP_RIGHT:
                xPos = bounds.x + bounds.width - 15;
                yPos = bounds.y + 10;
                break;
            case BOTTOM_LEFT:
                xPos = bounds.x;
                yPos = bounds.y + bounds.height;
                break;
            case BOTTOM_RIGHT:
                xPos = bounds.x + bounds.width - 15;
                yPos = bounds.y + bounds.height;
                break;
        }

        return new Point(xPos, yPos);
    }

    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
        if (!herbPasteValuesPlugin.ShouldDrawInfo()) {
            return;
        }

        if (!herbPasteValues.containsKey(itemId)) {
            return;
        }

        HerbPasteData hpd = herbPasteValues.get(itemId);

        graphics.setFont(FontManager.getRunescapeSmallFont());

        final TextComponent textComponent = new TextComponent();

        textComponent.setPosition(getTextPosition(widgetItem.getCanvasBounds()));

        switch (hpd.type) {
            case AGA:
                textComponent.setColor(Color.GREEN);
                break;
            case LYE:
                textComponent.setColor(Color.RED);
                break;
            case MOX:
                textComponent.setColor(Color.BLUE);
                break;
        }
        textComponent.setText(String.valueOf(hpd.pasteAmount));

        textComponent.render(graphics);
    }
}
