package fr.xephi.authme.security.crypts;

/**
 * Test for {@link Whirlpool}.
 */
public class WhirlpoolTest extends AbstractEncryptionMethodTest {

    public WhirlpoolTest() {
        super(new Whirlpool(),
            "74DFC2B27ACFA364DA55F93A5CAEE29CCAD3557247EDA238831B3E9BD931B01D77FE994E4F12B9D4CFA92A124461D2065197D8CF7F33FC88566DA2DB2A4D6EAE",  // password
            "819B4CBD26508E39EA76BFE102DCF2ACC87A446747CAB0BD88522B0822A724583E81B6A4BD2CE255DB694E530B659F47D434EEB50344A02F50B64414C9671583",  // PassWord1
            "71ECB0E5AEAB006F5336348076AA6A8E46075AEC9E010C7055BA1334B57746F2A9D8A8799BDD9B7EB4AB7544A59D25F469C8BCA2067508ACBA62A929260A1E17",  // &^%te$t?Pw@_
            "CBFEFB3DD7FC6D7F88E006955CFFE07758C74216A825A355D67AE352DA99D8F17FA00CB5CB6875A90BAB4992121E786F57E7B3602232002752925E4DC545C414"); // âË_3(íù*
    }

}
