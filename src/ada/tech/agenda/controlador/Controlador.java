package ada.tech.agenda.controlador;

import ada.tech.agenda.exception.ContatoNaoEncontradoException;
import ada.tech.agenda.exception.IndiceContatoNaoEncontradoException;
import ada.tech.agenda.exception.NaoExisteContatoException;
import ada.tech.agenda.exception.TelefoneExistenteException;
import ada.tech.agenda.modelo.Contato;

public class Controlador {

    private Contato [] contatos;

    public Contato [] adicionarContato(Contato contato) throws Exception {
        try {
            int tamanhoAgenda = verificarTamanho();
            consultarTelefoneExistente(contato.getTelefone());
            Contato[] contatosAtualizados = new Contato[tamanhoAgenda + 1];
            contatosAtualizados = copiarContatos(contatos, contatosAtualizados);
            contatosAtualizados[contatos.length] = contato;
            contatos = contatosAtualizados;
            return contatos;
        } catch (NaoExisteContatoException e) {
            contatos = new Contato[1];
            contatos[0] = contato;
            return contatos;
        } catch (Exception e) {
            throw e;
        }

    }

    public Contato buscarContatoPorTelefone(String telefone) throws Exception {
        Contato contato = null;
        try {
            int indice = this.indiceContatoPorTelefone(telefone);
            return this.contatos[indice];
        } catch (Exception e) {
           throw e;
        }
    }

    public String removerContatoPorTelefone(String telefone) {
        int indiceContatosAtualizados = 0;

        try {
            int tamanhoAgenda = verificarTamanho();
            int indiceContatoPorTelefone = indiceContatoPorTelefone(telefone);
            Contato[] contatosAtualizados = new Contato[tamanhoAgenda-1];
            for(int i = 0; i < tamanhoAgenda; i++) {
                if(indiceContatoPorTelefone != i) {
                    contatosAtualizados[indiceContatosAtualizados] = contatos[i];
                    indiceContatosAtualizados++;
                }
            }
            contatos = contatosAtualizados;
            return "Contato removido com sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public int verificarTamanho() throws NaoExisteContatoException {
         if(contatos != null && contatos.length > 0) {
            return contatos.length;
         }
         throw new NaoExisteContatoException();
    }

    public String atualizarContatoPorTelefone(Contato contatoEditado, String telefone) {
        try {
            int indice = this.indiceContatoPorTelefone(telefone);
            consultarTelefoneExistente(contatoEditado.getTelefone(), indice);
            this.contatos[indice] = contatoEditado;
            return "Contato atualizado com sucesso";
        } catch (Exception e) {
            return "Nao foi possivel  editar o contato";
        }
    }

    private void consultarTelefoneExistente(String telefone) throws Exception {
        int indice = -1;
        try {
            indice = this.indiceContatoPorTelefone(telefone);
            if(indice != -1)
                throw new TelefoneExistenteException();
        } catch (TelefoneExistenteException e) {
            throw e;
        } catch (Exception e) {
            //throw e;
        }
    }

    private void consultarTelefoneExistente(String telefone, int indice) throws Exception {
        try {
            int indiceContato = this.indiceContatoPorTelefone(telefone);
            if(indiceContato != indice) {
                throw new TelefoneExistenteException();
            }
        } catch (TelefoneExistenteException e) {
            throw e;
        } catch (Exception e) {
            //throw e;
        }
    }

    private int indiceContatoPorTelefone(String telefone) throws ContatoNaoEncontradoException, NaoExisteContatoException, IndiceContatoNaoEncontradoException {
        int indice = -1;
        try {
            int tamanho = this.verificarTamanho();
            for (int i = 0; i < tamanho; i++) {
                Contato contato = this.contatos[i];
                if(telefone.equalsIgnoreCase(contato.getTelefone())) {
                    indice = i;
                    break;
                }
            }
            if(indice == -1) {
                throw new ContatoNaoEncontradoException();
            }
            return indice;
        } catch (Exception e) {
            throw e;
        }
    }

    private Contato [] copiarContatos(Contato [] contatos, Contato [] novosContatos) {
        for (int i = 0; i < contatos.length; i++) {
            novosContatos[i] = contatos[i];
        }
        return novosContatos;
    }

    public Contato[] getContatos() {
        return contatos;
    }

}
