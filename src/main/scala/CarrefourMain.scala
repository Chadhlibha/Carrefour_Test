
object CarrefourMain {


  def main (args:Array[String]): Unit ={


     val dataPath : String = args(0)         //expl: /home/lansrod/Bureau/ComCarrefourTest/data/
     val transactionDate : String = args(1)


    //val dataPath : String =   "/home/lansrod/Bureau/BIGDATA/CarrefourTest-master/"       //expl: /home/lansrod/Bureau/ComCarrefourTest/data/
    //val transactionDate : String = "20190708"



    val genererInputFiles = new GenererInput(dataPath,transactionDate,7)
    genererInputFiles.run()     //générer les inputs

    val topVente : TopVenteParMagasin = new TopVenteParMagasin(dataPath,transactionDate)
    topVente.generateTopVente()   //générer top 100 ventes par magasin en transactiondate



    var topCa: TopCAParMagasin = new TopCAParMagasin (dataPath,transactionDate)
    topCa.generateOutput()        //générer top 100 ca par magasin pendant 7j à partir du transactiondate






  }





}


















