
import gestionInput.GenererInput
import traitement.{TopCAParMagasin, TopVenteParMagasin}

object CarrefourMain {

  def main (args:Array[String]): Unit ={

    val dataPath : String = args(0)
    val transactionDate : String = args(1)

    //générer les inputs
    val genererInputFiles = new GenererInput(dataPath,"/data",transactionDate,7)
    genererInputFiles.run()

    //générer top 100 ventes par magasin en transactiondate
    val topVente : TopVenteParMagasin = new TopVenteParMagasin(dataPath,transactionDate)
    topVente.generateTopVente()

    //générer top 100 ca par magasin pendant 7jours à partir du transactiondate
    var topCa: TopCAParMagasin = new TopCAParMagasin (dataPath,transactionDate)
    topCa.generateOutput()

  }
}
