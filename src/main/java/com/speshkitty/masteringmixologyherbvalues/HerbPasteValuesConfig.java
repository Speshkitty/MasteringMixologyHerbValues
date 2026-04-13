package com.speshkitty.masteringmixologyherbvalues;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("mastering-mixology-herb-values")
public interface HerbPasteValuesConfig extends Config
{
	@ConfigItem(
			keyName = "showInMM",
			name = "Show in Mastering Mixology",
			description = "Shows the paste value of herbs inside Mastering Mixology"
	)
	default boolean showInMM()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showEverywhere",
			name = "Show everywhere",
			description = "Shows the paste value of herbs everywhere"
	)
	default boolean showEverywhere()
	{
		return false;
	}

	@ConfigItem(
			keyName = "displayPosition",
			name = "Display Position",
			description = "The position the text is drawn"
	)
	default DisplayPosition priceDisplayMode()
	{
		return DisplayPosition.TOP_RIGHT;
	}
}
