package src.CLI;

/**
 * MenuHolder
 */

public class MenuHolder {

  String[] mainMenu;
  String[] atvComplMenu;
  String[] descansoMenu;
  String[] disciplinaMenu;
  String[] registroTempoOnlineMenu;

  public MenuHolder() {
    mainMenu = new String[6];
    atvComplMenu = new String[6];
    descansoMenu = new String[6];
    registroTempoOnlineMenu = new String[6];
    disciplinaMenu = new String[7];
    //
    mainMenu[0] = "Bem-vindo ao Coisa!";
    mainMenu[1] = "";
    mainMenu[2] = "1 - Atividades complementares";
    mainMenu[3] = "2 - Descanso";
    mainMenu[4] = "3 - Disciplinas";
    mainMenu[5] = "4 - Registro Tempo Online";
    //
    atvComplMenu[0] = "Atividades complementares";
    atvComplMenu[1] = "";
    atvComplMenu[2] = "1 - Adicionar estágio";
    atvComplMenu[3] = "2 - Adicionar projeto";
    atvComplMenu[4] = "3 - Adicionar curso";
    atvComplMenu[5] = "4 - Verificar atividades complementares";
    //
    descansoMenu[0] = "Descanso";
    descansoMenu[1] = "";
    descansoMenu[2] = "1 - Definir quantidade de horas dedicadas ao descanso.";
    descansoMenu[3] = "2 - Definir quantidade de semanas em que as horas de descanso serão distribuídas.";
    descansoMenu[4] = "3 - Definir emoji :)";
    descansoMenu[5] = "4 - Ver estado atual.";
    //
    disciplinaMenu[0] = "Disciplinas";
    disciplinaMenu[1] = "";
    disciplinaMenu[2] = "1 - Adicionar disciplina";
    disciplinaMenu[3] = "2 - Adicionar notas";
    disciplinaMenu[4] = "3 - Definir peso das notas";
    disciplinaMenu[5] = "4 - Verificar disciplinas";
    disciplinaMenu[6] = "5 - Verificar situação de aprovação em disciplina";
  }

}
