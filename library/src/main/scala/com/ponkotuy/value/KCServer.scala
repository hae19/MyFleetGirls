package com.ponkotuy.value

/**
 * Date: 14/06/10
 */
case class KCServer(number: Int, ip: String, name: String)

object KCServer {
  val values = List(
    KCServer(1, "203.104.209.71", "横須賀鎮守府"),
    KCServer(2, "203.104.209.87", "呉鎮守府"),
    KCServer(3, "125.6.184.16", "佐世保鎮守府"),
    KCServer(4, "125.6.187.205", "舞鶴鎮守府"),
    KCServer(5, "125.6.187.229", "大湊警備府"),
    KCServer(6, "203.104.209.134", "トラック泊地"),
    KCServer(7, "203.104.209.167", "リンガ泊地"),
    KCServer(8, "203.104.248.135", "ラバウル基地"),
    KCServer(9, "125.6.189.7", "ショートランド泊地"),
    KCServer(10, "125.6.189.39", "ブイン基地"),
    KCServer(11, "125.6.189.71", "タウイタウイ泊地"),
    KCServer(12, "125.6.189.103", "パラオ泊地"),
    KCServer(13, "125.6.189.135", "ブルネイ泊地"),
    KCServer(14, "125.6.189.167", "単冠湾泊地"),
    KCServer(15, "125.6.189.215", "幌筵泊地"),
    KCServer(16, "125.6.189.247", "宿毛湾泊地"),
    KCServer(17, "203.104.209.23", "鹿屋基地"),
    KCServer(18, "203.104.209.39", "岩川基地"),
    KCServer(19, "203.104.209.55", "佐伯湾泊地"),
    KCServer(20, "203.104.209.102", "柱島泊地")
  )

  lazy val ips = values.map(_.ip).toSet

  def fromNumber(number: Int): Option[KCServer] = values.find(_.number == number)
  def fromIP(ip: String): Option[KCServer] = values.find(_.ip == ip)
}
