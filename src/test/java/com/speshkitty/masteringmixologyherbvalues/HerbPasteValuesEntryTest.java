package com.speshkitty.masteringmixologyherbvalues;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

import java.util.ArrayList;
import java.util.List;

public class HerbPasteValuesEntryTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HerbPasteValuesPlugin.class);

		List<String> arguments = new ArrayList<>();

		arguments.add("--profile");
		arguments.add("test");
		arguments.add("--debug");
		arguments.add("--developer-mode");

		String[] arrayArgs = arguments.toArray(String[]::new);
		RuneLite.main(arrayArgs);
	}
}