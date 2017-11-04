

import java.util.List;

import excecao.TimeNaoEncontradoException;
import modelo.Membro;
import modelo.Time;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servico.TimeAppService;
import corejava.Console;
import excecao.TimeNaoEncontradoException;

public class PrincipalTime {
    public static void main(String[] args) {
        String nome;
        String liga;

        Time umTime;

        ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

        TimeAppService timeAppService =
                (TimeAppService) fabrica.getBean("timeAppService");

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um time");
            System.out.println("2. Alterar um time");
            System.out.println("3. Remover um time");
            System.out.println("4. Listar um time e seus membros");
            System.out.println("5. Listar todos os times e seus membros");
            System.out.println("6. Sair");

            int opcao = Console.readInt('\n' +
                    "Digite um número entre 1 e 6:");

            switch (opcao) {
                case 1: {
                    nome = Console.readLine('\n' +
                            "Informe o nome do time: ");
                    liga = Console.readLine(
                            "Informe a liga do time: ");

                    Time time = new Time(nome, liga);

                    long numero = timeAppService.inclui(time);

                    System.out.println('\n' + "Time número " +
                            numero + " incluído com sucesso!");

                    break;
                }

                case 2: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número do time que você deseja alterar: ");

                    try {
                        umTime = timeAppService.recuperaUmTime(resposta);
                    } catch (TimeNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + umTime.getId() +
                            "    Nome = " + umTime.getNome() +
                            "    Liga = " + umTime.getLiga());

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1. Nome");
                    System.out.println("2. Liga");

                    int opcaoAlteracao = Console.readInt('\n' +
                            "Digite um número de 1 a 2:");

                    switch (opcaoAlteracao) {
                        case 1:
                            String novoNome = Console.
                                    readLine("Digite o novo nome: ");
                            umTime.setNome(novoNome);

                            try {
                                timeAppService.altera(umTime);

                                System.out.println('\n' +
                                        "Alteração de nome efetuada com sucesso!");
                            } catch (TimeNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            }

                            break;

                        case 2:
                            String novaLiga = Console.
                                    readLine("Digite a nova liga: ");
                            umTime.setLiga(novaLiga);

                            try {
                                timeAppService.altera(umTime);

                                System.out.println('\n' +
                                        "Alteração de descrição efetuada " +
                                        "com sucesso!");
                            } catch (TimeNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            }

                            break;

                        default:
                            System.out.println('\n' + "Opção inválida!");
                    }

                    break;
                }

                case 3: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número do time que você deseja remover: ");

                    try {
                        umTime = timeAppService.recuperaUmTime(resposta);
                    } catch (TimeNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + umTime.getId() +
                            "    Nome = " + umTime.getNome() +
                            "    Liga = " + umTime.getLiga());

                    String resp = Console.readLine('\n' +
                            "Confirma a remoção do time?");

                    if (resp.equals("s")) {
                        try {
                            timeAppService.exclui(umTime);
                            System.out.println('\n' + "Time removido com sucesso!");
                        } catch (TimeNaoEncontradoException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    } else {
                        System.out.println('\n' +
                                "Time não removido.");
                    }

                    break;
                }

                case 4: {
                    long numero = Console.readInt('\n' +
                            "Informe o número do time: ");
                    try {
                        umTime = timeAppService.recuperaUmTimeEMembros(numero);
                    } catch (TimeNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Id = " + umTime.getId() +
                            "  Nome = " + umTime.getNome() +
                            "  Liga = " + umTime.getLiga());

                    List<Membro> membros = umTime.getMembros();

                    for (Membro membro : membros) {
                        System.out.println('\n' +
                                "      Membro número = " + membro.getId() +
                                "  Nome = " + membro.getNome() +
                                "  Posicao = " + membro.getPosicao() +
                                "  Adimissao = " + membro.getDataAdimissao());
                    }

                    break;
                }

                case 5: {
                    List<Time> times = timeAppService.recuperaTimesEMembros();

                    if (times.size() != 0) {
                        System.out.println("");

                        for (Time time : times) {
                            System.out.println('\n' +
                                    "Time numero = " + time.getId() +
                                    "  Nome = " + time.getNome() +
                                    "  Descrição = " + time.getLiga());

                            List<Membro> membros = time.getMembros();

                            for (Membro membro : membros) {
                                System.out.println('\n' +
                                        "      Membro número = " + membro.getId() +
                                        "  Nome = " + membro.getNome() +
                                        "  Posicao = " + membro.getPosicao() +
                                        "  Adimissao = " + membro.getDataAdimissao());
                            }
                        }
                    } else {
                        System.out.println('\n' +
                                "Não há times cadastrados com esta descrição.");
                    }

                    break;
                }

                case 6: {
                    continua = false;
                    ((ConfigurableApplicationContext) fabrica).close();
                    break;
                }

                default:
                    System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
