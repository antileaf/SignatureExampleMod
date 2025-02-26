package me.antileaf.example.cards.maki;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import me.antileaf.example.patches.enums.CardColorEnum;
import me.antileaf.example.utils.ExampleHelper;
import me.antileaf.signature.card.AbstractSignatureCard;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class EverydayMomentsWithYou extends AbstractSignatureCard {
	public static final String SIMPLE_NAME = EverydayMomentsWithYou.class.getSimpleName();
	public static final String ID = ExampleHelper.makeID(SIMPLE_NAME);
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;

	public static final WeakHashMap<AbstractCard, Boolean> signature = new WeakHashMap<>();

	public EverydayMomentsWithYou() {
		super(
				ID,
				cardStrings.NAME,
				ExampleHelper.getCardImgFilePath(SIMPLE_NAME),
				COST,
				cardStrings.DESCRIPTION,
				CardType.SKILL,
				CardColorEnum.MAKI_COLOR,
				CardRarity.RARE,
				CardTarget.NONE
		);

		this.cardsToPreview = new Miracle();
		signature.put(this.cardsToPreview, true);
		this.cardsToPreview.upgrade();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

	}

	@Override
	public AbstractCard makeCopy() {
		return new EverydayMomentsWithYou();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(UPGRADED_COST);
			this.initializeDescription();
		}
	}
}
