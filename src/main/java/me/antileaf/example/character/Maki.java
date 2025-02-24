package me.antileaf.example.character;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import me.antileaf.example.SignatureExampleModCore;
import me.antileaf.example.cards.alice.*;
import me.antileaf.example.cards.maki.EverydayMomentsWithYou;
import me.antileaf.example.cards.maki.PaintballLob;
import me.antileaf.example.cards.maki.Retro;
import me.antileaf.example.patches.enums.CardColorEnum;
import me.antileaf.example.patches.enums.PlayerEnum;
import me.antileaf.example.relics.maki.SmileBadge;
import me.antileaf.example.utils.ExampleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Maki extends CustomPlayer {
	private static final Logger logger = LogManager.getLogger(Maki.class.getName());

	public static final String SIMPLE_NAME = Maki.class.getSimpleName();
	public static final String ID = ExampleHelper.makeID(SIMPLE_NAME);

	private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);

	private static final String SHOULDER_2 = ExampleHelper.getImgFilePath("char", "shoulder");
	private static final String SHOULDER_1 = ExampleHelper.getImgFilePath("char", "shoulder");
	private static final String CORPSE = ExampleHelper.getImgFilePath("char", "maki");
	private static final String[] ORB_TEXTURES = {
			"SignatureExampleMod/img/ui/EPanel/layer5.png",
			"SignatureExampleMod/img/ui/EPanel/layer4.png",
			"SignatureExampleMod/img/ui/EPanel/layer3.png",
			"SignatureExampleMod/img/ui/EPanel/layer2.png",
			"SignatureExampleMod/img/ui/EPanel/layer1.png",
			"SignatureExampleMod/img/ui/EPanel/layer0.png",
			"SignatureExampleMod/img/ui/EPanel/layer5d.png",
			"SignatureExampleMod/img/ui/EPanel/layer4d.png",
			"SignatureExampleMod/img/ui/EPanel/layer3d.png",
			"SignatureExampleMod/img/ui/EPanel/layer2d.png",
			"SignatureExampleMod/img/ui/EPanel/layer1d.png"
	};
	private static final String ORB_VFX = "SignatureExampleMod/img/ui/energyVFX.png";
	private static final float[] LAYER_SPEED =
			{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

	public Maki(String name) {
		super(name, PlayerEnum.BA_MAKI_PLAYER_CLASS, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);

		this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

		this.initializeClass(
				ExampleHelper.getImgFilePath("char", "maki"),
				SHOULDER_2,
				SHOULDER_1,
				CORPSE,
				this.getLoadout(),
				20.0F, -10.0F, 220.0F, 290.0F,
				new EnergyManager(3)
		);
	}

	@Override
	public ArrayList<String> getStartingDeck() { // 初始卡组
		ArrayList<String> ret = new ArrayList<>();

		ret.add(PaintballLob.ID);
		ret.add(Retro.ID);
		ret.add(EverydayMomentsWithYou.ID);

		ret.add(SPDoll.ID);
		ret.add(DollCremation.ID);
		ret.add(DollActivation.ID);
		ret.add(DollMiraCeti.ID);
		ret.add(IllusoryMoon.ID);
		ret.add(SurpriseSpring.ID);
		ret.add(CollapsingWorlds.ID);
		ret.add(PrincessForm.ID);

		return ret;
	}

	@Override
	public ArrayList<String> getStartingRelics() { // 初始遗物
		ArrayList<String> ret = new ArrayList<>();
		ret.add(SmileBadge.ID);
		return ret;
	}

	private static final int STARTING_HP = 68;
	private static final int MAX_HP = 68;
	private static final int STARTING_GOLD = 99;
	private static final int HAND_SIZE = 5;
	private static final int ASCENSION_MAX_HP_LOSS = 6;

	@Override
	public CharSelectInfo getLoadout() {
		return new CharSelectInfo(
				characterStrings.NAMES[0],
				characterStrings.TEXT[0],
				STARTING_HP,
				MAX_HP,
				3,
				STARTING_GOLD,
				HAND_SIZE,
				this,
				this.getStartingRelics(),
				this.getStartingDeck(),
				false
		);
	}

	@Override
	public AbstractCard.CardColor getCardColor() {
		return CardColorEnum.MAKI_COLOR;
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		return new PaintballLob();
	}

	@Override
	public String getTitle(PlayerClass playerClass) { // 称号
		return characterStrings.NAMES[1];
	}

	@Override
	public Color getCardTrailColor() {
		return SignatureExampleModCore.MAKI_IMPRESSION_COLOR.cpy();
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return ASCENSION_MAX_HP_LOSS;
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontBlue;
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.play("SignatureExampleMod:CHAR_SELECT" + MathUtils.random(1, 2));
		CardCrawlGame.screenShake.shake(
				ScreenShake.ShakeIntensity.LOW,
				ScreenShake.ShakeDur.SHORT,
				false
		);
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "SignatureExampleMod:CHAR_SELECT" + MathUtils.random(1, 2);
	}

	@Override
	public String getLocalizedCharacterName() {
		return characterStrings.NAMES[0];
	}

	@Override
	public AbstractPlayer newInstance() {
		return new Maki(this.name);
	}

	@Override
	public String getVampireText() {
		return Vampires.DESCRIPTIONS[1]; // 加入我们，姐妹
	}

	@Override
	public Color getCardRenderColor() {
		return SignatureExampleModCore.MAKI_IMPRESSION_COLOR.cpy();
	}

	@Override
	public Color getSlashAttackColor() {
		return SignatureExampleModCore.MAKI_IMPRESSION_COLOR.cpy();
	}

	@Override
	public AttackEffect[] getSpireHeartSlashEffect() {
		return new AttackEffect[]{
				AttackEffect.SLASH_HEAVY,
				AttackEffect.BLUNT_LIGHT,
				AttackEffect.SLASH_DIAGONAL,
				AttackEffect.SLASH_HEAVY,
				AttackEffect.BLUNT_LIGHT,
				AttackEffect.SLASH_DIAGONAL
		};
	}

	@Override
	public String getSpireHeartText() {
		return characterStrings.TEXT[1];
	}
}
