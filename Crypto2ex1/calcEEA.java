import java.math.BigInteger;


public class calcEEA {

	static BigInteger s = new BigInteger("0");
	static BigInteger t = new BigInteger("0");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger x = new BigInteger("1572855870797393");
		BigInteger y = new BigInteger("630065648824575");
		/*BigInteger x = new BigInteger("15");
		BigInteger y = new BigInteger("7");*/
		BigInteger sign_x = new BigInteger("1"), sign_y = new BigInteger("1");
		
		BigInteger[] ans = new BigInteger[3];

		ans = ExtendedEuclid(x,y);
		
		if(x.signum()== 0){
			sign_x = new BigInteger("-1");
			x = x.abs();
		}       
		if(y.signum()== 0){
			sign_y = new BigInteger("-1");
			y = y.abs();
		}     
		s = sign_x.multiply(ans[1]);
		
		t = sign_y.multiply(ans[2]);
		
		System.out.println(ans[0] + "  = gcd(" + x.multiply(sign_x) + "," + y.multiply(sign_y)+")");
		System.out.println("d = " + ans[0]);
		System.out.println("s = " + s );
		System.out.println("t = " + t ); 
		BigInteger d = new BigInteger("0");
		d = ((sign_x.multiply(x)).multiply(s)).add((sign_y.multiply(y).multiply(t)));
		System.out.println(sign_x.multiply(x) +"*"+ s +" + " + sign_y.multiply(y)+"*"+ t + " = " + d);
		
	}
	public static BigInteger[] ExtendedEuclid(BigInteger x, BigInteger y){ 
		BigInteger[] ans = new BigInteger[3];
		BigInteger q;
		
		if (y.signum()== 0) {  
			ans[0] = x;
			ans[1] = new BigInteger("1");
			ans[2] = new BigInteger("0");
		} else{    
			q = x.divide(y);
			ans = ExtendedEuclid (y, x.mod(y));
			BigInteger temp = ans[1].subtract(ans[2].multiply(q));
			ans[1] = ans[2];
			ans[2] = temp;
		}
		return ans;
	}

}
