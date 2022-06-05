package Lab2_JOAO_VICTOR_DE_SOUZA_LUCENA;

import Lab2_JOAO_VICTOR_DE_SOUZA_LUCENA.src.base.*;

/**
 * CoisaBonus serve como uma classe de testes para mim. Através de asserts vejo
 * se minhas saídas se mantém as mesmas após alterações no código base.
 * 
 * @author João Victor de Souza Lucena
 */

public class CoisaBonus {

  // Gabarito do primeiro array de strings das atividadesComplementares.
  private static String[] atvComplGabarito1 = {
      "Estagio 600 4",
      "Projeto 7",
      "Cursos 59.0",
      "Creditos_Estagio 5",
      "Creditos_Projeto 4",
      "Creditos_Cursos  1",
  };

  // Gabarito do segundo array de strings das atividadesComplementares.
  private static String[] atvComplGabarito2 = {
      "Estagio 600 4",
      "Estagio 600 8",
      "Projeto 7",
      "Projeto 8",
      "Cursos 90.0",
      "Creditos_Estagio 15",
      "Creditos_Projeto 8",
      "Creditos_Cursos  3",
  };

  /**
   * Main roda os testes.
   */
  public static void main(String[] args) {
    descanso();
    registroOnline();
    disciplina();
    atividadesComplementares();
  }

  /**
   * Verifica integridade da classe Descanso.
   */
  public static void descanso() {
    Descanso descanso = new Descanso();
    descanso.definirEmoji(":P");
    assert descanso.getStatusGeral().equals("cansado - :P");
    descanso.defineHorasDescanso(30);
    descanso.defineNumeroSemanas(1);
    assert descanso.getStatusGeral().equals("descansado");
    descanso.defineHorasDescanso(26);
    descanso.defineNumeroSemanas(2);
    assert descanso.getStatusGeral().equals("cansado");
    descanso.defineHorasDescanso(26);
    descanso.defineNumeroSemanas(1);
    descanso.definirEmoji(">:O");
    assert descanso.getStatusGeral().equals("descansado - >:O");
  }

  /**
   * Verifica integridade da classe RegistroTempoOnline.
   */
  private static void registroOnline() {
    RegistroTempoOnline tempoLP2 = new RegistroTempoOnline("LP2", 30);
    tempoLP2.adicionaTempoOnline(10);
    assert tempoLP2.atingiuMetaTempoOnline() == false;
    tempoLP2.adicionaTempoOnline(10);
    tempoLP2.adicionaTempoOnline(10);
    assert tempoLP2.atingiuMetaTempoOnline();
    tempoLP2.adicionaTempoOnline(2);
    assert tempoLP2.atingiuMetaTempoOnline();
    assert tempoLP2.toString().equals("LP2 32/30");
    RegistroTempoOnline tempoP2 = new RegistroTempoOnline("P2");
    assert tempoP2.toString().equals("P2 0/120");
  }

  /**
   * Verifica integridade da classe Disciplina.
   */
  private static void disciplina() {
    Disciplina[] casos = new Disciplina[3];
    casos[0] = new Disciplina("PROGRAMACAO 2");
    casos[1] = new Disciplina("PROGRAMACAO 2", 2);
    casos[2] = new Disciplina("PROGRAMACAO 2", 6, new int[] { 1, 2, 3, 4, 5, 6 });
    String[] argumentos = new String[] {
        "4$5 6 7$10",
        "8$5$10",
        "16$10 10 10 5 5.6$10"
    };
    String[] gabaritos = new String[] {
        "false$true$PROGRAMACAO 2 4 7.0 [5.0, 6.0, 7.0, 10.0]",
        "false$true$PROGRAMACAO 2 8 7.5 [5.0, 10.0]",
        "false$true$PROGRAMACAO 2 16 8.0 [10.0, 10.0, 10.0, 5.0, 5.6, 10.0]"
    };

    for (int i = 0; i < casos.length; i++) {
      Disciplina disciplina = casos[i];
      String[] args = argumentos[i].split("\\$");
      String[] gabs = gabaritos[i].split("\\$");

      disciplina.cadastraHoras(Integer.parseInt(args[0]));

      String[] notas = args[1].split(" ");
      int j = 0;

      for (; j < notas.length; j++) {
        disciplina.cadastraNota(j + 1, Double.parseDouble(notas[j]));
      }
      assert disciplina.aprovado() == Boolean.parseBoolean(gabs[0]);
      disciplina.cadastraNota(j + 1, Double.parseDouble(args[2]));
      assert disciplina.aprovado() == Boolean.parseBoolean(gabs[1]);
      assert disciplina.toString().equals(gabs[2]);
    }
  }

  /**
   * Verifica integridade da classe AtividadesComplementares.
   */
  private static void atividadesComplementares() {
    AtividadesComplementares minhasAtividades = new AtividadesComplementares();
    int horasEstagio = 600;
    int mesesProjeto = 7;
    double horasCurso = 59;

    minhasAtividades.adicionarEstagio(horasEstagio);
    minhasAtividades.adicionarProjeto(mesesProjeto);
    minhasAtividades.adicionarCurso(horasCurso);

    String[] atividades = minhasAtividades.pegaAtividades();

    for (int i = 0; i < atividades.length; i++) {
      assert atividades[i].equals(atvComplGabarito1[i]);
    }

    assert minhasAtividades.contaCreditos() == 10;

    int horasOutroEstagio = 600;
    double horasOutroCurso = 31;
    int mesesOutroProjeto = 8;

    minhasAtividades.adicionarEstagio(horasOutroEstagio, 8);
    minhasAtividades.adicionarProjeto(mesesOutroProjeto);
    minhasAtividades.adicionarCurso(horasOutroCurso);

    atividades = minhasAtividades.pegaAtividades();
    for (int i = 0; i < atividades.length; i++) {
      assert atividades[i].equals(atvComplGabarito2[i]);
    }
    assert minhasAtividades.contaCreditos() == 26;

  }

}
