package DiffieHellman;

import java.math.BigInteger;
import java.util.Map;

public class DiffieHellmanTest {  //Test of the implementation Exercise II - 2. Part 1 and 2.
	public static void main(String[] args) {
		User A = new User();
		User B = new User();
		
		Map<String, BigInteger> msgA = A.computeMessageAtoB();
		BigInteger bMsg = B.computeMessageBtoA(msgA);
		A.ComputeKeyA(bMsg);
		B.ComputeKeyB(msgA);
		
		
		System.out.println("secretA = "  + A.getFirstKeyA());
		System.out.println("secretB = " + B.getFirstKeyB());
		System.out.println("msg1.modulus = "  + A.getModuloA());
		System.out.println("msg1.base = " + A.getBaseA());
		System.out.println("msg1.valueA = "  + A.getValueA());
		System.out.println("msg2.valueB = " + B.getValueB());
		System.out.println("keyA = "  + A.getFinalKeyA());
		System.out.println("keyB = " + B.getFinalKeyB());
		
	}
}
