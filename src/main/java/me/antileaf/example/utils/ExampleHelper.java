package me.antileaf.example.utils;

import com.megacrit.cardcrawl.core.Settings;
import me.antileaf.example.SignatureExampleModCore;

public abstract class ExampleHelper {
	public static String makeID(String id) {
		return SignatureExampleModCore.MOD_ID + ":" + id;
	}

	public static String getImgFilePath(String type, String name) {
		return "SignatureExampleMod/img/" + type + "/" + name + ".png";
	}

	public static String getCardImgFilePath(String name) {
		return getImgFilePath("cards", name);
	}

	public static String getRelicImgFilePath(String name) {
		return getImgFilePath("relics", name);
	}

	public static String getRelicOutlineImgFilePath(String name) {
		return getImgFilePath("relics/outline", name);
	}

	public static String getRelicLargeImgFilePath(String name) {
		return getImgFilePath("relics/large", name);
	}

	public static String getLangShort() {
		if (Settings.language == Settings.GameLanguage.ZHS ||
				Settings.language == Settings.GameLanguage.ZHT ||
				Settings.language == Settings.GameLanguage.JPN ||
				Settings.language == Settings.GameLanguage.KOR)
			return "zhs";
		else
			return "eng";
	}
}
