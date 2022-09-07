package prova1simulado.src.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import src.Dog;
import src.DogHost;

public class DogHostTest {
  private Dog d1;
  private Dog d2;
  private DogHost dh1;

  @BeforeEach
  public void preparaDog() {
    this.d1 = new Dog("Julie", "André", 100, false);
    this.d2 = new Dog("Toto", "Maty", 100, false);
    this.dh1 = new DogHost(3, "A", 500);

    dh1.adicionaDog("Julie", "André", 100);
    dh1.adicionaDog("Toto", "Maty", 100);
  }

  @Test
  void testConsultaValorHospedagem() {
    assertEquals(120, dh1.consultaValorHospedagem(d1, 3));
    assertEquals(0, dh1.consultaValorHospedagem(d2, 0));
  }
}
