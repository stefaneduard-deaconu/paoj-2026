# PAOJ 2026

Proiect educațional pentru cursul **Programare Avansată pe Obiecte în Java**.

Programare Avansată pe Obiecte în Java — materiale și resurse pentru cursul din 2026.

---


## Laboratoare

| Laborator | Subiect |
|-----------|---------|
| [laboratory00](src/com/pao/laboratory00/Readme.md) | Primul program, array-uri, Scanner |
| [laboratory01](src/com/pao/laboratory01/Readme.md) | Clase, încapsulare, Singleton, Comparator |
| [laboratory02](src/com/pao/laboratory02/Readme.md) | Moștenire, clase abstracte, interfețe, equals/hashCode, colecții |
| [laboratory03](src/com/pao/laboratory03/Readme.md) | Map, enum-uri, excepții custom |


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

Pentru a lucra pe un proiect Java real, cel mai important lucru în prezent este să înveți **Spring Boot** pentru backend. Spring Boot este frameworkul dominant în industrie pentru aplicații enterprise Java și este cerut în marea majoritate a anunțurilor de angajare pentru dezvoltatori Java.

Pe lângă Spring Boot, **ingineria cloud** a devenit o competență esențială. Certificările cloud — în special certificările **AWS** (Amazon Web Services) — sunt foarte apreciate de angajatori și îți cresc semnificativ șansele de angajare. Este un domeniu în care eu însumi investesc în prezent, urmând o certificare AWS.

**Pe scurt:**
- Învață **Spring Boot** pentru a construi aplicații backend Java solide.
- Obține o **certificare AWS** (sau alt furnizor cloud) pentru a demonstra competențe în cloud engineering.

---

### 2. Pot îmbina mai mulți comparatori în `Arrays.sort()` pentru a sorta după multiple criterii?

Da! Poți îmbina mai mulți comparatori în Java folosind metoda **`thenComparing()`**, introdusă în Java 8. Aceasta permite crearea unui lanț de criterii de sortare (lexicografice): dacă primul comparator consideră elementele egale, se trece la următorul.

**Metode principale pentru concatenare:**
- `Comparator.comparing()` — creează comparatorul de bază (primul criteriu).
- `.thenComparing()` — adaugă un sub-criteriu evaluat doar dacă rezultatul anterior a fost `0` (egalitate).
- `.reversed()` — inversează ordinea unui comparator specific sau a întregului lanț.

**Exemplu practic:**

Dacă ai o listă de obiecte `Angajat` și vrei să sortezi întâi după `Nume` (criteriu principal), apoi după `Vârstă` (sub-criteriu):

```java
import java.util.Comparator;
import java.util.List;

// Sortează după nume, apoi după vârstă
listaAngajati.sort(
    Comparator.comparing(Angajat::getNume)
              .thenComparing(Angajat::getVarsta)
);
```

**Variante avansate:**
- **Sortare inversă pe un singur criteriu:** Folosește `Comparator.comparing(Angajat::getNume, Comparator.reverseOrder())` pentru a inversa doar primul criteriu, menținând sub-criteriile în ordine naturală.
- **Gestionarea valorilor nule:** Folosește `nullsFirst()` sau `nullsLast()` în interiorul lanțului pentru a evita `NullPointerException`.
- **Metode primitive:** Pentru performanță mai bună, folosește variantele specializate precum `thenComparingInt()` sau `thenComparingLong()` pentru a evita procesul de autoboxing.

## 3. Cum rulez Java din terminal (pentru începători)

### 1. Am Java instalat?

```bash
java -version
javac -version
```

Dacă primești un număr de versiune (ex: `21.0.x`), ești pregătit.  
Dacă nu, descarcă JDK de la [adoptium.net](https://adoptium.net/) și instalează-l.

---

### 2. Compilez un fișier `.java`

```bash
javac NumeleFisierului.java
```

Se generează un fișier `NumeleFisierului.class` în același folder.

---

### 3. Rulez clasa compilată

```bash
java NumeleFisierului
```

> ⚠️ Fără extensia `.class` — scrii doar numele clasei.

---

### 4. Clasa are `package`? Compilez din rădăcina proiectului

Dacă fișierul conține `package com.pao.laboratory00;`, trebuie să lucrezi **din folderul `src/`**:

```bash
cd src
javac com/pao/laboratory00/Main.java
java com.pao.laboratory00.Main
```

> Calea la compilare folosește `/` (sau `\` pe Windows), iar la rulare folosești `.` (puncte).

---

### 5. Compilez mai multe fișiere dintr-un pachet

```bash
javac com/pao/laboratory01/model/Dog.java com/pao/laboratory01/Main.java
```

Sau compilezi tot pachetul dintr-o dată:

```bash
javac com/pao/laboratory01/**/*.java
```

---

### 6. Trimit date de la tastatură (Scanner)

Rulezi normal cu `java ...` și scrii în terminal când programul așteaptă input, apoi apeși **Enter**.

---

### 7. Rezumat rapid

| Pas | Comandă |
|-----|---------|
| Verificare Java | `java -version` |
| Compilare (fără pachet) | `javac Main.java` |
| Rulare (fără pachet) | `java Main` |
| Compilare (cu pachet, din `src/`) | `javac com/pao/lab00/Main.java` |
| Rulare (cu pachet, din `src/`) | `java com.pao.lab00.Main` |

