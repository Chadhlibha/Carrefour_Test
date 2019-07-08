package gestionInput

class LectureTransaction(path:String, firstDay:String, nbrOfDays:Int) {

  val generateInput = new GenererInput(path,"data",firstDay,nbrOfDays)
  val tab: List[String] = generateInput.listofDays
  var result:Iterator[Array[String]] = Iterator[Array[String]]()

  for (i <- 0 to  tab.length-1){
    val transactionpath =  String.format(path + "/data/input/transactions/transactions_%s.data",tab(i).split("T")(0))
    val bufferedSource = io.Source.fromFile(transactionpath)
    val transaction = bufferedSource.getLines().map(line => line.split('|'))
    result =  result ++ transaction
  }
}
