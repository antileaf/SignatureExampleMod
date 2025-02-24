package me.antileaf.example.relics.maki;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import me.antileaf.example.utils.ExampleHelper;

public class SmileBadge extends CustomRelic {
	public static final String SIMPLE_NAME = SmileBadge.class.getSimpleName();
	public static final String ID = ExampleHelper.makeID(SIMPLE_NAME);

	private static final String IMG = ExampleHelper.getRelicImgFilePath(SIMPLE_NAME);
	private static final String IMG_OTL = ExampleHelper.getRelicOutlineImgFilePath(SIMPLE_NAME);
	private static final String IMG_LARGE = ExampleHelper.getRelicLargeImgFilePath(SIMPLE_NAME);

	boolean firstTurn = false;

	public SmileBadge() {
		super(
				ID,
				ImageMaster.loadImage(IMG),
				ImageMaster.loadImage(IMG_OTL),
				RelicTier.STARTER,
				LandingSound.FLAT
		);
		
		this.largeImg = ImageMaster.loadImage(IMG_LARGE);
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}

	@Override
	public void atPreBattle() {
		this.firstTurn = true;
	}

	@Override
	public void atTurnStart() {
		if (this.firstTurn) {
			this.flash();
			this.addToTop(new GainEnergyAction(1));
			this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			this.firstTurn = false;
		}
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new SmileBadge();
	}
}
