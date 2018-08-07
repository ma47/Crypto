package DiffieHellman;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;

public class User {
	private int modSize = 1024;
	private BigInteger finalKeyA;
	private BigInteger finalKeyB;
	private BigInteger firstKeyA;
	private BigInteger firstKeyB;
	private Map<String, BigInteger> messageA = new HashMap<String, BigInteger>();
	private BigInteger valueB;

	
	
	public User() {}
	
	
	public Map<String,BigInteger> computeMessageAtoB() {	
		SecureRandom rand = new SecureRandom();
		
		// generating random prime numbers for base, modulo and secretkey values
		BigInteger base = BigInteger.probablePrime(modSize, rand);
		BigInteger modulo = BigInteger.probablePrime(modSize, rand);
		firstKeyA = BigInteger.probablePrime(modSize, rand);
		BigInteger valueA = base.modPow(firstKeyA, modulo); //calculating valueA with exponent in the modulo 
		
		//generating a message to send
		
		messageA.put("base", base);
		messageA.put("modulo", modulo);
		messageA.put("valueA", valueA);
		
		return messageA;
	}
	
	
	public BigInteger computeMessageBtoA(Map<String, BigInteger> map) {	
		SecureRandom rand = new SecureRandom();
		
		// generating random prime numbers 
		firstKeyB = BigInteger.probablePrime(modSize, rand);
		BigInteger valueB = map.get("base").modPow(firstKeyB, map.get("modulo")); //calculating valueB with exponent in the modulo that was received with the map 
		this.valueB = valueB;
		//generating a message to send
		return valueB;
	}
	
	public void ComputeKeyA(BigInteger valueB) { // calculates the finalKeyA that is known to B and A only from a value that was passed by a message from B 
		BigInteger a = valueB.modPow(firstKeyA, messageA.get("modulo"));
		this.finalKeyA = a;
	}
	
	public void ComputeKeyB(Map<String, BigInteger> map) { // calculates the finalKeyB that is known to B and A only with the values from A msg and his own secret key
		BigInteger b = map.get("valueA").modPow(firstKeyB, map.get("modulo"));
		this.finalKeyB = b;
	}
	
	
	public BigInteger getFinalKeyA() {
		return finalKeyA;
	}
	
	public BigInteger getFinalKeyB() {
		return finalKeyB;
	}
	
	public BigInteger getBaseA() {
		return messageA.get("base");
	}
	
	public BigInteger getModuloA() {
		return messageA.get("modulo");
	}
	
	public BigInteger getValueA() {
		return messageA.get("valueA");
	}
	public BigInteger getValueB() {
		return valueB;
	}

	public BigInteger getFirstKeyA() {
		return firstKeyA;
	}
	
	public BigInteger getFirstKeyB() {
		return firstKeyB;
	}
}
