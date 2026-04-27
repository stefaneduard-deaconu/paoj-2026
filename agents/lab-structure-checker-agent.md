# lab-structure-checker-agent.md

> ⚠️ **DEPRECAT** — Logica de validare structurală a fost mutată și actualizată în
> [`ex1-ex2-bonus-agent.md`](ex1-ex2-bonus-agent.md) (Faza 1 — Validare structurală).
>
> Folosește `ex1-ex2-bonus-agent.md` pentru orice creare sau validare de laborator.
> Acest fișier este păstrat doar ca referință istorică și **nu mai trebuie actualizat**.

---

# ~~README.agent.md — Ghid de validare automată pentru laboratoare PAOJ~~

~~Acest fișier descrie **regulile structurale și de conținut** ale laboratoarelor din
acest repository și oferă un set de verificări pe care un agent AI le poate executa
automat pentru a valida că un laborator respectă convențiile.~~

---

## Descriere agent

**Scop:** Validează că un laborator (`laboratoryNN/`) respectă toate convențiile
structurale, de testare și de conținut ale acestui repository.

**Intrare:** Calea către un director de laborator (ex: `src/com/pao/laboratory06/`).

**Ieșire:** Un raport cu `[OK]` / `[WARN]` / `[FAIL]` pentru fiecare regulă,
urmat de un sumar cu numărul total de probleme găsite.

**Mod de rulare:** Agentul parcurge directorul dat recursiv, citește fișierele
Markdown și Java relevante, și verifică fiecare regulă din lista de mai jos.

---

## Reguli structurale

### R-STR-1: Fiecare exercițiu are propriul `Readme.md`
- Fiecare subdirector `exerciseN/` din laborator **trebuie** să conțină un fișier `Readme.md`.
- `[FAIL]` dacă lipsește.

### R-STR-2: Fiecare exercițiu are un `Main.java`
- Fiecare subdirector `exerciseN/` trebuie să conțină `Main.java`.
- `[FAIL]` dacă lipsește.

### R-STR-3: Exercițiile cu teste automate au `Test.java` și directorul `tests/`
- Dacă există `Test.java`, trebuie să existe și `tests/`.
- Dacă există `tests/`, trebuie să existe și `Test.java`.
- `[FAIL]` dacă una există fără cealaltă.

### R-STR-4: Directorul `tests/` conține **numai** subdirectoare `partX/`
- Nu trebuie să existe fișiere `.in` / `.out` direct în `tests/` (flat layout e interzis).
- Subdirectoarele trebuie să fie denumite `partA`, `partB`, `partC`, ... (alfabetic).
- `[FAIL]` dacă există fișiere `.in` sau `.out` direct în `tests/`.
- `[WARN]` dacă subdirectoarele nu urmează schema `part[A-Z]+`.

### R-STR-5: Fiecare `.in` are un `.out` corespondent și invers
- Pentru fiecare `tests/partX/N.in` trebuie să existe `tests/partX/N.out`.
- Pentru fiecare `tests/partX/N.out` trebuie să existe `tests/partX/N.in`.
- `[FAIL]` dacă există fișiere orfane.

### R-STR-6: Laboratorul are un `Readme.md` la nivel de laborator
- Fișierul `laboratoryNN/Readme.md` trebuie să existe.
- `[FAIL]` dacă lipsește.

### R-STR-7: `Test.java` apelează `IOTest.runParts`, nu `IOTest.runAll`
- Conținutul `Test.java` trebuie să conțină `IOTest.runParts(`.
- `[FAIL]` dacă conține `IOTest.runAll(`.
- `[FAIL]` dacă nu conține nicio referință la `IOTest`.

### R-STR-8: Exercițiile 3 și 4 nu au teste automate
- Subdirectoarele `exercise3/` și `exercise4/` nu trebuie să conțină `Test.java`
  sau directorul `tests/`.
- `[WARN]` dacă există (nu e greșeală fatală, dar nu e convenția).

---

## Reguli de conținut pentru `Readme.md` per exercițiu

### R-CON-1: Readme-ul descrie un **scenariu / problemă**, nu o soluție
- Readme-ul trebuie să aibă o secțiune cu un scenariu narativ (cuvânt cheie:
  „Scenariul" sau „Context" sau o descriere în proză a problemei).
- `[FAIL]` dacă Readme-ul începe direct cu liste de clase/interfețe de creat
  fără nicio introducere a problemei.

