package me.antileaf.example.utils;

import basemod.BaseMod;

public abstract class ExampleAudioMaster {
	public static String[] CHAR_SELECT = new String[] {"SignatureExampleMod:CHAR_SELECT1", "SignatureExampleMod:CHAR_SELECT2"};

	public static void init() {
		BaseMod.addAudio(CHAR_SELECT[0], "SignatureExampleMod/audio/Maki_Title.ogg");
		BaseMod.addAudio(CHAR_SELECT[1], "SignatureExampleMod/audio/CH0235_Title.ogg");
	}
}
