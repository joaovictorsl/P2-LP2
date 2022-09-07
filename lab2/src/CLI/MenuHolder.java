package lab2.src.CLI;

import lab2.src.base.*;

/**
 * MenuHolder é uma classe valor. Carrega os arrays que representam os menus.
 */

public class MenuHolder {

  private String[] mainMenu;
  private String[] atvComplMenu;
  private String[] descansoMenu;
  private String[] disciplinaMenu;

  public MenuHolder() {
    mainMenu = new String[7];
    atvComplMenu = new String[7];
    descansoMenu = new String[7];
    disciplinaMenu = new String[5];
    //
    mainMenu[0] = "Bem-vindo ao Coisa!";
    mainMenu[1] = "";
    mainMenu[2] = "1 - Atividades complementares";
    mainMenu[3] = "2 - Descanso";
    mainMenu[4] = "3 - Disciplinas";
    mainMenu[5] = "4 - Registro Tempo Online";
    mainMenu[6] = "0 - Sair";
    //
    atvComplMenu[0] = "Atividades complementares";
    atvComplMenu[1] = "";
    atvComplMenu[2] = "1 - Adicionar estágio";
    atvComplMenu[3] = "2 - Adicionar projeto";
    atvComplMenu[4] = "3 - Adicionar curso";
    atvComplMenu[5] = "4 - Verificar atividades complementares";
    atvComplMenu[6] = "0 - Voltar";
    //
    descansoMenu[0] = "Descanso";
    descansoMenu[1] = "";
    descansoMenu[2] = "1 - Definir quantidade de horas dedicadas ao descanso.";
    descansoMenu[3] = "2 - Definir quantidade de semanas em que as horas de descanso serão distribuídas.";
    descansoMenu[4] = "3 - Definir emoji :)";
    descansoMenu[5] = "4 - Ver estado atual.";
    descansoMenu[6] = "0 - Voltar";
    //
    disciplinaMenu[0] = "Disciplinas";
    disciplinaMenu[1] = "";
    disciplinaMenu[2] = "1 - Adicionar disciplina";
    disciplinaMenu[3] = "2 - Gerenciar disciplinas";
    disciplinaMenu[4] = "0 - Voltar";
  }

  public String[] getMainMenu() {
    return mainMenu;
  }

  public String[] getRegistroTempoOnlineMenu(RegistroTempoOnline reg) {
    String[] registroTempoOnlineMenu = new String[6];

    registroTempoOnlineMenu[0] = "Registro de tempo online da disciplina " + reg.getNomeDaDisciplina();
    registroTempoOnlineMenu[1] = "";
    registroTempoOnlineMenu[2] = "1 - Verificar situação";
    registroTempoOnlineMenu[3] = "2 - Adicionar tempo online";
    registroTempoOnlineMenu[4] = "3 - Redefinir tempo esperado online";
    registroTempoOnlineMenu[5] = "0 - Voltar";

    return registroTempoOnlineMenu;
  }

  public String[] getAtvComplMenu() {
    return atvComplMenu;
  }

  public String[] getDescansoMenu() {
    return descansoMenu;
  }

  public String[] getDisciplinaMenu() {
    return disciplinaMenu;
  }

  public String[] getGerenciarDisciplinaMenu(Disciplina disciplina) {
    String[] gerenciarDisciplinaMenu = new String[7];

    gerenciarDisciplinaMenu[0] = disciplina.getNome();
    gerenciarDisciplinaMenu[1] = "";
    gerenciarDisciplinaMenu[2] = "1 - Verificar situação";
    gerenciarDisciplinaMenu[3] = "2 - Modificar nota";
    gerenciarDisciplinaMenu[4] = "3 - Modificar pesos";
    gerenciarDisciplinaMenu[5] = "4 - Adicionar horas dedicadas à disciplina";
    gerenciarDisciplinaMenu[6] = "0 - Voltar";

    return gerenciarDisciplinaMenu;
  }

}
