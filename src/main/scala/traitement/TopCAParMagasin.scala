package traitement

import genererOutput.GenererTopCaParMagasin
import gestionInput.LectureTransaction


class TopCAParMagasin(myPath:String, transactionDate:String) {

  val transaction: LectureTransaction = new LectureTransaction(myPath,transactionDate,7)
  val genererFichier: GenererTopCaParMagasin = new GenererTopCaParMagasin(myPath)

  /**
    * Calculer les Top 100 ca des produits dans tous les magasins pendant 7 jours
    */
  def generateOutput() :Unit = {
    transaction.result.toList.map(x => (x(1).split("T")(0), x(2), x(3), x(4).toDouble)).groupBy(_._1).values.toList.map { day => treatByDay(day) }.flatMap { day =>
      day.map { magasin =>

        val idMagasin = magasin.head._2
        val date = magasin.head._1
        val path = String.format(myPath + "/data/input/magasins/reference_prod-%s_%s.data", idMagasin, date)
        val bufferedSourcee = io.Source.fromFile(path)
        val listOfProducts =  bufferedSourcee.getLines().map(line => line.split('|'))
        var myList: List[(String,String, String, Double)] = Nil
        for (product <- listOfProducts) {

          for ( produit<- magasin) {

            if (product(0) == produit._3) {
              var ca :Double = product(1).toDouble * produit._4
              myList = myList :::  List ((produit._1,produit._2,produit._3,ca))
            }
          }
        }
        bufferedSourcee.close
        myList
      }

    }.flatten
      .groupBy(day => (day._2, day._3)).values.toList.flatMap { listOfTuples =>

      //calculet somme des ca pour un produit pendant les 7 jours
      val somme: Double = listOfTuples.foldLeft(0.0)((acc, tuple) => acc + tuple._4)
      List((listOfTuples.head._1, listOfTuples.head._2, listOfTuples.head._3, somme))

    }.groupBy(_._2).values.toList.map(list => list.sortWith(_._4 > _._4))

      //Générer les fichiers output pour toutes les transaction pendant les 7 jours
      .foreach((x: List[(String, String, String, Double)]) => genererFichier.generateFile(x, x.head._2, transactionDate))

  }




  /**
    *  Calculer la quantité totale vendue pour chaque produit pendant une journée
    *
    * @param list
    * @return
    */
  def treatByDay(list:List[(String,String,String,Double)]): List[List[(String, String, String, Double)]] = {

    val date = list.head._1
    list.groupBy(_._2).values.map { transaction =>

      val idMagasin = transaction.head._2
      transaction.groupBy(_._3).map { transaction =>
        val idProduit = transaction._1
        var qteTotal: Double = 0.0
        val transactionValues = transaction._2

        transactionValues.foreach { line =>
          qteTotal = qteTotal + line._4

        }
        (date,idMagasin,idProduit, qteTotal)
      }.toList


    }.toList

  }

}
