import java.math.BigInteger;

public class linearEquation extends calcEEA{
	public static void main(String[] args) {
		
		//calcEEA inverse = new calcEEA();
		//solve linear equation where ax+b = 0 in Zn
		
		BigInteger a = new BigInteger("343254645645745645647687955345699987434576876432345665" +
				"79654234676796634378768434237897634345765879087764242354365767869780876543424");
		BigInteger b = new BigInteger("4529238420912791724362124239857322093583572346433245235" +
				"3464376432246757234546765745246457656354765878442547568543334677652352657235");
		
		BigInteger n = new BigInteger("6438080068035544392301298549614926991513861075340134329" +
				"18073439524138264842370630061369715394739134090922937332590384720397133335969" +
				"549256322620979036686633213903952966175107096769180017646161851573147596390153");
		
		System.out.println("Question 2a :");
		solveEquation(a,b,n);
		
		a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768"+
					"434237897634345765879087764242354365767869780876543424");
		b = new BigInteger("24243252873562935279236582385723952735639239823957923562835832582635283562852"+ 
					"252525256882909285959238420940257295265329820035324646");
		
		n = new BigInteger("34248723532593458235023583785345602939423526832829428589598243238758257023423"+ 
					"84876259232895263823795235732659632932938392985950720935732042930927056234852738"+
					"93582930285732889238492377364284728834632342522323422");
		
		System.out.println("\nQuestion 2b :");
		solveEquation(a,b,n);
		
	}
	//calc inverse of a if gcd = 1
	// subtract b, multiply by a^-1
	public static void solveEquation(BigInteger a,BigInteger b, BigInteger n){
		
		BigInteger x = new BigInteger("0");
		BigInteger zero = new BigInteger("0");
		BigInteger gcd = new BigInteger("0");
		BigInteger inverse = new BigInteger("0");
		BigInteger solved = new BigInteger("1");
		
		//calculate gcd
		gcd = a.gcd(n);
		System.out.println("gcd = " + gcd.toString());
		//can have an inverse only if = 1
		if (!gcd.equals(BigInteger.ONE)){
			System.out.println("NULL");
			
		}else{
			//calculate inverse
			inverse = a.modInverse(n);
			System.out.println("inverse = " + inverse.toString());
	
			//calculate x
			x = ((zero.subtract(b)).multiply(inverse));
			x = x.mod(n);
			System.out.println("x = " + x.toString());
			
			//check equation correct in range Zn	
			solved = ((a.multiply(x)).add(b));
			System.out.println("solved : "+solved.toString());
			solved = solved.mod(n);
			System.out.println("solved : "+solved.toString());	
		}	
	}	
}
