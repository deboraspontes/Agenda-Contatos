package ada.tech.agenda.operacoes;

import ada.tech.agenda.modelo.Contato;
import ada.tech.agenda.utilitario.Util;

import java.util.Scanner;


public class AdicionarContato {
    public Contato[] executar(Contato[] contatos, Scanner entrada, int nextId) throws Exception {
        String telefone;
        boolean isValidPhone = false;

        // Loop até que o usuário insira um telefone válido (somente números)
        do {
            telefone = Util.ler(entrada, "Digite o telefone (somente números): ");
            if (telefone.matches("\\d+")) {
                isValidPhone = true;
            } else {
                Util.erro("O telefone deve conter apenas números. Tente novamente.");
            }
        } while (!isValidPhone);

        Util.contatoExiste(contatos, telefone);

        String primeiroNome = Util.ler(entrada, "Digite o nome do contato: ");
        if (primeiroNome.contains(" ")) {
            primeiroNome = primeiroNome.split(" ")[0];
        }

        String sobrenome = Util.ler(entrada, "Digite o sobrenome do contato: ");
        if (sobrenome.contains(" ")) {
            if(sobrenome.split(" ")[0].length() > 3){
                sobrenome = sobrenome.split(" ")[0];
            };
        }

        String email = Util.ler(entrada, "Digite o email: ");
        boolean isEmailValid = false;
        while(!isEmailValid) {
            if(email.contains("@")) {
                isEmailValid = true;
            } else{
                System.err.println("Formato de email inválido. Tente novamente!");
                email = Util.ler(entrada, "Digite novamente o email: ");
            }
        }

        Contato[] contatosAtualizados = new Contato[contatos.length + 1];
        for (int i = 0; i < contatos.length; i++) {
            contatosAtualizados[i] = contatos[i];
        }

        Contato novoContato = new Contato(nextId, primeiroNome, sobrenome, telefone, email);
        contatosAtualizados[contatos.length] = novoContato;
        Util.escrever("Contato adicionado com sucesso!");

        return contatosAtualizados;
    }
}