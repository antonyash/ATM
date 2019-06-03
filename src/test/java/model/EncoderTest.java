package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {

  @Test
  void encodePin() {
    Encoder encoder = new Encoder(5);

    assertEquals(99072,encoder.encodePin(1337));
  }
}