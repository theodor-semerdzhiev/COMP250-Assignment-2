package assignment2;

public class EncryptionTester {

	 public static void main(String[] args) 
	    {
	        System.out.println("Hello world!");

	        String[] KavoshEncoded =
	                {
	                        "ZHCAET",
	                        "TWFVXH",
	                        "SVSGNA",
	                        "MCLPVF",
	                        "TUKFSE",
	                        "OLBLHH",
	                        "YUOALF",
	                        "YEENEA",
	                        "QCWURD",
	                        "GPYXBK",
	                        "FXVNMA",
	                        "IVJTXU",
	                        "TYWYIR",
	                        "KUHLQY",
	                        "WMYSCN",
	                        "TLQYPJ",
	                        "MSSSPH",
	                        "ARXLFB",
	                        "UPCNNS",
	                        "GVVCWP",
	                        "ZUKLLI",
	                        "MDQKWZ",
	                        "PDCFGZ",
	                        "YWMDDJ",
	                        "KIQLKV",
	                        "CMOHLU",
	                        "XNJUYI",
	                        "EZRPNJ",
	                        "ODRVIX",
	                        "ZVZZUP",
	                        "JVOHKR",
	                        "QBMDMK",
	                        "UWTNVC",
	                        "ZCRUYG",
	                        "MPXGDX",
	                        "DZYUQZ",
	                        "KEHYZN",
	                        "ZJEYFP",
	                        "JCRDAR",
	                        "KCMFGH",
	                        "IIKTUY",
	                        "HNHFKA",
	                        "GFLROS",
	                        "KKMNZD",
	                        "QBLXLR",
	                        "XZQTWX",
	                        "HBOOAP",
	                        "FYHLDD",
	                        "IJIUHF",
	                        "OOYXUZ",
	                        "ITMOGK",
	                        "JXMMFJ",
	                        "AXEZYI",
	                        "LTJDLV",
	                        "MHMVQA",
	                        "KHSBPZ",
	                        "MIRMFX",
	                        "KSQXTX",
	                        "QQNZXL",
	                        "LQCMAQ",
	                        "BMSIGH",
	                        "VEXIBU",
	                        "LWIQQY",
	                        "DECVYA",
	                        "SQRLWM",
	                        "FAWLME",
	                        "ORKFFC",
	                        "WLVBLA",
	                        "XQMGPI",
	                        "JSSEVM",
	                        "BFOECX",
	                        "GPBRRU",
	                        "UFAEHS",
	                        "REQCGN",
	                        "GZUVSX",
	                        "NVBFZA",
	                        "GFMLXS",
	                        "FIAYPW",
	                        "PMQBQD",
	                        "WNIMEC",
	                        "WWOHLM",
	                        "WGKZHT",
	                        "IHTBCY",
	                        "NMGJZD",
	                        "RQFOOC",
	                        "DVEIAW",
	                        "RLXZQT",
	                        "ZGIOGD",
	                        "XPGWYJ",
	                        "UWDBVW",
	                        "JWHBCR",
	                        "EDQLXB",
	                        "RMIURI",
	                        "PIDTEW",
	                        "WSDNDE",
	                        "AIBXNB",
	                        "VMUSZR",
	                        "THGRJY",
	                        "QMEKCY",
	                        "NGXWZU"
	                };
	        for (int index = 0; index < KavoshEncoded.length; index++) {
	            Deck deck = new Deck(13, 4);
	            Deck.gen.setSeed(index);
	            deck.shuffle();
	            SolitaireCipher cipher = new SolitaireCipher(deck);
	            System.out.println(cipher.decode(KavoshEncoded[index]));
	        }
	    }

}
