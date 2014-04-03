package com.ponkotuy.parser

import com.github.theon.uri.Uri
import com.ponkotuy.util.Log
import scala.util.matching.Regex

/**
 *
 * @author ponkotuy
 * Date: 14/02/19.
 */
sealed abstract class ResType(val regexp: Regex)

object ResType extends Log {
  val Api = "/kcsapi"
  val AuthMember = s"$Api/api_auth_member"
  val GetMaster = s"$Api/api_get_master"
  val GetMember = s"$Api/api_get_member"
  val ReqKousyou = s"$Api/api_req_kousyou"
  val ReqHokyu = s"$Api/api_req_hokyu"
  val ReqHensei = s"$Api/api_req_hensei"
  val ReqMission = s"$Api/api_req_mission"
  val ReqKaisou = s"$Api/api_req_kaisou"
  val ReqPractice = s"$Api/api_req_practice"
  val ReqMember = s"$Api/api_req_member"
  val ReqMap = s"$Api/api_req_map"
  val ReqSortie = s"$Api/api_req_sortie"
  val ReqQuest = s"$Api/api_req_quest"
  val ReqNyukyo = s"$Api/api_req_nyukyo"

  case object LoginCheck extends ResType(s"\\A$AuthMember/logincheck\\z".r) // 取るべきではない
  case object Material extends ResType(s"\\A$GetMember/material\\z".r)
  case object Basic extends ResType(s"\\A$GetMember/basic\\z".r)
  case object Record extends ResType(s"\\A$GetMember/record\\z".r) // Basicの綺麗版
  case object Ship2 extends ResType(s"\\A$GetMember/ship2\\z".r) // どうせship3に含まれているんだろう？
  case object Ship3 extends ResType(s"\\A$GetMember/ship3\\z".r)
  case object NDock extends ResType(s"\\A$GetMember/ndock\\z".r)
  case object KDock extends ResType(s"\\A$GetMember/kdock\\z".r)
  case object Deck extends ResType(s"\\A$GetMember/deck\\z".r) // DeckPortと何が違うのか分からなくて困っている
  case object DeckPort extends ResType(s"\\A$GetMember/deck_port\\z".r)
  case object UseItem extends ResType(s"\\A$GetMember/useitem\\z".r) // 家具箱とか
  case object SlotItem extends ResType(s"\\A$GetMember/slotitem\\z".r)
  case object Practice extends ResType(s"\\A$GetMember/practice\\z".r) // 演習相手。取るべきではない
  case object Book2 extends ResType(s"\\A$GetMember/book2\\z".r)
  case object MapInfo extends ResType(s"\\A$GetMember/mapinfo\\z".r)
  case object CreateShip extends ResType(s"\\A$ReqKousyou/createship\\z".r)
  case object GetShip extends ResType(s"\\A$ReqKousyou/getship\\z".r) // IDとshipIDのみ
  case object CreateItem extends ResType(s"\\A$ReqKousyou/createitem\\z".r)
  case object Charge extends ResType(s"\\A$ReqHokyu/charge\\z".r) // 特に要らない
  case object HenseiChange extends ResType(s"\\A$ReqHensei/change\\z".r) // 特に要らない
  case object HenseiLock extends ResType(s"\\A$ReqHensei/lock\\z".r)
  case object MissionStart extends ResType(s"\\A$ReqMission/start\\z".r) // 特に要らない
  case object KaisouPowerup extends ResType(s"\\A$ReqKaisou/powerup\\z".r)
  case object PracticeBattle extends ResType(s"\\A$ReqPractice/battle\\z".r)
  case object PracticeMidnightBattle extends ResType(s"\\A$ReqPractice/midnight_battle\\z".r)
  case object PracticeBattleResult extends ResType(s"\\A$ReqPractice/battle_result\\z".r)
  case object GetOthersDeck extends ResType(s"\\A$ReqMember/getothersdeck\\z".r) // 演習相手。取るべきではない
  case object MapStart extends ResType(s"\\A$ReqMap/start\\z".r)
  case object MapNext extends ResType(s"\\A$ReqMap/next\\z".r)
  case object SortieBattle extends ResType(s"\\A$ReqSortie/battle\\z".r)
  case object SortieBattleResult extends ResType(s"\\A$ReqSortie/battleresult\\z".r)
  case object ClearItemGet extends ResType(s"\\A$ReqQuest/clearitemget\\z".r)
  case object NyukyoStart extends ResType(s"\\A$ReqNyukyo/start\\z".r)
  case object MasterShip extends ResType(s"\\A$GetMaster/ship\\z".r)
  case object MasterPractice extends ResType(s"\\A$GetMaster/practice\\z".r)
  case object MasterUseItem extends ResType(s"\\A$GetMaster/useitem\\z".r) // 高速修復材とかの説明
  case object MasterFurniture extends ResType(s"\\A$GetMaster/furniture\\z".r) // 家具の説明
  case object MasterSlotItem extends ResType(s"\\A$GetMaster/slotitem\\z".r)
  case object MasterMapArea extends ResType(s"\\A$GetMaster/maparea\\z".r) // 鎮守府海域・南西諸島海域など
  case object MasterSType extends ResType(s"\\A$GetMaster/stype\\z".r)
  case object MasterMission extends ResType(s"\\A$GetMaster/mission\\z".r)
  case object ShipSWF extends ResType("""\A/kcs/ships/[0-9]+\.swf\z""".r)

  val values = Set(
    LoginCheck, Material, Basic, Record, Ship2, Ship3, NDock, KDock,
    Deck, DeckPort, UseItem, SlotItem, Practice, Book2, MapInfo, CreateShip, GetShip, CreateItem,
    Charge, HenseiChange, HenseiLock, MissionStart, KaisouPowerup, PracticeBattle, PracticeBattleResult, GetOthersDeck,
    MapStart, SortieBattle, SortieBattleResult, ClearItemGet, NyukyoStart,
    MasterShip, MasterPractice, MasterUseItem, MasterFurniture, MasterSlotItem, MasterMapArea, MasterSType, MasterMission,
    ShipSWF
  )

  def fromUri(uri: String): Option[ResType] = {
    val path = Uri.parseUri(uri).pathRaw
    println(path)
    values.find(_.regexp.findFirstIn(path).isDefined)
  }
}
