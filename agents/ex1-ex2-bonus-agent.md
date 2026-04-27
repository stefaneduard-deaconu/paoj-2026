---
description: >
  Unified agent for authoring and validating a PAOJ laboratory that contains
  1–2 compulsory exercises (auto-tested) and 1 bonus exercise (manual demo).
  Covers structure checking, exercise-1 rules, optional exercise-2 rules, and
  bonus-exercise rules.  Supersedes ex1-agent.md, ex2-agent.md, ex3-agent.md
  and lab-structure-checker-agent.md.
---

# Agent — Compulsory + Bonus Laboratory (PAOJ)

## Cum se folosește

Acest agent ghidează **crearea sau validarea** unui laborator PAOJ care conține:
- **Exercițiul 1** (obligatoriu, teste automate multi-parte)  
- **Exercițiul 2** (obligatoriu dacă laboratorul este complet, teste automate flat — *numai când există timp*)  
- **Exercițiu bonus** (opțional, fără teste automate, demonstrație în `Main.java`)

Rulează secțiunile **în ordine**. La fiecare fază care include un bloc `> 🤔 Întreabă`, agentul **trebuie să pună acele întrebări utilizatorului** înainte de a continua.

---

## Faza 0 — Întrebări preliminare

> 🤔 **Întreabă utilizatorul:**
>
> 1. **Numărul laboratorului** — care este `NN` în `laboratoryNN`?
> 2. **Tip laborator** — este un laborator complet (exerciții 1 + 2 + bonus) sau scurt (exercițiu 1 + bonus)?
>    - *Complet* = ~1h40 min, 3 exerciții (ex1 ~35 min, ex2 ~20 min, bonus ~30-45 min) — ca `laboratory07`
>    - *Scurt* = ~1h25 min, 2 exerciții (ex1 ~60 min, bonus ~25 min) — ca `laboratory08`
> 3. **Scenariul / tema** — care este domeniul problemei (eCommerce, bibliotecă, spital, etc.)?
> 4. **Noțiunile Java** care trebuie demonstrate în acest laborator (ex: sealed classes, Stream API, generics, I/O, etc.)
> 5. **Exercițiul 1 are mai multe sub-cerințe (părți)?** — câte părți estimezi (partA, partB, partC)?
> 6. *(Dacă laborator complet)* **Exercițiul 2 extinde scenariul din ex1 sau introduce obiecte noi?** Scurtă descriere.
> 7. **Bonusul** — ce noțiune avansată sau nouă introduce față de exercițiile obligatorii?

Salvează răspunsurile; ele controlează ce reguli se aplică în fazele următoare.

---

## Faza 1 — Validare structurală

Parcurge recursiv directorul `src/com/pao/laboratoryNN/` și verifică fiecare regulă de mai jos.  
Raportează fiecare regulă cu `[OK]`, `[WARN]` sau `[FAIL]`.  
La final, afișează un sumar: număr total de `[FAIL]` și `[WARN]`.

### R-STR-1 — Fiecare exercițiu are propriul `Readme.md`
- Fiecare subdirector `exerciseM/` **trebuie** să conțină `Readme.md`.
- `[FAIL]` dacă lipsește.

### R-STR-2 — Fiecare exercițiu are un `Main.java`
- Fiecare subdirector `exerciseM/` trebuie să conțină `Main.java`.
- `[FAIL]` dacă lipsește.

### R-STR-3 — Exercițiile cu teste automate au `Checker.java` și directorul `tests/`
- Dacă există `Checker.java`, trebuie să existe și `tests/`.
- Dacă există `tests/`, trebuie să existe și `Checker.java`.
- `[FAIL]` dacă una există fără cealaltă.
- **Notă:** checker-ul se numește `Checker.java`, nu `Test.java` (convenție actualizată față de lab06).

### R-STR-4 — Structura `tests/` depinde de tipul de exercițiu
- **Exercițiu 1 (multi-parte):** `tests/` trebuie să conțină **numai** subdirectoare `partA/`, `partB/`, `partC/`, ... (literă mare, fără fișiere `.in`/`.out` direct în `tests/`).
  - `[FAIL]` dacă există fișiere `.in`/`.out` direct în `tests/`.
  - `[WARN]` dacă subdirectoarele nu urmează schema `part[A-Z]+`.
