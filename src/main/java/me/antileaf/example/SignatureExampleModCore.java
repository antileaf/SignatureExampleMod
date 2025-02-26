package me.antileaf.example;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import me.antileaf.example.cards.maki.EverydayMomentsWithYou;
import me.antileaf.example.cards.maki.PaintballLob;
import me.antileaf.example.cards.maki.Retro;
import me.antileaf.example.character.Maki;
import me.antileaf.example.patches.enums.CardColorEnum;
import me.antileaf.example.patches.enums.PlayerEnum;
import me.antileaf.example.utils.ExampleAudioMaster;
import me.antileaf.example.utils.ExampleHelper;
import me.antileaf.signature.interfaces.SignatureSubscriber;
import me.antileaf.signature.utils.SignatureHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

@SpireInitializer
public class SignatureExampleModCore implements
		EditCardsSubscriber,
		EditCharactersSubscriber,
		EditRelicsSubscriber,
		EditStringsSubscriber,
		AddAudioSubscriber,
		PostInitializeSubscriber,
		SignatureSubscriber {
	public static final String MOD_ID = "SignatureExampleMod";

	private static final Logger logger = LogManager.getLogger(SignatureExampleModCore.class.getName());

//	private static final java.awt.Color MAKI_IMPRESSION_COLOR_AWT = new java.awt.Color(0xDB6464);
	public static final Color MAKI_IMPRESSION_COLOR = new Color(0xDB6464FF); // 219, 100, 100
	public static final Color ALICE_IMPRESSION_COLOR = CardHelper.getColor(255,215,0);

	private static String get512Path(String s) {
		return ExampleHelper.getImgFilePath("512", s);
	}

	private static String get1024Path(String s) {
		return ExampleHelper.getImgFilePath("1024", s);
	}

	public SignatureExampleModCore() {
		BaseMod.subscribe(this);
		SignatureHelper.subscribe(this);

		BaseMod.addColor(
				CardColorEnum.MAKI_COLOR,
				MAKI_IMPRESSION_COLOR,
				MAKI_IMPRESSION_COLOR,
				MAKI_IMPRESSION_COLOR,
				MAKI_IMPRESSION_COLOR,
				MAKI_IMPRESSION_COLOR,
				MAKI_IMPRESSION_COLOR,
				MAKI_IMPRESSION_COLOR,
				get512Path("attack"),
				get512Path("skill"),
				get512Path("power"),
				get512Path("orb"),
				get1024Path("attack"),
				get1024Path("skill"),
				get1024Path("power"),
				get1024Path("orb"),
				ExampleHelper.getImgFilePath("ui", "energy_orb")
		);

		BaseMod.addColor(
				CardColorEnum.ALICE_MARGATROID_COLOR,
				ALICE_IMPRESSION_COLOR,
				ALICE_IMPRESSION_COLOR,
				ALICE_IMPRESSION_COLOR,
				ALICE_IMPRESSION_COLOR,
				ALICE_IMPRESSION_COLOR,
				ALICE_IMPRESSION_COLOR,
				ALICE_IMPRESSION_COLOR,
				get512Path("attack_alice"),
				get512Path("skill_alice"),
				get512Path("power_alice"),
				get512Path("orb_alice"),
				get1024Path("attack_alice"),
				get1024Path("skill_alice"),
				get1024Path("power_alice"),
				get1024Path("orb_alice"),
				ExampleHelper.getImgFilePath("ui", "energy_orb_alice")
		);
	}

	@SuppressWarnings("unused")
	public static void initialize() {
		new SignatureExampleModCore();
	}

	@Override
	public void receiveEditCards() {
		System.out.println(" --------------------------- ");

		new AutoAdd(SignatureExampleModCore.MOD_ID)
				.packageFilter("me.antileaf.example.cards.maki")
				.setDefaultSeen(true)
				.cards();

		new AutoAdd(SignatureExampleModCore.MOD_ID)
				.packageFilter("me.antileaf.example.cards.alice")
				.setDefaultSeen(true)
				.cards();

		System.out.println(" --------------------------- ");
	}

	@Override
	public void receiveEditCharacters() {
		BaseMod.addCharacter(
				new Maki("Maki"),
				ExampleHelper.getImgFilePath("charSelect", "maki_button"),
				ExampleHelper.getImgFilePath("charSelect", "maki_portrait"),
				PlayerEnum.BA_MAKI_PLAYER_CLASS
		);
	}

	@Override
	public void receiveEditRelics() {
		new AutoAdd(SignatureExampleModCore.MOD_ID)
				.packageFilter("me.antileaf.example.relics.maki")
				.any(CustomRelic.class, (info, relic) -> {
					logger.info("Adding relic: {}", relic.getClass().getSimpleName());
					BaseMod.addRelicToCustomPool(relic, CardColorEnum.MAKI_COLOR);
					UnlockTracker.markRelicAsSeen(relic.relicId);
				});
	}

	@Override
	public void receiveEditStrings() {
		String lang = ExampleHelper.getLangShort();

		BaseMod.loadCustomStringsFile(CharacterStrings.class,
				"SignatureExampleMod/localization/" + lang + "/character.json");

		BaseMod.loadCustomStringsFile(CardStrings.class,
				"SignatureExampleMod/localization/" + lang + "/cards.json");

		BaseMod.loadCustomStringsFile(RelicStrings.class,
				"SignatureExampleMod/localization/" + lang + "/relics.json");
	}

	@Override
	public void receiveAddAudio() {
		ExampleAudioMaster.init();
	}

	@Override
	public void receivePostInitialize() {
		SignatureHelper.register(Miracle.ID, new SignatureHelper.Info(
				ExampleHelper.getImgFilePath("signature", "Miracle_s"),
				ExampleHelper.getImgFilePath("signature", "Miracle_s_p"),
				card -> EverydayMomentsWithYou.signature.containsKey(card) &&
						EverydayMomentsWithYou.signature.get(card),
				EverydayMomentsWithYou.ID
		));

		SignatureHelper.addUnlockConditions("SignatureExampleMod/localization/" +
				ExampleHelper.getLangShort() + "/unlock.json");

		new AutoAdd(SignatureExampleModCore.MOD_ID)
				.packageFilter("me.antileaf.example.cards.alice")
				.any(AbstractCard.class, (info, card) -> {
					SignatureHelper.unlock(card.cardID, true);
				});

		SignatureHelper.unlock(PaintballLob.ID, true);
		SignatureHelper.unlock(Retro.ID, false);

		logger.info("If you want to disable debugging, you can call:");
		logger.info("SignatureHelper.noDebuggingPrefix(\"SignatureExampleMod:\")");
		logger.info("Or:");
		logger.info("SignatureHelper.noDebugging(Miracle.ID)");
	}

	@Override
	public void receiveOnSignatureUnlocked(String cardID, boolean unlock) {
		if (cardID.equals(Retro.ID))
			SignatureHelper.enable(Retro.ID, true);
	}
}
