# Battleship

## Índice

- [Membros do Grupo](#membros-do-grupo )
- [Tipos de Navios](#tipos-de-navios)
- [Regras do Jogo](#regras-do-jogo)
- [Links](#links)

## Membros do Grupo 
Grupo TP05_LEI-6

| Curso | Número | Nome |
|---|---:|---|
| Engenharia Informática | 129884 | Joana Pires |
| Engenharia Informática | 129846 | Inês Marchante |
| Engenharia Informática | 129866 | Maria Inês Rodrigues |
| Engenharia Informática | 131589 | Gonçalo Gonçalves |

## Tipos de Navios 

| Batalha Naval | Descobrimentos | English | Dimensão | #Navios |
|---|---|---|---|---|
| Porta-aviões | Galeão | Galleon | 5 | 1 |
| Navio de 4 canhões | Fragata | Frigate | 4 | 1 |
| Navio de 3 canhões | Nau | Carrack | 3 | 2 |
| Navio de 2 canhões | Caravela | Caravel | 2 | 3 |
| Submarino | Barca | Barge | 1 | 4 |


Este projeto adapta as regras tradicionais da Batalha Naval a um cenário histórico baseado nos Descobrimentos. O jogo exige o posicionamento tático de uma frota de 11 navios, tanto de grande porte como é o caso Galeão como de menor dimensão, que ocupam um espaço no tabuleiro de jogo. O objetivo final mantém-se inalterável, consistindo em localizar e destruir a frota do oponente para garantir a vitória.

## Regras do Jogo
Cada jogador começa por construir **duas grelhas quadriculadas iguais de 10 × 10 quadrados**. Uma das grelhas representa **o seu mar**, enquanto a outra representa **o mar do adversário**. De seguida, cada jogador deverá **posicionar os seus navios na sua própria grelha**, sem que o adversário os veja. Os navios podem ser colocados **na horizontal ou na vertical**. O número de navios é **igual para cada jogador**. Os navios devem ser posicionados de forma a que **não possam tocar-se entre si**, nem horizontalmente, verticalmente ou diagonalmente. No entanto, **podem estar encostados às bordas da grelha**. Durante o jogo, os jogadores jogam **alternadamente**, tentando atingir os navios do adversário através da indicação de uma posição da grelha. O jogador deve indicar uma **linha e uma coluna**, correspondentes à posição que pretende atacar. Se a posição indicada corresponder a uma casa ocupada por um navio, o ataque é considerado **um acerto**. Caso contrário, é considerado **um tiro na água**. Um navio é considerado **afundado quando todas as casas que ocupa tiverem sido atingidas**. O jogo termina quando **um dos jogadores conseguir afundar todos os navios do adversário**. Esse jogador é declarado vencedor.

## Links

* **[Galeão](https://pt.wikipedia.org/wiki/Gale%C3%A3o)**: O maior navio da frota, ocupando 5 quadrados na grelha. Eram embarcações fortemente armadas usadas para guerra e comércio.
* **[Fragata](https://pt.wikipedia.org/wiki/Fragata)**: Navio de guerra rápido que ocupa 4 quadrados.
* **[Nau](https://pt.wikipedia.org/wiki/Nau)**: Embarcação de grande porte que ocupa 3 quadrados, fundamental nas antigas carreiras da Índia.
* **[Caravela](https://pt.wikipedia.org/wiki/Caravela)**: Ocupa 2 quadrados. Um navio rápido e de fácil manobra (famoso pelas velas latinas), ideal para as viagens de exploração costeira.
* **[Barca](https://pt.wikipedia.org/wiki/Barca)**: O navio de menor dimensão, ocupando apenas 1 quadrado, utilizado sobretudo para navegação costeira ou em águas rasas.

## Resposta à pergunta 6 do subcapítulo E

- **Como assumir a User Story:** Para me atribuir uma tarefa após a reunião diária de Scrum, devo aceder à secção Assignees na barra lateral direita e clicar em assign yourself. Caso a equipa esteja a usar um quadro (Projects), devo também mover este Issue para a coluna In Progress.
- **Como indicar que já foi implementada:** A forma correta é associar este Issue ao Pull Request da funcionalidade. Para isso, coloco uma palavra-chave na descrição do PR (por exemplo, Closes #numero_do_issue ou Resolves #numero_do_issue). Quando o PR for aprovado e feito o merge, o GitHub fecha este Issue automaticamente.
- **O que fazer se o Product Owner desistir da User Story:** O Issue não deve ser apagado. O procedimento correto é clicar na seta ao lado do botão de fechar no final desta página e selecionar a opção Close as not planned, adicionando um breve comentário a documentar o motivo da decisão do Product Owner.

