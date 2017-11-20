import corejava.Console;
import excecao.MembroJaExistenteException;
import excecao.MembroNaoEncontradoException;
import excecao.TimeNaoEncontradoException;
import modelo.Membro;
import modelo.Time;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import servico.MembroAppService;
import servico.TimeAppService;
import util.Util;

import java.util.List;

public class PrincipalMembro {

    public static void main(String[] args) {
        String dataCriacao;
        String posicao;
        String nome;

        Time umTime;
        Membro umMembro;

        ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

        TimeAppService timeAppService =
                (TimeAppService) fabrica.getBean("timeAppService");
        MembroAppService membroAppService =
                (MembroAppService) fabrica.getBean("membroAppService");

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "O que voc� deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um membro de um time");
            System.out.println("2. Remover um membro");
            System.out.println("3. Recuperar todos os membros");
            System.out.println("4. Sair");

            int opcao = Console.readInt('\n' +
                    "Digite um n�mero entre 1 e 4:");

            switch (opcao) {
                case 1: {
                    long idProduto = Console.
                            readInt('\n' + "Informe o n�mero do time: ");

                    try {
                        umTime = timeAppService.recuperaUmTime(idProduto);
                    } catch (TimeNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    nome = Console.readLine('\n' +
                            "Informe o nome: ");
                    posicao = Console.readLine('\n' +
                            "Informe a posicao: ");
                    dataCriacao = Console.readLine(
                            "Informe a data de admissao no time: ");

                    umMembro = new Membro(nome, posicao, Util.strToCalendar(dataCriacao), umTime);

                    try {
                        membroAppService.inclui(umMembro);
                        System.out.println('\n' + "Membro adicionado com sucesso");
                    } catch (TimeNaoEncontradoException | MembroJaExistenteException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 2: {
                    int resposta = Console.readInt('\n' +
                            "Digite o n�mero do membro que voc� deseja remover: ");

                    try {
                        umMembro = membroAppService.recuperaUmMembro(resposta);
                    } catch (MembroNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    } catch (TimeNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "N�mero = " + umMembro.getId() +
                            "    Nome = " + umMembro.getNome() +
                            "    Posi��o = " + umMembro.getPosicao() +
                            "    Data de Adimiss�o = " + umMembro.getDataAdimissao());

                    String resp = Console.readLine('\n' +
                            "Confirma a remo��o do lance?");

                    if (resp.equals("s")) {
                        try {
                            membroAppService.exclui(umMembro);
                            System.out.println('\n' +
                                    "Membro removido com sucesso!");
                        } catch (MembroNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println('\n' +
                                "Membro n�o removido.");
                    }

                    break;
                }

                case 3: {
                    List<Membro> arrayMembros = membroAppService.recuperaMembros();

                    if (arrayMembros.size() == 0) {
                        System.out.println('\n' +
                                "Nao h� lances cadastrados.");
                        break;
                    }

                    System.out.println("");
                    for (Membro membro : arrayMembros) {
                        System.out.println('\n' +
                                "N�mero = " + membro.getId() +
                                "    Nome = " + membro.getNome() +
                                "    Posi��o = " + membro.getPosicao() +
                                "    Data de Adimiss�o = " + membro.getDataAdimissao());
                    }

                    break;
                }

                case 4: {
                    continua = false;
                    ((ConfigurableApplicationContext) fabrica).close();
                    break;
                }

                default:
                    System.out.println('\n' + "Op��o inv�lida!");
            }
        }
    }
}
