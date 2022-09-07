package estacionaqui;

/**
 * Comentario
 * 
 * Essa classe não possui testes por ser muito simples.
 * 
 * @author João Victor de Souza Lucena
 */
public class Comentario {

  private String autor;
  private String mensagem;

  public Comentario(String autor, String mensagem) {
    this.autor = autor;
    this.mensagem = mensagem;
  }

  @Override
  public String toString() {
    return mensagem + " " + "(" + autor + ")";
  }
}