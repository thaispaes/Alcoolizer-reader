package DAO;

public class Basica {
//Variaveis que serão usadas no decorrer da aplicação

    private static String nome, usuario, senha, email, id, valor, livro;

//Quando fizer login será dado um set nas variaveis(abaixo) dessa clase com os dados do usuário. 
    public static void SetUsuario(String nome, String usuario, String senha, String email) {
        Basica.nome = nome;
        Basica.usuario = usuario;
        Basica.senha = senha;
        Basica.email = email;

    }

    //Metodos que serão usados para setar valores em variáveis
    public static void SetValor(String valor) {
        Basica.valor = valor;
    }

    public static void SetLivro(String livro) {
        Basica.livro = livro;
    }

    public static void SetId(int id) {
        Basica.id = Integer.toString(id);
    }
//Metodos que serão usados para dar um retorno nos valores das variáveis.
    public static String GetValor() {
        return valor;
    }

    public static String GetNome() {
        return nome;
    }

    public static String GetUsuario() {
        return usuario;
    }

    public static String GetSenha() {
        return senha;
    }

    public static String GetEmail() {
        return email;
    }

    public static String GetId() {
        return id;
    }

   public static void ApagarTudo(){
       id="";
       email="";
       usuario="";
       senha="";
       nome="";
   }

}
