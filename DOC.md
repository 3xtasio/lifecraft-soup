NOTE : il y a eu de nombreuse modification.


---

<h1 id="pvpsoup---lifecraft-documentation">PvPSoup - Lifecraft Documentation</h1>
<p>Le PvPSoup Lifecraft est développé en Java et le stockage de donnée se fait sous MySQL et HashMap.<br>
Mais le plugin utilise des dépendances pour compléter l’expérience de jeux, il vous faudra :</p>
<ul>
<li>SmartInvs</li>
<li>LuckPerms</li>
</ul>
<h1 id="système">Système</h1>
<p>Le système se base sur une économie interne (les crédits) avec des grades (ranks) et prestiges.<br>
Les kits sont disponibles en fonction du ranks, c’est à dire que le grade Novice (Rank = 0) n’a accès qu’aux kits de base. Tandis que le grade Chevalier (Rank = 4) aura accès à tout les kits <strong>précédents</strong> le grade Chevalier.</p>
<p>Vous pourrez voir la notation des grades ci-dessous :<br>
<img src="https://image.prntscr.com/image/JscPXKytQti4ue2swnvmqg.png" alt="enter image description here"></p>
<p>Vous pourrez voir la notation des prestiges ci-dessous :<br>
<img src="https://image.prntscr.com/image/nI2K0yuEQkKNZ8dSCYiVvg.png" alt=""><br>
(Erreur, ID 10 = P10)</p>
<h2 id="commandes">Commandes</h2>
<p>Un grand nombre de commandes permettent de gérer le plugin. Vous pourrez voir la description ainsi que leurs usages ci-dessous :<br>
| Commandes |  Description | Usage | Permission |</p>
<ul>
<li><code>coins</code> : Affiche/gère la monnaie du serveur | Usage : /coins [add|remove|none] [Montant] {Joueur} | permission : lcsoup.credit<br>
Par défaut : OP</li>
<li><code>rank</code> : Affiche/gère les grades du serveur | Usage : /rank [set|info|list|help] {Joueur} [Rank] | permission : lcsoup.rank<br>
Par défaut : OP</li>
<li><code>prestige</code> : Affiche/gère les prestiges du serveur | Usage : /prestiges [set|info|list|help] {Joueur} [Prestige] | permission : lcsoup.prestige<br>
Par défaut : OP</li>
<li><code>soup</code> : Rempli l’inventaire de soupe | Usage : /soup | Coût : 20$<br>
Par défaut : tout le monde</li>
<li><code>autosoup</code> : Active l’autosoup | Usage : /autosoup [true|false] | Permission: lcsoup.autosoup<br>
Par défaut : OP</li>
<li><code>menu</code> : Ouvre le menu des kits | Usage : /menu | Condition : être dans le monde “spawnsoup”<br>
Par défaut : Tout le monde</li>
<li><code>stats</code> : Affiche les stats | Usage : /stats [player|none] | Condition : être en ligne<br>
Par défaut : Tout le monde</li>
<li><code>rankup</code> : Passe le grade d’un joueur | Usage : /rankup | Condition : avoir la monnaie requise<br>
Par défaut : Tout le monde</li>
<li><code>leaderboard</code> : Affiche les meilleurs stats | Usage : /leaderboard [mort|kills]<br>
Par défaut : Tout le monde</li>
</ul>
<h2 id="informations-supplémentaire">Informations supplémentaire</h2>
<ul>
<li>Les prestiges vont jusqu’à 10 max pour les joueurs, j’ai ajouté jusqu’au prestige 20 pour le staff (useless mais bon)</li>
<li>Les données sont envoyé/récupéré via MySQL, pensez à config le fichier .yml</li>
<li>Le Spawn, les TP sont prédéfinis par moi, me contacter si ajouts</li>
<li>Tout les données sont modifiables directement dans la BDD, aucun problème si modification <strong>mais</strong><br>
<strong>Ne pas dépasser le RANK 9 et le PRESTIGE 20</strong> dans la gestion MySQL.</li>
<li>Pour LuckPerms, le plugin récupère le préfix du grade, il faut donc ajouter un espace après le grade<br>
Exemple : <code>§bVIP_</code> (_ = espace)</li>
</ul>

