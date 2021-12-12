# Loup_Garou

C'est un projet mobile réasliser avec Java native "Android Studio" dans le cadre d'un projet ds pour l'année universitaire 2021/2022.

Ce projet a été réaliser par :
Mohamed Ali Meftah
Alaa Bouassida
Smaer Guesmi

Vous trouvez ci-join la maquette qui a été réaliser avec Figma.
Lien maquette :

Régle du jeu Loup Garou:
Loup Garoup est un jeu de société d'ambiance dans lequel chaque joueur incarne un villageois ou un loup-garou, et dont le but général est :
-Pour les villageois (dont certains ont des pouvoirs ou des particularités) : démasquer et tuer tous les loups-garous.
-Pour les loups-garous(Loups) : d'éliminer tous les villageois et ne pas se faire démasquer.
Les versions ultérieures introduisent des personnages aux caractères spécifiques, dont certains ont pour but de gagner seul et sont par conséquent en dehors des deux camps de base.

Pour cette application on a imbriqué 11 roles, dont 10 sont stables.
Les roles se génére automatiquement on relation avec le nombre de joueur.
Chaque joueur va avoir un role d'une maniére automatique par l'application.
Apprés avoir affécter les roles pour tous les joueurs, le narrateur (c'est perrsonne qui va guider le jeu avec l'aide de l'application) va annoncer le debut du jeu.

Chauqe nuit le narrateur doit réveiller les joueurs un par un avec l'ordre afficher dans l'application sont dire le nom du joueur.
Chaque joueur a un role différent de l'autre, est chauque role dépose des pouvoir différente, pour notre application on a 12 role comme suit:

Paladin: Chaque nuit il peut proteger soit lui soit un joueur au choix ( il peut pas proteger la meme personne deux nuit succsésivement), c'est une protection contre les sort qui tue, affecte, block ou qui annonce les roles.
Serber: C'est un role loup qui peut blocker les sort d'un joueur au choix chaque nuit.
Voyante: Chaque nuit elle peut voir un role d'un joueur au choix.
Enfant: Dans la premier nuit il doit choisir sont idole(un joueur au choix), si sont idole a été tuer ou éliminer l'enfant se transform a un loup.
Sorciére: Elle peut tuer et/ou viver un joueur une seul fois.
Chasseur: Si il a été éliminer dans la phase du vote, il doit choisir un joueur qui va etre éléminer avec lui.
LoupSimple: Il n'a aucune sort, il se revaille chaque nuit avec les autre pour tuer un joueur.
Cuipidant: Il peut choisir deux joueurs est le rendre amoureux c'est a dire que ces deux joueurs deviennent ni des loup ni des villagois, leur objectif est de garnier le jeu tous seuls.
Barbie: Dans la premiere nuit, elle donner un signe pour le narrateur, elle peut annoncer ce signe pour qu'elle puise choisir un joueur est le tuer.
Prist: Il peut choisir un joueur (une seule fois) est le faire annoncer sont role pour tous les autre joueurs.
Pere: C'es le pére des loup, il peut choisir un joueur (une seule fois) est lui affecte ( lui rendre un loup ).
Simpe: C'est une joueure simple qui ne dispose pas de sort.





