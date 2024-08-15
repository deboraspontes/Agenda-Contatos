package ada.tech.agenda.modelo;

public class Contato {

    private int id;
    private String nome;
    private String sobreNome;
    private String telefone;
    private String email;

    public Contato(int id, String nome, String sobreNome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return """

                ############################
                ##### DADOS DO CONTATO #####
                ############################

                Nome: %s %s
                Telefone: %s
                E-mail: %s
                """.formatted(nome, sobreNome, telefone, email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
