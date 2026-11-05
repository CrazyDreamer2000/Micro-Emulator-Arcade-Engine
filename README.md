# Micro-Emulator-Arcade-Engine
Émulateur matériel modulaire écrit en Java (L3 Sorbonne Université). Gère un bus d'adressage (AddressDecoder), un CPU gérant des cycles d'instructions bas niveau, et des alarmes optimisées en temps constant O(1). Incorpore un bonus fonctionnel émulant la borne d'arcade originale Asteroids (1979) avec interface graphique JavaFX.

##  Introduction et Objectifs du Projet

Ce projet a été réalisé dans le cadre de l'UE **LU3IN002 - Programmation par Objets (L3 Informatique, Sorbonne Université)**. 

### Le But
L'objectif central est de concevoir un **émulateur de système matériel générique et modulaire** entièrement écrit en Java. Plutôt que de coder une machine figée, le projet applique des principes de conception orientés objets rigoureux afin de créer des briques logicielles indépendantes et réutilisables (processeur, bus d'adressage, mémoires, périphériques). Ces composants s'assemblent pour simuler le comportement de vrais circuits électroniques interconnectés.

### La Problématique
Un émulateur doit reproduire fidèlement l'exécution d'un logiciel conçu pour un matériel d'origine. Cela implique de résoudre plusieurs défis d'architecture logicielle :
1.  **Le cadençage temporel :** Comment synchroniser l'exécution d'un processeur et le rafraîchissement d'un écran alors qu'ils n'avancent pas à la même vitesse ?
2.  **Le routage des données :** Comment un CPU peut-il lire ou écrire au bon endroit en utilisant un seul et unique bus de communication partagé entre de la RAM, de la ROM et des composants graphiques ?
3.  **La gestion des interruptions :** Comment planifier des actions matérielles périodiques (comme l'affichage ou des alarmes) de manière performante ?

---

## 🛠️ Architecture Technique Complétée

Le système émulé est architecturé autour de trois grands sous-systèmes interconnectés :

### 1. Gestion du Bus et de la Mémoire (`pobj.tme6.memory`)
*   **`RAM` & `ROM`** : Modélisation des espaces mémoires volatiles (lecture/écriture) et permanents (lecture seule). La ROM intègre une copie défensive des données à sa création pour empêcher toute altération externe.
*   **`AddressMask`** : Implantation d'un masquage binaire (`& bit-à-bit`) pour gérer les accès miroirs lorsque l'espace d'adressage du processeur dépasse la capacité physique de la mémoire réelle.
*   **`AddressDecoder` & `MemorySlot`** : Simulation du bus d'adressage matériel. Le décodeur fragmente l'espace d'adressage global et redirige les requêtes du CPU vers le bon composant physique en convertissant les adresses absolues en adresses relatives.
*   **`ICopyableMemory`** : Extension permettant la duplication récursive profonde (*deep copy*) du décodeur pour simuler des sauvegardes d'état à chaud (*savestates*).

### 2. Jeu d'Instructions et Décodage CPU (`pobj.tme6.cpu`)
*   **`CPUState`** : Registre d'état gérant l'accumulateur `A`, le compteur de programme `PC` et la méthode d'extraction synchrone `fetch()`.
*   **Décodage des Opcodes** : Isolation de chaque instruction via le patron de conception Commande (interface `IOpCode<T>`) pour décoder et exécuter le jeu d'instructions bas niveau :
    *   `SET` (0) : Charge une valeur dans l'accumulateur.
    *   `ADD` (1) : Additionne une valeur à l'accumulateur.
    *   `LOAD` (2) : Charge le contenu d'une adresse mémoire dans l'accumulateur.
    *   `STORE` (3) : Stocke le contenu de l'accumulateur à une adresse mémoire.
    *   `JUMP` (4) : Saut conditionnel si `A > 0` pour la gestion des boucles.
*   **Gestion des Cycles** : Simulation du temps d'exécution (2 cycles par instruction lue, 1 cycle pour l'instruction factice `NOP`).

### 3. Périphériques et Horloges (`pobj.tme6.device` & `pobj.tme6`)
*   **`EmulatorLoop`** : Boucle principale d'émulation indépendante de la machine. Elle cadence le système en mesurant les cycles consommés par le CPU pour les distribuer aux périphériques sous forme d'unités de temps écoulées (`tick`).
*   **`Screen`** : Périphérique hybride agissant à la fois comme une mémoire vidéo mappée (VRAM de 10 mots) et comme un périphérique périodique chargé de restituer l'affichage console toutes les 100 unités de temps.
*   **`Clock` & `Alarm`** : Gestionnaire d'alarmes temporelles asynchrones. Pour optimiser les performances, les alarmes sont triées par ordre décroissant (ordre anti-chronologique) grâce à l'interface `Comparable`, permettant à la méthode `tick` de vérifier et dépiler les alertes passées en temps constant $O(1)$ à la fin de la liste.

---

##  Partie Bonus : Émulation Réaliste de la Borne Asteroids (1979)

Pour valider la robustesse et la modularité de l'architecture, les briques mémoires et la boucle d'émulation ont été réutilisées pour faire tourner une véritable machine commerciale de salon : le jeu d'arcade **Asteroids (Atari, 1979)**.
*   **Émulation du CPU MOS 6502 (1975)** connecté sur notre décodeur d'adresses personnalisé.
*   **Simulation de l'Atari DVG (Digital Vector Generator)** : Redirection des signaux de la VRAM vers un moteur de rendu vectoriel 2D développé avec **JavaFX**.
*   **Résultat** : Chargement et exécution des fichiers binaires (ROMs) d'origine, affichage des astéroïdes, gestion du focus de la fenêtre et écoute des contrôles clavier d'époque.

---

##  Compétences et Technologies Validées
*   **Langage** : Java (Concepts avancés : Généricité, Héritage, Encapsulation stricte, Interfaces multiples).
*   **Conception** : Architecture découplée, programmation événementielle, gestion de structures de données triées (`Collections.sort`).
*   **Graphismes & Modules** : Configuration et intégration de JavaFX.
*   **Tests** : Validation rigoureuse du comportement de l'architecture via une suite de tests unitaires automatisés (JUnit).
