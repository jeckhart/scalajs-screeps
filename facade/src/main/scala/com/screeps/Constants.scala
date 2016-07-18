package com.screeps


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
  val DroppedEnergy            = Value(106, "DROPPED_ENERGY")
  val DropperResources         = Value(106, "DROPPED_RESOURCES")
  val Strcutures               = Value(107, "STRUCTURES")
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