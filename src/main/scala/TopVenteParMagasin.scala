
class TopVenteParMagasin(myPath:String, transactionDate:String) {



  var transaction: LectureTransaction = new LectureTransaction(myPath,transactionDate,1)
  val genererFichier:GenererTopVenteParMagasin = new GenererTopVenteParMagasin(myPath)
  val myResult = transaction.result.toList

  //Calculer les Top 100 ventes des produits dans tous les magasins pendant un jours
  var listeDesTransactions: List[List[(String, String, Double)]] = myResult.map(x=>(x(2),x(3),x(4).toDouble)).groupBy(_._1).values.map { transaction =>
    val idMagasin = transaction.head._1
    transaction.groupBy(_._2).map { transaction =>
      val idProduit = transaction._1
      var qteTotal: Double = 0.0
      val transactionValues = transaction._2

      transactionValues.foreach { line =>
        qteTotal = qteTotal + line._3

      }
      (idMagasin,idProduit, qteTotal)
    }.toList


  }.toList



  /**
    * Générer les fichier output pour les 100 top_ventes
    */
  def generateTopVente(): Unit = {

    listeDesTransactions.map {x=> x.sortWith(_._3 >_._3)}.foreach((x: List[(String, String, Double)]) => genererFichier.generateFile(x,x.head._1,transactionDate))
  }







}
