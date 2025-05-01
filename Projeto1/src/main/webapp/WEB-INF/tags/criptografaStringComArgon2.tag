<%-- 
    Document   : criptografaStringComArgon2
    Created on : 30 de abr. de 2025, 15:17:10
    Author     : luiz.souza
--%>

<%@tag description="transforma a string recebida em hash com argon2" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="senha"%>

<h2>${message}</h2>
/*Copy
Let’s look at an example that implements hashing using the Bouncy Castle library.

First, let’s create a method to generate a random salt for us:

private byte[] generateSalt16Byte() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
        
    return salt;
}
Copy
In the sample code above, we create a SecureRandom object, which is a class that provides a cryptographically strong random number generator. Next, we create a byte array of size 16 to store 16 bytes of data. Then, we invoke the nextBytes() method on secureRandom to generate the salt.

Finally, let’s hash the password “Baeldung“:


freestar
@Test
public void givenRawPasswordAndSalt_whenArgon2AlgorithmIsUsed_thenHashIsCorrect() {
    byte[] salt = generateSalt16Byte();
    String password = "Baeldung";
        
    int iterations = 2;
    int memLimit = 66536;
    int hashLength = 32;
    int parallelism = 1;
        
    Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
      .withVersion(Argon2Parameters.ARGON2_VERSION_13)
      .withIterations(iterations)
      .withMemoryAsKB(memLimit)
      .withParallelism(parallelism)
      .withSalt(salt);
        
    Argon2BytesGenerator generate = new Argon2BytesGenerator();
    generate.init(builder.build());
    byte[] result = new byte[hashLength];
    generate.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);
        
    Argon2BytesGenerator verifier = new Argon2BytesGenerator();
    verifier.init(builder.build());
    byte[] testHash = new byte[hashLength];
    verifier.generateBytes(password.getBytes(StandardCharsets.UTF_8), testHash, 0, testHash.length);
        
    assertTrue(Arrays.equals(result, testHash));
}