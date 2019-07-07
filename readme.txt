

***Méthodologie

Ce projet est composé de deux parties:

**Partie1: Générations des fichiers

-Les fichiers input (produits et transactions) sont générés par la classe :générerinput.GénérerInput.scala et les données sont stockés respèctivement dans: ./data/input/magasins et ./data/input/transactions.
-Pour générer les données, il faut spécifier les variables associés déclarés dans la classe générerinput.GénérerInput.scala qui sont initialisés à:

 nbr_transactions: Int = 50000
 nbr_magasins: Int = 500
 nbr_produit : Int = 30000
 qté_max : Int = 20
 prix_max = 50

**Partie2: Traitement des données

-Les données générés par la résolution des exercices 1 et 7 sont stockés respectivement dans ./data/Topvente_output et ./data/Topca_output.

-Concernant la question 1, les données ont été récupérées à partir du fichier transactions_<YYYYMMDD>.data. Ensuite il ya génération des fichiers output top_100_ventes_<ID_MAGASIN>_YYYYMMDD.data pour chaque magasin.
-La question 2 consiste à récupérer les prix des produits vendues dans les transactions durant 7 jours à partir des fichiers référentiels reference_prod-ID_MAGASIN_YYYYMMDD.data pour calculer après le chiffre d'affaire par magasin pendant cette durée. Finallement il y'aura génération des fichier output top_100_ca_<ID_MAGASIN>_YYYYMMDD-J7.data. 





***Environnement de développement

-scala 2.12.8
-maven 3.3.9
-intellij IDEA 2018 3.2
-java 1.8.0_152





***Exécution du code

-Générer un nouveau fichier.jar ou utiliser celui qui est déja généré dans CarrefourTest/classes/artifacts/CarrefourTest_master_jar/

-Exécuter la commande: java -Xmx512m -jar path_to_fichier.jar path_to_projectdirectory day

expl: java -Xmx512m -jar /home/Bureau/ComCarrefourTest/out/artifacts/Com_Carrefour_Test_jar/Com.Carrefour.Test.jar /home/Bureau/ComCarrefourTest/ "20180612"

-Remarques:
*day est la date où le code va générer les top_100 vente par magasin ainsi les top_100 CA par magasin pendant une semaine à partir de cette date.
*Les input dans ./data sont généré avec les conditions suivantes:

 nbr_transactions: Int = 50000
 nbr_magasins: Int = 50
 nbr_produit : Int = 3000
 qté_max : Int = 20
 prix_max = 50



