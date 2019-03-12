package handlers;

//https://aesencryption.net/
	
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static String decryptedMsg;
	private static String encryptedMsg;

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			//System.out.println(key.length);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			//System.out.println(key.length);
			//System.out.println(new String(key, "UTF-8"));
			secretKey = new SecretKeySpec(key, "AES");
		} catch (Exception e) {e.printStackTrace();}
	}

	public static String getDecryptedMsg() {
		return decryptedMsg;
	}

	public static void setDecryptedMsg(String message) {
		AES.decryptedMsg = message;
	}

	public static String getEncryptedMsg() {
		return encryptedMsg;
	}

	public static void setEncryptedMsg(String message) {
		AES.encryptedMsg = message;
	}

	public static String encrypt(String message) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			setEncryptedMsg(Base64.encodeBase64String(cipher.doFinal(message.getBytes("UTF-8"))));
		} catch (Exception e) {System.out.println("Error while encrypting: " + e.toString());}
		return null;
	}

	public static String decrypt(String message) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			setDecryptedMsg(new String(cipher.doFinal(Base64.decodeBase64(message))));
		} catch (Exception e) {System.out.println("Error while decrypting: " + e.toString());}
		return null;
	}

	public static void main(String args[]) {
		String message = "sample";
		final String key= "test";
		
		AES.setKey(key);
		AES.encrypt(message.trim());

		System.out.println("String to Encrypt: " + message);
		System.out.println("Encrypted: " + AES.getEncryptedMsg());

		message = AES.getEncryptedMsg();
		AES.decrypt(message.trim());

		System.out.println("String To Decrypt : " + message);
		System.out.println("Decrypted : " + AES.getDecryptedMsg());

	}

}
