# Relatório de justificativas de escolha de design, dependências e melhorias no Servidor para melhor uso de dados

Design e dependências:
* *MVVM*. Separa bem as funcionalidades da tela com tratamento dos dados. Usado por ser de rápida e clara implementação, resultado é obtido com agilidade;
* *Retrofit*. Trata de requisições HTTP de forma confiável e assíncrono. Fácil implementação e ótima legibilidade;
* *Glide*. Trata cache de imagens, faz o bind direto com a view. Fácil implementação;
* *Stetho*. Perfeito para gerenciar as requisições feitas pelo aplicativo. Possui explorador de árvore de views. Fácil implementação;
* *Separação das classes/pacotes*. Separação feita nos moldes de "tela" para "implementação", é pensado primeiro em que pontos o usuário vai interagir, para posterior implementação de funcionalidade. Evita criação de classes desnecessárias ao longo da implementação, foca na experiência do usuário.

Melhorias no Servidor:
* *Desnormalização*. O uso de "ids" dentro do json que faz referência a outro objeto faz necessário o uso de mais requisições para coleta completa do dado requerido no primeiro momento. O melhor seria a desnormalização do banco, criando duplicidades entre os items que possuem referências um do outro, possibilitando assim em menos requisições coletar o dado completo.
* *Plural*. Chamadas que podem retornar mais de um item serem em formato plural. Exemplo: api/lanches, api/lanches/{id}
