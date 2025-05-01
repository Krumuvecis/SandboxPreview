# Chemistry

## Abstract

Provides models for various chemical elements and compounds.


## Model structure
* `Element`:
  * a base unit
* `Molecule`:
  * consists of elements
  * has a `Map<Element, Integer>`
* `Substance`:
  * consists of molecules amd a phase
  * has a `Map<Molecule, Double>`
  * has `SubstancePhase` enum
* `SubstanceMixture`:
  * consists of substances of various phases
  * has a `Map<Substance, Double` *(for now...)*
  * *(planned to differentiate between **base-matrix-substance** and a **phase-substance-mix**)*


## Notes

*something, something...*

*more info coming soon...*