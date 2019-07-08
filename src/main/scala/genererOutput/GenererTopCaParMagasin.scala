package genererOutput

import java.io.{BufferedWriter, File, FileWriter}

class GenererTopCaParMagasin(mypath :String) {

  /** Générer le fichier output top_100_ca_<ID_MAGASIN>_YYYYMMDD.data
    *
    * @param list         la liste qui contient les transactions pour un certain magasin
    * @param idMagasin    l'identifiant du magasin
    * @param date         la date de transaction
    */

  def generateFile(list: List[(String,String, String, Double)],idMagasin:String,date:String):Unit= {

    val file = new File(String.format( mypath +"/data/Topca_output/top_100_ca_%s_%s.data",idMagasin,date))
    val bw = new BufferedWriter(new FileWriter(file))
    list.slice(1,100).foreach(x => bw.write(x._3 + "|" + x._4 + "\n") )

    bw.close()
  }
}
