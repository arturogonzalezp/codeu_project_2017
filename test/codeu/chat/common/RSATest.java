// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.chat.common;

import codeu.chat.util.EncryptionKey;
import codeu.chat.util.RSA;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class RSATest {

  private RSA rsa = new RSA();
  private EncryptionKey pubKey, secKey;

  @Before
  public void setup(){
    RSA.generateKeys(1024);
    pubKey = rsa.getPubKey();
    secKey = rsa.getSecKey();
  }

  //Make sure that when keys are cast into Strings, they remain the same when turned back
  @Test
  public void testValueConversionCongruence(){
    final BigInteger expected = secKey.getNumber();
    final BigInteger actual = RSA.valueToBigInteger(RSA.valueToString(secKey.getNumber()));

    assertEquals(expected, actual);
  }

  //Make sure messages remain the same after being parsed into a BigInteger and back
  @Test
  public void testMessageConversionCongruence(){
    final String expected = "The test works!";
    final String actual = RSA.messageToString(RSA.messageToBigInteger(expected));

    assertEquals(expected, actual);
  }

  //Test that the message is the same before and after encryption and decryption
  @Test
  public void testMessageIntegrity() {

    final String originalMessage = "hello";
    final BigInteger encodedMessage = RSA.messageToBigInteger(originalMessage);
    final BigInteger encryptedMessage = RSA.encrypt(encodedMessage, pubKey);
    final BigInteger decryptedMessage = RSA.decrypt(encryptedMessage, secKey);
    final String actual = RSA.messageToString(decryptedMessage);

    assertNotNull(actual);
    assertEquals(originalMessage, actual);
  }

}