- **Exercițiu 2 (flat):** `tests/` trebuie să conțină **numai** fișiere `.in`/`.out` direct (fără subdirectoare `partX/`).
  - `[FAIL]` dacă există subdirectoare `partX/` în `tests/` al exercițiului 2.
  - `[WARN]` dacă există subdirectoare care nu sunt `partX/`.

### R-STR-5 — Fiecare `.in` are un `.out` corespondent și invers
- Pentru fiecare `tests/partX/N.in` trebuie să existe `tests/partX/N.out` (și invers).
- Pentru structura flat: pentru fiecare `tests/N.in` trebuie să existe `tests/N.out` (și invers).
- `[FAIL]` dacă există fișiere orfane.

### R-STR-6 — Laboratorul are un `Readme.md` la nivel de laborator
- Fișierul `laboratoryNN/Readme.md` trebuie să existe.
- `[FAIL]` dacă lipsește.

### R-STR-7 — `Checker.java` apelează metoda corectă din `IOTest`
- **Exercițiu 1** — `Checker.java` trebuie să conțină `IOTest.runParts(`.
  - `[FAIL]` dacă conține `IOTest.runFlat(` sau `IOTest.runAll(`.
  - `[FAIL]` dacă nu conține nicio referință la `IOTest`.
- **Exercițiu 2** — `Checker.java` trebuie să conțină `IOTest.runFlat(`.
  - `[FAIL]` dacă conține `IOTest.runParts(`.
  - `[FAIL]` dacă nu conține nicio referință la `IOTest`.

### R-STR-8 — Exercițiul bonus nu are teste automate
- Subdirectorul bonus (`exercise3/` în lab complet, `exercise2/` în lab scurt) **nu** trebuie să conțină `Checker.java` sau directorul `tests/`.
- `[WARN]` dacă există (nu e greșeală fatală, dar nu este convenția pentru exercițiu bonus).

### R-STR-9 — Numerotarea fișierelor de test este consecutivă și începe de la 1
- Prefixele numerice ale testelor (`1.in`, `2.in`, ...) trebuie să fie consecutive și să înceapă de la `1`.
- `[WARN]` dacă există lacune în numerotare.

---

## Reguli de conținut `Readme.md` per exercițiu

### R-CON-1 — Readme-ul descrie un scenariu / problemă, nu o soluție
- Trebuie să existe o secțiune cu scenariu narativ (cuvânt cheie: „Scenariul", „Context" sau descriere în proză).
- `[FAIL]` dacă Readme-ul începe direct cu liste de clase de creat fără nicio introducere.

### R-CON-2 — Nu există blocuri Java cu implementări complete de clase
- `[FAIL]` dacă un bloc ` ```java ` depășește 8 linii de cod efectiv (non-comentariu, non-blank).
- `[WARN]` dacă un bloc Java conține atribute private cu tip și nume (`private String nume;`).
- Blocuri scurte de **semnătură** (ex: `public interface X { void metoda(); }`) sunt permise.

### R-CON-3 — Există hint-uri, nu soluții
- Hint-urile trebuie să menționeze concepte sau interfețe standard, **nu** să dicteze codul exact.
- `[WARN]` dacă un hint conține un bloc de cod mai lung de 3 linii.

### R-CON-4 — Formatul I/O este specificat pentru exercițiile cu teste automate
- Dacă exercițiul are `Checker.java`, Readme-ul trebuie să conțină o secțiune „Format input" (sau echivalent).
- `[FAIL]` dacă lipsește această secțiune.

### R-CON-5 — Există cel puțin un exemplu de input/output
- Readme-ul trebuie să conțină cel puțin un exemplu concret cu input și output așteptat.
- `[WARN]` dacă nu există niciun exemplu.

### R-CON-6 — Fiecare parte (`partX/`) este documentată în Readme (ex1 multi-parte)
- Pentru fiecare subdirector `partX/` din `tests/`, Readme-ul trebuie să aibă o secțiune corespunzătoare (`## Partea A`, `## Partea B`, etc.).
- `[FAIL]` dacă un `partX/` nu are secțiune corespunzătoare în Readme.

### R-CON-7 — Readme-ul nu specifică atribute private exacte ale claselor
- Expresii de tipul `private String nume;`, `private double salariu;` nu trebuie să apară în afara blocurilor de semnătură scurtă.
- `[WARN]` dacă sunt găsite astfel de linii în afara unui bloc de cod.

