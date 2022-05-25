package lab2;

/**
 * AtividadesComplementares
 * 
 * @author João Victor de Souza Lucena
 */

public class AtividadesComplementares {

  private int horasDeEstagio = 0;
  private int quantEstagios = 0;
  private int quantProjetos = 0;
  private double horasDeCurso = 0;
  private int mesesDeProjeto = 0;

  void adicionarEstagio(int horas) {
    if (quantEstagio > 9) {
      return;
    }
    horasDeEstagio += horas;
    quantEstagio += 1;
  }

  void adicionarProjeto(int meses) {
    if (quantProjeto > 16) {
      return;
    }
    mesesDeProjeto += meses;
    quantProjeto += 1;
  }

  void adicionarCurso(double horas) {
    horasDeCurso += horas;
  }

  int contaCreditos() {
    int creditosEstagio = horasDeEstagio / 300 * 5;
    int creditosProjeto = mesesDeProjeto / 3 * 2;
    int creditosCurso = horasDeCurso / 30;

  }

  String[] pegaAtividades() {
    return ["Estagio " + horasDeEstagio, "Projeto " + mesesDeProjeto, "Cursos " + horasDeCurso];
  }

}