### R-CON-2: Nu există blocuri de cod Java cu implementări complete de clase
- Readme-ul **nu** trebuie să conțină blocuri ` ```java ` cu corpuri complete de
  clase (câmpuri listate, constructori gata scrisi, `@Override` complet implementate).
- Blocuri scurte de **semnătură** (ex: `public interface X { void metoda(); }`) sunt permise.
- `[FAIL]` dacă un bloc Java depășește 8 linii de cod efectiv (non-comentariu, non-blank).
- `[WARN]` dacă un bloc Java conține atribute private cu tip și nume (`private String nume;`).

### R-CON-3: Există hint-uri, nu soluții
- Hints-urile trebuie să menționeze concepte sau interfețe standard
  (`Comparable`, `Cloneable`, `sealed`, etc.), **nu** să dicteze codul exact.
- `[WARN]` dacă un hint conține un bloc de cod mai lung de 3 linii.

### R-CON-4: Formatul I/O este specificat pentru exercițiile cu teste automate
- Dacă exercițiul are `Test.java`, Readme-ul trebuie să conțină o secțiune
  „Format input" (sau echivalent) cu descrierea formatului stdin/stdout.
- `[FAIL]` dacă lipsește această secțiune.

### R-CON-5: Există cel puțin un exemplu de input/output
- Readme-ul trebuie să conțină cel puțin un exemplu concret cu input și output așteptat.
- `[WARN]` dacă nu există niciun exemplu.

### R-CON-6: Fiecare parte (`Partea A / B / C`) este documentată în Readme
- Pentru fiecare subdirector `partX/` din `tests/`, Readme-ul trebuie să aibă
  o secțiune corespunzătoare (`## Partea A`, `## Partea B`, etc.).
- `[FAIL]` dacă un subdirector `partX/` nu are secțiune în Readme.

### R-CON-7: Readme-ul nu specifică atribute private exacte ale claselor
- Expresii de tipul `private String nume;`, `private double salariu;` etc. nu trebuie
  să apară în afara blocurilor de exemplu-semnătură scurtă.
- `[WARN]` dacă sunt găsite astfel de linii în afara unui bloc de cod.

---

## Reguli pentru `Readme.md` la nivel de laborator

### R-LAB-1: Există un tabel de exerciții cu linkuri la Readme-urile per exercițiu
- Tabelul trebuie să conțină linkuri Markdown (`[exercise1/](exercise1/Readme.md)`)
  spre fiecare `exerciseN/Readme.md`.
- `[FAIL]` dacă lipsesc linkurile.

### R-LAB-2: Readme-ul de laborator nu conține cerințe detaliate per exercițiu
- Secțiunile cu cod Java complet sau cu liste de atribute nu trebuie să apară
  în Readme-ul de laborator — acestea aparțin Readme-ului per exercițiu.
- `[WARN]` dacă există blocuri Java > 5 linii în Readme-ul de laborator.

### R-LAB-3: Există instrucțiuni de rulare a testelor
- Readme-ul de laborator trebuie să descrie cum se rulează `Test.java`.
- `[WARN]` dacă lipsesc instrucțiunile.

---

## Checklist rapid (pentru validare manuală sau automatizată)

```
Pentru fiecare laboratoryNN/:
  □ laboratoryNN/Readme.md există                                    [R-LAB-1]
  □ Readme laborator conține tabel cu linkuri spre exerciții         [R-LAB-1]
  □ Readme laborator are instrucțiuni de rulare teste                [R-LAB-3]
  □ Readme laborator NU conține cod Java > 5 linii                   [R-LAB-2]

  Pentru fiecare exerciseM/:
    □ exerciseM/Readme.md există                                     [R-STR-1]
    □ exerciseM/Main.java există                                     [R-STR-2]
    □ Readme are secțiune de scenariu/problemă                       [R-CON-1]
    □ Readme NU are blocuri Java > 8 linii                           [R-CON-2]
    □ Hints menționează concepte, nu cod                             [R-CON-3]

    Dacă exerciseM/ are Test.java:
      □ tests/ există                                                [R-STR-3]
      □ Test.java apelează IOTest.runParts                           [R-STR-7]
      □ tests/ conține NUMAI subdirectoare partX/                    [R-STR-4]
      □ Fiecare N.in are N.out în același partX/                     [R-STR-5]
      □ Readme are secțiune „Format input"                           [R-CON-4]
      □ Readme are cel puțin un exemplu input/output                 [R-CON-5]
      □ Readme are secțiuni Partea A/B/C corespunzătoare partX/      [R-CON-6]

    Dacă exerciseM este exercise3 sau exercise4:
      □ Test.java și tests/ NU există                                [R-STR-8]
```

---

## Note de implementare pentru agent

- Toate Readme-urile sunt în **română** — agentul nu trebuie să semnaleze
  lipsa secțiunilor în engleză.
- Convenția de denumire a subdirectoarelor este `partA`, `partB`, `partC` (literă mare).
- Prefixele numerice ale testelor (`1.in`, `2.in`, ...) trebuie să fie consecutive
  și să înceapă de la `1`.
- Clasa `IOTest` se află în `src/com/pao/test/IOTest.java` și oferă metodele
  `runParts(String testsDir, MainMethod main)` și
  `runPart(String testsDir, String partName, MainMethod main)`.
- `Test.java` din fiecare exercițiu apelează `IOTest.runParts` cu calea relativă
  față de rădăcina proiectului (`paoj-2026/`), nu față de pachetul curent.

