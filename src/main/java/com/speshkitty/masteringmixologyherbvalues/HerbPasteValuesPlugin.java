package com.speshkitty.masteringmixologyherbvalues;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import org.slf4j.Logger;

import java.awt.*;

@Slf4j
@PluginDescriptor(
	name = "Mastering Mixology Herb Values"
)
public class HerbPasteValuesPlugin extends Plugin
{
	private final Rectangle masteringMixologyArea = new Rectangle(1382, 9307, 24, 35);

	@Inject	private Client client;
	@Inject	private HerbPasteValuesConfig config;
	@Inject	private HerbPasteValuesOverlay overlay;
	@Inject	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		log.info("{} started", this.getName());
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.info("{} stopped", this.getName());
	}

	protected Logger getLogger(){
		return log;
	}

	protected boolean ShouldDrawInfo(){
		if(TileIsInMixology(client.getLocalPlayer().getWorldLocation()) && config.showInMM()) {
			return true;
		}
		return config.showEverywhere();
	}

	private boolean TileIsInMixology(WorldPoint tile) {
		return masteringMixologyArea.contains(new Point(tile.getX(), tile.getY()));
	}

	@Provides
	HerbPasteValuesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HerbPasteValuesConfig.class);
	}
}
