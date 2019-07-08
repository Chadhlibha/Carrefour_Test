package gestionInput

import java.io.{BufferedWriter, File, FileWriter}
import java.util.Date

class GenererInput(path:String,dataFolderPath:String,firstDay:String, nbrOfDays:Int) extends Thread{

  val nbrTransactions: Int = 10000
  val nbrMagasins: Int = 15
  val nbrProduit : Int = 1500
  val qteMax : Int = 20
  val prixMax = 50
  val year: String =firstDay.substring(0,4)
  val month: String =firstDay.substring(4,6)
  val day : String = firstDay.substring(6,firstDay.length)
  val random = new scala.util.Random


  /** Générer une liste qui contient les jours dont les transactions sont éffectués
    *
    * @param year            l'année associée à la liste des transactions
    * @param month           le mois associé à la liste des transactions
    * @param myFirstDay      la première journée dans la liste des jours
    * @return
    */

  def generateListOfDays(year:String,month: String,myFirstDay:String): List[String] ={

    var listDesJours : List[String]= Nil
    val date = new Date()

    if(nbrOfDays==1){
      listDesJours = List(firstDay)
    }
    else {
      val lastDay: Int = myFirstDay.toInt + (nbrOfDays - 1)
      for (i <- myFirstDay.toInt to lastDay) {
        var Iso: String=""
        if(i < 10 ) {
          Iso = year + month + "0" + i + 'T' + date.getHours.toString +
            date.getMinutes.toString + date.getSeconds.toString
        }
        else {
          Iso= year + month + i + 'T' + date.getHours.toString + date.getMinutes.toString + date.getSeconds.toString
        }
        listDesJours = listDesJours ::: List(Iso)
      }
    }
    listDesJours
  }

  //La liste qui contient les 7 jours à utiliser
  val listofDays :List[String]= generateListOfDays(year,month,day)


  /** Générer un random string de longueur n à partir de l'alphabet donné
    *
    * @param alphabet        String des chars utilisé pour generer un nouveau String
    * @param n               longueur de l'output
    * @return
    */
  def randomString(alphabet: String)(n: Int): String =
    Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString


  //Générer une liste qui contient nbrMagasins des magasins
  var listDesMagasins : List[String] = Nil
  for(i <- 1 to nbrMagasins ) {
    var idMagasin : String =this.randomString("abcdefghijklmnopqrstuvwxyz0123456789")(8) + "-" +
      this.randomString("abcdefghijklmnopqrstuvwxyz0123456789")(4)+"-"+
      this.randomString("abcdefghijklmnopqrstuvwxyz0123456789")(4)+"-"+
      this.randomString("abcdefghijklmnopqrstuvwxyz0123456789")(4)+
      "-"+ this.randomString("abcdefghijklmnopqrstuvwxyz0123456789")(12)
    listDesMagasins = listDesMagasins:::List(idMagasin)
  }


  /** Générer un fichier de transaction pour chaque jour dans listofdays
    *
    * @param listOfDays          la liste qui contient les sept jours
    * @param nbr_transactions    le nombre des transactions chaque jour
    * @param listdesmagasins     la liste qui contient tous les id des magasins
    */

  def genererTransactions(listOfDays: List[String], nbr_transactions:Int,listdesmagasins : List[String]): Unit = {

    for (day <- listOfDays) {
      val date = day.split("T")(0)
      val file = new File(String.format(path + dataFolderPath + "/input/transactions/transactions_%s.data",date ))
      val bw = new BufferedWriter(new FileWriter(file))
      for (i <- 1 to nbr_transactions) {
        bw.write(
          i + "|" + day + "|" +
            listdesmagasins(random.nextInt(listdesmagasins.length)) +
            "|" + random.nextInt(nbrProduit) + "|" + random.nextInt(qteMax) + "\n")
      }

      bw.close()
    }
  }


  /** Générer les fichiers référentiels des produits
    *
    * @param listOfDays         la liste qui contient les sept jours
    * @param nbrProduit         le nombre totale des produits
    * @param listDesMagasins    la liste qui contient tous les id des magasins
    */

  def genererReferentielProduit(listOfDays: List[String],nbrProduit: Int, listDesMagasins: List[String]):Unit = {

    for (day <- listOfDays) {
      for (magasin <- listDesMagasins) {
        val date = day.split("T")(0)
        val file = new File(String.format(path + dataFolderPath +
          "/input/magasins/reference_prod-%s_%s.data", magasin,date))
        val bw = new BufferedWriter(new FileWriter(file))

        for (i <- 0 to nbrProduit) {
          bw.write(i + "|" + random.nextInt(prixMax) + "\n")
        }

        bw.close()
      }
    }
  }


  override def run() {

    genererTransactions(listofDays,nbrTransactions,listDesMagasins)
    genererReferentielProduit(listofDays,nbrProduit,listDesMagasins)

  }
}