### R-CON-8 — Readme-ul exercițiului bonus nu promite teste automate
- Nu trebuie să conțină referințe la `Test.java`, `Checker.java` sau secțiuni de format I/O obligatoriu.
- `[WARN]` dacă există astfel de referințe (poate induce în eroare studenții).

---

## Reguli `Readme.md` la nivel de laborator

### R-LAB-1 — Există un tabel de exerciții cu linkuri
- Tabelul trebuie să conțină linkuri Markdown spre fiecare `exerciseM/Readme.md`.
- `[FAIL]` dacă lipsesc linkurile.
- Tabelul trebuie să indice clar care exerciții au teste automate și care sunt bonus.

### R-LAB-2 — Readme-ul de laborator nu conține cerințe detaliate per exercițiu
- Secțiunile cu cod Java complet sau cu liste de atribute nu trebuie să apară în Readme-ul de laborator.
- `[WARN]` dacă există blocuri Java > 5 linii.

### R-LAB-3 — Există instrucțiuni de rulare a testelor
- Readme-ul de laborator trebuie să descrie cum se rulează `Checker.java`.
- `[WARN]` dacă lipsesc instrucțiunile de rulare.

### R-LAB-4 — Tipul de test (`runParts` vs `runFlat`) este documentat
- Readme-ul trebuie să menționeze că exercițiul 1 folosește structura pe `partX/` și exercițiul 2 (dacă există) folosește structura flat.
- `[WARN]` dacă nu există această mențiune.

---

## Faza 2 — Exercițiul 1 (obligatoriu, multi-parte, `IOTest.runParts`)

> 🤔 **Întreabă utilizatorul (dacă nu s-a răspuns deja în Faza 0):**
>
> 1. **Câte părți** are exercițiul 1? (de obicei 2–3: partA, partB, opțional partC)
> 2. **Ce progresie** urmăresc părțile? (ex: partA = funcționalitate de bază, partB = extindere cu cazuri speciale, partC = funcționalitate avansată)
> 3. **Ce clasă / enum / interfață** trebuie creată ca „nucleu" al exercițiului?
> 4. **Există o excepție custom** care trebuie implementată?

### Reguli de creare

**Scenariu:**
- Exercițiul 1 introduce scenariul principal al laboratorului.
- Complexitate crescătoare pe părți: partA = funcționalitate minimă, partB = adăugare funcționalitate, partC (opțional) = cazuri avansate.

**Structură fișiere:**
```
exercise1/
  Readme.md
  Main.java
  Checker.java           ← IOTest.runParts("src/com/pao/laboratoryNN/exercise1/tests", Main::main)
  tests/
    partA/
      1.in  1.out
      2.in  2.out
    partB/
      1.in  1.out
      2.in  2.out
    partC/               ← opțional, dacă există cerință pentru partC
      1.in  1.out
```

**`Checker.java` — template:**
```java
package com.pao.laboratoryNN.exercise1;

import com.pao.test.IOTest;

public class Checker {
    public static void main(String[] args) {
        IOTest.runParts("src/com/pao/laboratoryNN/exercise1/tests", Main::main);
    }
}
```

**Readme:**
- Secțiune de scenariu narativ la început.
- Câte o secțiune `## Partea A / B / C` per parte, cu cerință și exemple de input/output.
- Secțiune `## Format input / output` cu descrierea completă a protocolului stdin/stdout.
- Hint-uri cu concepte Java relevante (nu cod complet).
- `[FAIL]` dacă o parte din `tests/` nu are secțiune corespunzătoare în Readme.

**Teste:**
- Minim 2 teste per parte; acoperă: caz normal, caz limită, caz de eroare (dacă aplicabil).
- Fișierele: `1.in`, `1.out`, `2.in`, `2.out`, ... — numerotare consecutivă de la `1`.

**Timp estimat:** menționează în Readme `~25–45 min` în funcție de complexitate.

---

## Faza 3 — Exercițiul 2 (obligatoriu dacă laborator complet, `IOTest.runFlat`)

> 🤔 **Întreabă utilizatorul:**
>
> 1. **Exercițiul 2 există?** (răspuns din Faza 0 — dacă laborator scurt, sari această fază)
> 2. **Ce reutilizează din exercițiul 1?** (ex: importă enum-ul, extinde o clasă, folosește același scenariu)
> 3. **Ce concept Java nou** introduce exercițiul 2? (ex: sealed classes, generics, Iterator, etc.)
> 4. **Câte teste flat** sunt necesare? (de obicei 3–5 teste directe fără sub-structură partX)

