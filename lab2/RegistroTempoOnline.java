package lab2;

/**
 * RegistroTempoOnline
 * 
 * @author João Victor de Souza Lucena
 */

public class RegistroTempoOnline {

  private String nomeDaDisciplina;
  private int tempoGastoOnline = 0;
  private int tempoEsperadoOnline = 120;

  public RegistroTempoOnline(String nomeDaDisciplina) {
    this.nomeDaDisciplina = nomeDaDisciplina;
  }

  public RegistroTempoOnline(String nomeDaDisciplina, int tempoEsperadoOnline) {
    this.nomeDaDisciplina = nomeDaDisciplina;
    this.tempoEsperadoOnline = tempoEsperadoOnline;
  }

  public void adicionaTempoOnline(int tempoParaAdicionar) {
    tempoGastoOnline += tempoParaAdicionar;
  }

  public boolean atingiuMetaTempoOnline() {
    if (tempoGastoOnline >= tempoEsperadoOnline)
      return true;
    return false;
  }

  public String toString() {
    return nomeDaDisciplina + " " + tempoGastoOnline + "/" + tempoEsperadoOnline;
  }
}
