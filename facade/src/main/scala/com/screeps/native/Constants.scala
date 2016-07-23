package com.screeps.native


object Direction extends Enumeration {
  val Top, TopRight, Right, BottomRight, Bottom, BottomLeft, Left, TopLeft = Value
}

object Errors extends Enumeration {
  val OK                 = Value(0)
  val NotOwner           = Value(-1)
  val NoPath             = Value(-2)
  val NameExists         = Value(-3)
  val Busy               = Value(-4)
  val NotFound           = Value(-5)
  val NotEnoughResources = Value(-6)
  val InvalidTarget      = Value(-7)
  val Full               = Value(-8)
  val NotInRange         = Value(-9)
  val InvalidArgs        = Value(-10)
  val Tired              = Value(-11)
  val NoBodypart         = Value(-12)
  val RCLNotEnough       = Value(-14)
  val GCLNotEnough       = Value(-15)
}

object GameMode extends Enumeration {
  val Simulation = Value("simulation")
  val Survival   = Value("survival")
  val World      = Value("world")
  val Arena      = Value("arena")
}

object StructureType extends Enumeration {
  val Spawn      = Value("spawn")
  val Extension  = Value("extension")
  val Road       = Value("road")
  val Wall       = Value("constructedWall")
  val Rampart    = Value("rampart")
  val KeeperLair = Value("keeperLair")
  val Portal     = Value("portal")
  val Controller = Value("controller")
  val Link       = Value("link")
  val Storage    = Value("storage")
  val Tower      = Value("tower")
  val Observer   = Value("observer")
  val PowerBank  = Value("powerBank")
  val PowerSpawn = Value("powerSpawn")
  val Extractor  = Value("extractor")
  val Lab        = Value("lab")
  val Terminal   = Value("terminal")
  val Container  = Value("container")
  val Nuker      = Value("nuker")
}

object Color extends Enumeration(1) {
  val Red    = Value("red")
  val Purple = Value("purple")
  val Blue   = Value("blue")
  val Cyan   = Value("cyan")
  val Green  = Value("green")
  val Yellow = Value("yellow")
  val Orange = Value("orange")
  val Brown  = Value("brown")
  val Grey   = Value("grey")
  val White  = Value("white")
}

object FindType extends Enumeration {
  val ExitTop                  = Value(Direction.Top.id, "EXIT_TOP")
  val ExitRight                = Value(Direction.Right.id, "EXIT_RIGHT")
  val ExitBottom               = Value(Direction.Bottom.id, "EXIT_BOTTOM")
  val ExitLeft                 = Value(Direction.Left.id, "EXIT_LEFT")
  val Exit                     = Value(10, "EXIT")
  val Creeps                   = Value(101, "CREEPS")
  val MyCreeps                 = Value(102, "MY_CREEPS")
  val HostileCreeps            = Value(103, "HOSTILE_CREEPS")
  val SourcesActive            = Value(104, "SOURCES_ACTIVE")
  val Sources                  = Value(105, "SOURCES")
  val DroppedResources         = Value(106, "DROPPED_RESOURCES")
  val Structures               = Value(107, "STRUCTURES")
  val MyStructures             = Value(108, "MY_STRUCTURES")
  val HostileStructures        = Value(109, "HOSTILE_STRUCTURES")
  val Flags                    = Value(110, "FLAGS")
  val ConstructionSites        = Value(111, "CONSTRUCTION_SITES")
  val MySpawns                 = Value(112, "MY_SPAWNS")
  val HostileSpawns            = Value(113, "HOSTILE_SPAWNS")
  val MyConstructionSites      = Value(114, "MY_CONSTRUCTION_SITES")
  val HostileConstructionSites = Value(115, "HOSTILE_CONSTRUCTION_SITES")
  val Minerals                 = Value(116, "MINERALS")
  val Nukes                    = Value(117, "NUKES")
}

object LookType extends Enumeration {
  val Creeps            = Value("creep")
  val Energy            = Value("energy")
  val Resources         = Value("resource")
  val Sources           = Value("source")
  val Minerals          = Value("mineral")
  val Structures        = Value("structure")
  val Flags             = Value("flag")
  val ConstructionSites = Value("constructionSite")
  val Nukes             = Value("nuke")
  val Terrain           = Value("terrain")
}

object ResourceType extends Enumeration {
  val Energy = Value("energy")
  val Power = Value("power")

  val Hydrogen = Value("H")
  val Oxygen = Value("O")
  val Utrium = Value("U")
  val Lemergium = Value("L")
  val Keanium = Value("K")
  val Zynthium = Value("Z")
  val Catalyst = Value("X")
  val Ghodium = Value("G")

  val Hydroxie = Value("OH")
  val ZynthiumKeanite = Value("ZK")
  val UtriumLemergite = Value("UL")

  val UtriumHydride = Value("UH")
  val UtriumOxide = Value("UO")
  val KeaniumHydride = Value("KH")
  val KeaniumOxide = Value("KO")
  val LemergiumHydrive = Value("LH")
  val LemergiumOxide = Value("LO")
  val ZynthiumHydride = Value("ZH")
  val ZynthiumOxide = Value("ZO")
  val GhodiumHydride = Value("GH")
  val GhodiumOxide = Value("GO")

  val UtriumAcide = Value("UH2O")
  val UtriumAlkalide = Value("UHO2")
  val KeaniumAcid = Value("KH2O")
  val KeaniumAlkalide = Value("KHO2")
  val LemergiumAcid = Value("LH2O")
  val LemergiumAlkalide = Value("LHO2")
  val ZynthiumAcid = Value("ZH2O")
  val ZynthiumAlkalide = Value("ZHO2")
  val GhodiumAcid = Value("GH2O")
  val GhodiumAlkalide = Value("GHO2")

  val CatalyzedUtriumAcid = Value("XUH2O")
  val CatalyzedUtriumAlkalide = Value("XUHO2")
  val CatalyzedKeaniumAcid = Value("XKH2O")
  val CatalyzedKeaniumAlkalide = Value("XKHO2")
  val CatalyzedLemergiumAcid = Value("XLH2O")
  val CatalyzedLemergiumAlkalide = Value("XLHO2")
  val CatalyzedZynthiumAcide = Value("XZH2O")
  val CatalyzedZynthiumAlkalide = Value("XZHO2")
  val CatalyzedGhodiumAcid = Value("XGH2O")
  val CatalyzedGhodiumAlkalide = Value("XGHO2")

}