### Reguli de creare

**Scenariu:**
- Exercițiul 2 *reutilizează sau extinde* conceptele din exercițiul 1, dar introduce un concept Java distinct.
- I/O-ul este diferit față de ex1 — altă schemă de input sau output mai complex.
- Importă explicit din pachetul `exercise1` cel puțin un tip (enum, clasă abstractă, interfață).

**Structură fișiere:**
```
exercise2/
  Readme.md
  Main.java
  Checker.java           ← IOTest.runFlat("src/com/pao/laboratoryNN/exercise2/tests", Main::main)
  tests/
    1.in  1.out
    2.in  2.out
    3.in  3.out
```

**`Checker.java` — template:**
```java
package com.pao.laboratoryNN.exercise2;

import com.pao.test.IOTest;

public class Checker {
    public static void main(String[] args) {
        IOTest.runFlat("src/com/pao/laboratoryNN/exercise2/tests", Main::main);
    }
}
```

**Readme:**
- Secțiune de scenariu care leagă ex2 de ex1 (fraza de tip „Pornind de la exercițiul 1, ...").
- Secțiune `## Import din exercițiul 1` cu exemplu de import Java (semnătură, nu implementare).
- Secțiune `## Structura inputului` și `## Structura outputului`.
- Minim un exemplu complet input/output.

**Teste:**
- 3–5 teste plate; acoperă: caz normal, caz fără înregistrări, caz cu valori marginale.
- Nu există subdirectoare `partX/` în `tests/`.

**Timp estimat:** menționează în Readme `~15–25 min`.

---

## Faza 4 — Exercițiu bonus (fără teste automate, demonstrație în `Main.java`)

> 🤔 **Întreabă utilizatorul:**
>
> 1. **Numărul exercițiului bonus:** `exercise3` (lab complet) sau `exercise2` (lab scurt)?
> 2. **Ce noțiune Java nouă / avansată** introduce bonusul față de exercițiile obligatorii?
>    (ex: Stream API, design pattern avansat, generics cu wildcard, annotation processing, etc.)
> 3. **Extinde clasele din exercițiul precedent** sau creează o ierarhie nouă în același pachet?
> 4. **Ce demo-uri trebuie incluse** în `Main.java`? (ex: operații Stream, output structurat cu comentarii)
> 5. **Care este cerința minimă** pentru a considera bonusul „rezolvat"? (ex: 3 operații Stream distincte, acoperă toate tipurile din ierarhie, etc.)

### Reguli de creare

**Scenariu:**
- Bonusul extinde sau reutilizează ierarhia / scenariul din exercițiile obligatorii, dar introduce **noțiuni noi** sau **complexitate suplimentară** care nu au fost cerute în exercițiile cu teste.
- Oferă **libertate mai mare** studentului: nu există un format strict de I/O, studentul poate alege cum structurează datele de demo.
- Evaluarea este **manuală** (cadru didactic verifică outputul și codul sursă).

**Structură fișiere:**
```
exerciseM/             ← M = 3 (lab complet) sau 2 (lab scurt)
  Readme.md
  Main.java            ← demonstrație cu date hardcodate + output comentat
  (alte clase, dacă sunt necesare)
  ← NU există Checker.java, NU există tests/
```

**Readme:**
- Secțiune de scenariu care explică de ce acest exercițiu este bonus și ce aduce nou.
- Secțiune `## Noțiuni demonstrate` cu lista clară a conceptelor Java de demonstrat.
- Secțiune `## Cerințe minime` cu o listă de funcționalități pe care `Main.java` trebuie să le demonstreze.
- Secțiune `## Libertate de implementare` care explică că nu există format fix de I/O.
- Hint-uri pentru conceptele avansate introduse.
- **NU** conține: secțiune de format I/O strict, referințe la `Checker.java` sau `tests/`.

**`Main.java`:**
- Datele de demo sunt hardcodate sau generate intern (nu citite din stdin).
- Outputul trebuie să fie clar și să demonstreze fiecare cerință menționată în Readme.
- Comentariile din cod explică ce demonstrează fiecare bloc.

**Timp estimat:** menționează în Readme `~25–45 min`.

---

## Checklist rapid (validare manuală sau automatizată)

```
Pentru laboratoryNN/:
  □ laboratoryNN/Readme.md există                                      [R-LAB-1]
  □ Readme laborator conține tabel cu linkuri spre exerciții           [R-LAB-1]
  □ Readme laborator marchează clar exercițiile cu teste vs. bonus     [R-LAB-1]
  □ Readme laborator are instrucțiuni de rulare Checker.java           [R-LAB-3]
  □ Readme laborator menționează runParts vs. runFlat                  [R-LAB-4]
  □ Readme laborator NU conține cod Java > 5 linii                     [R-LAB-2]

  Pentru exercise1/ (obligatoriu, multi-parte):
    □ Readme.md există                                                  [R-STR-1]
    □ Main.java există                                                  [R-STR-2]
    □ Checker.java există                                               [R-STR-3]
    □ tests/ există și conține NUMAI subdirectoare partX/               [R-STR-4]
    □ Fiecare N.in are N.out în același partX/                          [R-STR-5]
    □ Checker.java apelează IOTest.runParts                             [R-STR-7]
    □ Readme are secțiune de scenariu/problemă                         [R-CON-1]
    □ Readme NU are blocuri Java > 8 linii                             [R-CON-2]
    □ Readme are secțiune „Format input / output"                      [R-CON-4]
    □ Readme are cel puțin un exemplu input/output                     [R-CON-5]
    □ Readme are secțiuni Partea A/B/C corespunzătoare partX/          [R-CON-6]

  Pentru exercise2/ (obligatoriu dacă lab complet, flat):
    □ Readme.md există                                                  [R-STR-1]
    □ Main.java există                                                  [R-STR-2]
    □ Checker.java există                                               [R-STR-3]
    □ tests/ există și conține NUMAI fișiere .in/.out (flat)            [R-STR-4]
    □ Fiecare N.in are N.out în tests/                                  [R-STR-5]
    □ Checker.java apelează IOTest.runFlat                              [R-STR-7]
    □ Readme are secțiune de scenariu care leagă de ex1                [R-CON-1]
    □ Readme are secțiune „Import din exercițiul 1"                    [R-CON-1]
    □ Readme NU are blocuri Java > 8 linii                             [R-CON-2]
    □ Readme are secțiune „Format input / output"                      [R-CON-4]
    □ Readme are cel puțin un exemplu input/output                     [R-CON-5]

  Pentru exercițiul bonus (exercise3 sau exercise2 în lab scurt):
    □ Readme.md există                                                  [R-STR-1]
    □ Main.java există                                                  [R-STR-2]
    □ Checker.java și tests/ NU există                                  [R-STR-8]
    □ Readme are secțiune „Noțiuni demonstrate"                        [R-CON-1]
    □ Readme are secțiune „Cerințe minime"                             [R-CON-1]
    □ Readme are secțiune „Libertate de implementare"                  [R-CON-8]
    □ Readme NU promite teste automate                                  [R-CON-8]
    □ Readme NU are blocuri Java > 8 linii                             [R-CON-2]
```

---

## Note de implementare

- Toate Readme-urile sunt în **română** — agentul nu semnalează lipsa secțiunilor în engleză.
- Convenția de denumire a subdirectoarelor de test este `partA`, `partB`, `partC` (literă mare).
- Prefixele numerice ale testelor (`1.in`, `1.out`) încep de la `1` și sunt consecutive.
- Clasa `IOTest` se află în `src/com/pao/test/IOTest.java`:
  - `IOTest.runParts(String testsDir, MainMethod main)` — pentru teste multi-parte (subdirectoare `partX/`)
  - `IOTest.runFlat(String testsDir, MainMethod main)` — pentru teste flat (fișiere `.in`/`.out` direct)
  - Calea `testsDir` este relativă la rădăcina proiectului (`paoj-2026/`), **nu** față de pachetul curent.
- Exercițiul 2 (lab complet) importă întotdeauna cel puțin un tip din `exercise1`.
- Exercițiul bonus importă din exercițiul precedent și extinde, nu rescrie de la zero.
- Dacă laboratorul este **scurt** (1 obligatoriu + 1 bonus), exercițiul bonus este `exercise2/`, iar `exercise3/` nu există.
- Dacă laboratorul este **complet** (2 obligatorii + 1 bonus), exercițiul bonus este `exercise3/`.
- Bonusul contribuie cu **0.5%** la nota finală de laborator (conform regulilor de notare PAOJ).
- Exercițiul 1 singur poate fi multi-parte cu `IOTest.runParts`, sau cu un singur `partA/` dacă scenariul e simplu.
