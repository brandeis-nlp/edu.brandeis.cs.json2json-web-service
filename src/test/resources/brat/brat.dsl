{

/***********************************************************************************************************************
 [ &: ] refers to the who input
 [ &. ] refers to the local input
 [ %. ] refers to the toolkit.
***********************************************************************************************************************/

def targetText = ""
def targetRelations = []
def targetEquivs = []
def targetEntities = []

if (! %.has(&:toString(), "discriminator")) {
  text &:toString()
  return
}
def discriminator = &:discriminator
if (discriminator == null) return

def isText = %.has( &:discriminator,  "http://vocab.lappsgrid.org/ns/media/text")
if (isText) {
    text &:payload
    return
}

def isLifTex = %.has( &:discriminator,  "http://vocab.lappsgrid.org/ns/media/jsonld")
if (!isLifTex) return

def lastView = &:payload.views[-1]
if (lastView == null) return


def isCoref = %.has(lastView.metadata.contains,  "Coreference")
def isDepParser = %.has(lastView.metadata.contains,  "DependencyStructure")
def isParser = %.has(lastView.metadata.contains,  "Constituent")
def isNer = %.has(lastView.metadata.contains,  "NamedEntity")
def isTagger = %.has(lastView.metadata.contains,  "Token#pos")

def lastViewAnns = lastView.annotations
if (lastViewAnns == null) return

if (isText) targetText = &:payload
if (isLifTex) targetText = &:payload.text."@value"

if (isParser) {
    targetText +=  "\n~~~\n" + lastViewAnns.select{ %.has(&."@type", "PhraseStructure") }.features.penntree[0]
}

if (isDepParser) {
    def dependencies = lastViewAnns.select{%.has(&."@type", "DependencyStructure")}.features.dependencies.flatten()
    targetRelations = lastViewAnns.select{ &.id in dependencies}.foreach {
        [&.id, &.label, [["Governor", &.features.governor], ["Dependent", &.features.dependent]]]
    }
}

if (isCoref) {
    def corefFeatures = lastViewAnns.select{ %.has(&."@type", "Coreference") }.features
    targetEquivs += corefFeatures.flatten().foreach { ["*", "Coreference", &.mentions[0], &.mentions[1]] }

    targetEntities += lastViewAnns.select{%.has(&."@type", "Markable") && (&.id in corefFeatures.mentions.flatten())}.unique{&.start+" "+&.end}.foreach{
                          [&.id, "Mention", [[&.start.toInteger(), &.end.toInteger()]]]
    }
}

if (isNer) {
       targetEntities += lastViewAnns.select{ %.hasAny(&."@type", "Date", "Person", "Location", "Organization" )}.unique{ &.start + " " + &.end }.foreach {
            [&.id, %.lastWord(&."@type"), [[&.start.toInteger(), &.end.toInteger()]]]
       }
}

//if (isTagger) {
    targetEntities += lastViewAnns.select{&.features != null  &&  &.features.pos != null}.unique{ &.start + " " + &.end }.foreach {
        [&.id, %.toUpper(&.features.pos), [[&.start.toInteger(), &.end.toInteger()]]]
    }
//}

text  targetText

relations targetRelations

equivs (targetEquivs)

entities (targetEntities)

}
