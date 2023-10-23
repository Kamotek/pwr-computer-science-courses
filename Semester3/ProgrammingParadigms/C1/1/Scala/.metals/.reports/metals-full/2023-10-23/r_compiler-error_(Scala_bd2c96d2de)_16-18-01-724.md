file://<WORKSPACE>/main.scala
### java.lang.AssertionError: assertion failed

occurred in the presentation compiler.

action parameters:
offset: 243
uri: file://<WORKSPACE>/main.scala
text:
```scala
object app1 {
    def flatten1[T](list: List[List[T]]): List[T] = {
        if(list!=null && list.nonEmpty) {
            list.head ++ flatten1(list.tail)
        } 
        else {
            Nil
        }
    }

val list = List((1,2,3,4), ()@@)
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:11)
	dotty.tools.dotc.core.TypeOps$.dominators$1(TypeOps.scala:248)
	dotty.tools.dotc.core.TypeOps$.approximateOr$1(TypeOps.scala:382)
	dotty.tools.dotc.core.TypeOps$.orDominator(TypeOps.scala:395)
	dotty.tools.dotc.core.Types$OrType.join(Types.scala:3406)
	dotty.tools.dotc.core.Types$OrType.widenUnionWithoutNull(Types.scala:3422)
	dotty.tools.dotc.core.Types$Type.widenUnion(Types.scala:1293)
	dotty.tools.dotc.core.ConstraintHandling.widenOr$1(ConstraintHandling.scala:652)
	dotty.tools.dotc.core.ConstraintHandling.widenInferred(ConstraintHandling.scala:668)
	dotty.tools.dotc.core.ConstraintHandling.widenInferred$(ConstraintHandling.scala:29)
	dotty.tools.dotc.core.TypeComparer.widenInferred(TypeComparer.scala:30)
	dotty.tools.dotc.core.ConstraintHandling.instanceType(ConstraintHandling.scala:707)
	dotty.tools.dotc.core.ConstraintHandling.instanceType$(ConstraintHandling.scala:29)
	dotty.tools.dotc.core.TypeComparer.instanceType(TypeComparer.scala:30)
	dotty.tools.dotc.core.TypeComparer$.instanceType(TypeComparer.scala:2993)
	dotty.tools.dotc.core.Types$TypeVar.instantiate(Types.scala:4765)
	dotty.tools.dotc.typer.Inferencing.tryInstantiate$1(Inferencing.scala:726)
	dotty.tools.dotc.typer.Inferencing.doInstantiate$1(Inferencing.scala:729)
	dotty.tools.dotc.typer.Inferencing.interpolateTypeVars(Inferencing.scala:732)
	dotty.tools.dotc.typer.Inferencing.interpolateTypeVars$(Inferencing.scala:547)
	dotty.tools.dotc.typer.Typer.interpolateTypeVars(Typer.scala:115)
	dotty.tools.dotc.typer.Typer.simplify(Typer.scala:3007)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:2993)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3058)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3062)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3174)
	dotty.tools.dotc.typer.Namer.typedAheadExpr$$anonfun$1(Namer.scala:1619)
	dotty.tools.dotc.typer.Namer.typedAhead(Namer.scala:1609)
	dotty.tools.dotc.typer.Namer.typedAheadExpr(Namer.scala:1619)
	dotty.tools.dotc.typer.Namer.typedAheadRhs$1$$anonfun$1(Namer.scala:1872)
	dotty.tools.dotc.inlines.PrepareInlineable$.dropInlineIfError(PrepareInlineable.scala:249)
	dotty.tools.dotc.typer.Namer.typedAheadRhs$1(Namer.scala:1872)
	dotty.tools.dotc.typer.Namer.rhsType$1(Namer.scala:1880)
	dotty.tools.dotc.typer.Namer.cookedRhsType$1(Namer.scala:1898)
	dotty.tools.dotc.typer.Namer.lhsType$1(Namer.scala:1899)
	dotty.tools.dotc.typer.Namer.inferredResultType(Namer.scala:1910)
	dotty.tools.dotc.typer.Namer.inferredType$1(Namer.scala:1657)
	dotty.tools.dotc.typer.Namer.valOrDefDefSig(Namer.scala:1664)
	dotty.tools.dotc.typer.Namer$Completer.typeSig(Namer.scala:783)
	dotty.tools.dotc.typer.Namer$Completer.completeInCreationContext(Namer.scala:922)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:810)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.ensureCompleted(SymDenotations.scala:390)
	dotty.tools.dotc.typer.Typer.retrieveSym(Typer.scala:2869)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:2894)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:2990)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3058)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3062)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3084)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3130)
	dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:2562)
	dotty.tools.dotc.typer.Typer.typedTypeOrClassDef$1(Typer.scala:2916)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:2920)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:2990)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3058)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3062)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3084)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3130)
	dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:2692)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:2961)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:2991)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3058)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3062)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3174)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$1(TyperPhase.scala:44)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$adapted$1(TyperPhase.scala:54)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:437)
	dotty.tools.dotc.typer.TyperPhase.typeCheck(TyperPhase.scala:54)
	dotty.tools.dotc.typer.TyperPhase.runOn$$anonfun$3(TyperPhase.scala:88)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:88)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:247)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1321)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:263)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:271)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:280)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:67)
	dotty.tools.dotc.Run.compileUnits(Run.scala:280)
	dotty.tools.dotc.Run.compileSources(Run.scala:195)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:40)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:375)
```
#### Short summary: 

java.lang.AssertionError: assertion failed