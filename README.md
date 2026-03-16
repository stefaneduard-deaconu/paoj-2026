# PAOJ 2026

Materiale și resurse pentru cursul **Programare Avansată pe Obiecte în Java** — 2026.

---

## Laboratoare

| Laborator                                          | Subiect                                                          |
|----------------------------------------------------|------------------------------------------------------------------|
| [laboratory00](src/com/pao/laboratory00/Readme.md) | Primul program, array-uri, Scanner                               |
| [laboratory01](src/com/pao/laboratory01/Readme.md) | Clase, încapsulare, Singleton, Comparator                        |
| [laboratory02](src/com/pao/laboratory02/Readme.md) | Moștenire, clase abstracte, interfețe, equals/hashCode, colecții |
| [laboratory03](src/com/pao/laboratory03/Readme.md) | Map, enum-uri, excepții custom |

Începând cu **laboratory04**, soluțiile se trimit pe GitHub la un fork personal al acestui repo.
**Data limită:** miercuri, ora 23:59, în fiecare săptămână.

Mai jos găsești:
1. [Cum trimiți soluțiile](#1-cum-trimiți-soluțiile) — fork, configurare remotes, commit săptămânal
2. [Formularul de înregistrare](#2-completați-url-ul-fork-ului) — link fork personal
3. [Punctarea laboratoarelor](#3-punctarea-laboratoarelor) — prezență, obligatoriu, bonus

---

## 1. Cum trimiți soluțiile

> 🎬 **Video tutorial:** [Cum faci fork și trimiți soluțiile — YouTube](https://www.youtube.com/watch?v=PLACEHOLDER)
>
> Partea 1 - fork, o singură dată la începutul semestrului
> 
> https://youtu.be/ICJUYkHkWr4
> 
> Partea 2 - flux săptămânal pentru fiecare laborator
> 
> https://youtu.be/a27-0an-bTo


### Pre-rechizite

- ✅ Cont pe [github.com](https://github.com) (gratuit)
- ✅ Git instalat — verifică cu `git --version` ([descarcă de aici](https://git-scm.com/downloads) dacă nu ai)
- ✅ Autentificare configurată — [GitHub CLI](https://cli.github.com/) (`gh auth login`) sau SSH key

---

<details open>
<summary><h3>VARIANTA A — ai clonat deja repo-ul cursului</h3></summary>

**1. Creează fork-ul pe GitHub:**

- Deschide [https://github.com/stefaneduard-deaconu/paoj-2026](https://github.com/stefaneduard-deaconu/paoj-2026)
- Click **Fork** (dreapta sus) → **Create fork**
- Acum ai `https://github.com/USERNAME-TĂU/paoj-2026` pe contul tău

**2. Redenumește remote-ul și adaugă fork-ul:**

```bash
cd calea/spre/paoj-2026
git remote rename origin upstream
git remote add origin https://github.com/USERNAME-TĂU/paoj-2026.git
```

**3. Push inițial:**

```bash
git push -u origin main
```

**4. Verifică:**

```bash
git remote -v
# origin    https://github.com/USERNAME-TĂU/paoj-2026.git             (fork-ul tău)
# upstream  https://github.com/stefaneduard-deaconu/paoj-2026.git     (repo-ul cursului)
```

✅ **Gata!** Configurația ta e identică cu Varianta B.

</details>

<details>
<summary><h3>VARIANTA B — nu ai clonat încă repo-ul</h3></summary>

**1. Creează fork-ul și clonează-l:**

- Deschide [https://github.com/stefaneduard-deaconu/paoj-2026](https://github.com/stefaneduard-deaconu/paoj-2026)
- Click **Fork** → **Create fork**

```bash
git clone https://github.com/USERNAME-TĂU/paoj-2026.git
cd paoj-2026
```

**2. Adaugă repo-ul cursului ca `upstream`:**

```bash
git remote add upstream https://github.com/stefaneduard-deaconu/paoj-2026.git
```

**3. Verifică:**

```bash
git remote -v
# origin    https://github.com/USERNAME-TĂU/paoj-2026.git             (fork-ul tău)
# upstream  https://github.com/stefaneduard-deaconu/paoj-2026.git     (repo-ul cursului)
```

</details>

---

### Flux săptămânal

**1. Preiei branch-ul nou de pe `upstream`:**

```bash
git fetch upstream labX   # înlocuiește X cu numărul lab (ex: lab04)
git checkout -b labX --track upstream/labX
git push -u origin labX   # setează origin/labX ca remote tracking
```

> Aceasta creează un branch local `labX` care urmărește `upstream/labX`. Puteți folosi `-u` later dacă nu ați folosit `--track`.

**2. Lucrează** la exerciții — creează clase, completează TODO-uri.

**3. Salvează și trimiți soluția:**

```bash
git add .
git commit -m "Lab04: exercitiile 1-4 completate"
git push -u origin labX
```

**4. Submit** — trimite link-ul fork-ului pe platforma de curs.

### Greșeli frecvente

| Problemă | Soluție |
|----------|---------|
| `fatal: remote upstream already exists` | Upstream e deja adăugat — skip acest pas |
| `fatal: refusing to merge unrelated histories` | `git merge upstream/main --allow-unrelated-histories` |
| Conflicte la merge | `git status` → rezolvă fișierele → `git add .` → `git commit` |
| Push rejected | `git pull origin main --rebase` apoi `git push` |

## 2. Completați URL-ul fork-ului

Înregistrează-te cu link-ul fork-ului personal și alege proiectul de laborator:

[https://forms.gle/zKPvTiP3oTJrxhR19](https://forms.gle/zKPvTiP3oTJrxhR19)


## 3. Punctarea laboratoarelor

> ❗ Orice procent este relativ la nota finală de la PAOJ.
> ❗ în săptămâna 14, dupa corectarea proiectului, vei cumula un număr de procente, din care se calculează o notă între 1 și 10. Nu rotunjim în calcul, pentru că notăm 10/14 laboratoare și oferim feedback oricând doriți pentru proiect!
>  E.g. obții 48%, de aici 48/50*9 + 1 = 9.64, fără rotunjire va fi 9


### Structura notei finale

Iată varianta rescrisă:

---

## 3. Punctarea laboratoarelor

> ❗ Toate procentele sunt relative la nota finală PAOJ.

### Structura notei finale

| Componentă | Pondere |
|---|---|
| Proiect individual | 25% |
| Laboratoare | 25% |

**Total maxim posibil: 50%**

### Calculul notei

La finalul semestrului (săptămâna 14), procentele acumulate se convertesc într-o notă de la 1 la 10 folosind formula:

$$\text{notă} = \frac{\text{procente acumulate}}{50} \times 9 + 1$$

> **Exemplu:** 48% acumulate → $\frac{48}{50} \times 9 + 1 = 9.64$ → nota finală: **9** (fără rotunjire)

### Notarea laboratoarelor

Din cele 14 laboratoare, se iau în calcul **cele mai bune 10**, fiecare valorând **2.5%** din nota finală.

**Laboratoarele 1–3** — 2.5% fiecare, fără exercițiu bonus.

**Laboratoarele 4–14** — structură în două părți:

| Ce rezolvi | Punctaj |
|---|---|
| Prezență + exerciții obligatorii | 1.5% |
| Exercițiul bonus | 1.0% |

---

## Cum trimiți soluțiile (începând cu laboratory03)

[//]: # (> 🎬 **Video tutorial:** [Cum faci fork și trimiți soluțiile — YouTube]&#40;https://www.youtube.com/watch?v=PLACEHOLDER&#41;)

### Pentru laboratoarele online din saptamanile 4-14

#### Prezenta 

-> se deduce din submit-ul saptamanal, plus prezentarea o data la 2 saptamani a ce ati lucrat (online/fizic)

#### Punctajul

-> in laboratoarele 4-14 aveti si exercitii bonus, care valoreaza 2.5% din punctajul total al prezentei+activitate.

Reminder:
Prezenta + activitate -> 25%, din care 12.5% pentru prezenta, 7.5% daca rezolvati cel putin exercitiile obligatorii, si 5% daca rezolvati si exercitiile bonus.
Proiect -> 25%

### Pre-rechizite

- ✅ Cont pe [github.com](https://github.com) (gratuit)
- ✅ Git instalat — `git --version` în terminal ([descarcă de aici](https://git-scm.com/downloads) dacă nu ai)
- ✅ Autentificare configurată — recomand [GitHub CLI](https://cli.github.com/) (`gh auth login`) sau SSH key

---

**1. Fork** — creează-ți propria copie a repo-ului:
- Deschide repo-ul cursului pe GitHub
- Click **Fork** (dreapta sus) → **Create fork**
- Acum ai `https://github.com/USERNAME-UL-TAU/paoj-2026`

**2. Schimbi `origin` să pointeze spre fork-ul tău:**
```bash
cd C:\Users\stefan\path\to\paoj-2026  # sau unde ai tu clona locală
git remote rename origin upstream
git remote add origin https://github.com/USERNAME-UL-TAU/paoj-2026.git
```

**3. Push-ul inițial către fork:**
```bash
git push -u origin main
```

**4. Verifică:**
```bash
git remote -v
# origin    https://github.com/USERNAME-UL-TAU/paoj-2026.git          (fork-ul tău)
# upstream  https://github.com/stefaneduard-deaconu/paoj-2026.git    (repo-ul cursului)
```

✅ **Gata!** Acum ai aceeași configurație ca cei care încep de la zero.

---

### 🆕 VARIANTA B — Începi acum (studenți noi)

**1. Fork + clone:**
- Deschide [https://github.com/stefaneduard-deaconu/paoj-2026](https://github.com/stefaneduard-deaconu/paoj-2026)
- Click **Fork** → **Create fork**
- Clonează fork-ul tău:
```bash
git clone https://github.com/USERNAME-UL-TAU/paoj-2026.git
cd paoj-2026
```

**2. Adaugă repo-ul cursului ca `upstream`:**
```bash
git remote add upstream https://github.com/stefaneduard-deaconu/paoj-2026.git
```

**3. Verifică:**
```bash
git remote -v
# origin    https://github.com/USERNAME-UL-TAU/paoj-2026.git          (fork-ul tău)
# upstream  https://github.com/stefaneduard-deaconu/paoj-2026.git    (repo-ul cursului)
```

---

### 📅 Flux săptămânal (pentru TOȚI studenții)

**1. Actualizează** cu materialele noi de la curs:
```bash
git fetch upstream labX  # e.g. lab4 for laboratory03 of March 16-17th
git checkout labX        # e.g. lab4 for laboratory03 of March 16-17th
```

**2. Lucrează** la exerciții — creează clase, completează TODO-uri.

**3. Salvează** progresul:
```bash
git add .
git commit -m "Lab4: exercitiile 1-4 completate"
git push origin labX  # origin is your fork.
```

**4. Submit** — link către fork-ul tău pe platforma de curs.

---

## Întrebări frecvente (FAQ)

### 1. Cum pot să obțin un job pe un proiect Java?

Cel mai important lucru în prezent este **Spring Boot** — frameworkul dominant pentru aplicații enterprise Java, cerut în marea majorității anunțurilor de angajare.

Pe lângă asta, **ingineria cloud** e esențială. Certificările **AWS** sunt foarte apreciate și cresc șansele de angajare — un domeniu în care investesc și eu.

**Pe scurt:**
- **Spring Boot** — aplicații backend Java solide
- **Certificare AWS** — competențe cloud

---

### 2. Pot îmbina mai mulți comparatori în `Arrays.sort()` pentru a sorta după multiple criterii?

Da, folosind **`thenComparing()`** (Java 8+). Dacă primul comparator consideră elemente egale, se trece la următorul criteriu.

**Metode principale:**
- `Comparator.comparing()` — primul criteriu
- `.thenComparing()` — criteriu secundar (la egalitate)
- `.reversed()` — inversează ordinea

**Exemplu:**

```java
listaAngajati.sort(
    Comparator.comparing(Angajat::getNume)
              .thenComparing(Angajat::getVarsta)
);
```

**Variante utile:**
- Inversare: `Comparator.comparing(Angajat::getNume, Comparator.reverseOrder())`
- Valori null: `nullsFirst()` / `nullsLast()`
- Performanță: `thenComparingInt()` / `thenComparingLong()` evită autoboxing

### 3. Cum rulez Java din terminal?

**Am Java instalat?**

```bash
java -version
javac -version
```

Dacă primești un număr de versiune (ex: `21.0.x`), ești pregătit.
Dacă nu, descarcă JDK de la [adoptium.net](https://adoptium.net/).

**Compilare și rulare:**

```bash
javac NumeleFisierului.java   # generează NumeleFisierului.class
java NumeleFisierului         # fără extensia .class
```

**Clasa are `package`? Lucrează din `src/`:**

```bash
cd src
javac com/pao/laboratory00/Main.java
java com.pao.laboratory00.Main
```

> Compilarea folosește `/` (sau `\` pe Windows), rularea folosește `.` (puncte).

**Rezumat rapid:**

| Acțiune | Comandă |
|---------|---------|
| Verificare Java | `java -version` |
| Compilare (fără pachet) | `javac Main.java` |
| Rulare (fără pachet) | `java Main` |
| Compilare (cu pachet, din `src/`) | `javac com/pao/lab00/Main.java` |
| Rulare (cu pachet, din `src/`) | `java com.pao.lab00.Main` |

