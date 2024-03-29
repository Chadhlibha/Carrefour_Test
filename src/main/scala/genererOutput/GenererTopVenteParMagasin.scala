package genererOutput

import java.io.{BufferedWriter, File, FileWriter}

class GenererTopVenteParMagasin(mypath : String) {

  /** Générer le fichier top_100_vente_<ID_MAGASIN>_YYYYMMDD.data
    *
    * @param list          la liste qui contient les transactions pour un certain magasin
    * @param idMagasin     l'identifiant du magasin
    * @param date          la date de transaction
    */

  def generateFile(list: List[(String, String, Double)],idMagasin:String,date:String):Unit = {

    val myList = list.map(l => (l._2, l._3))
    val file = new File(String.format(mypath + "/data/Topvente_output/top_100_ventes_%s_%s.data",idMagasin,date))
    val bw = new BufferedWriter(new FileWriter(file))
    myList.slice(1,100).foreach(x => bw.write(x._1 + "|" + x._2 + "\n") )

    bw.close()
  }

}
