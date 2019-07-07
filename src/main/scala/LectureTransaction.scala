

class LectureTransaction(path:String, firstday:String, nbrofdays:Int) {

  val generateinput = new GenererInput(path,firstday,nbrofdays)

  val tab: List[String] = generateinput.listofDays

  var result:Iterator[Array[String]] = Iterator[Array[String]]()

  for (i <- 0 to  tab.length-1){

    val transactionpath =  String.format(path + "/data/input/transactions/transactions_%s.data",tab(i).split("T")(0))
    val bufferedSource = io.Source.fromFile(transactionpath)
    val transaction = bufferedSource.getLines().map(line => line.split('|'))
    result =  result ++ transaction

  }
}


