# Plan noțiuni — Laboratory 09 & Laboratory 10

> **Generat:** 26 aprilie 2026  
> **Surse:** `temporarily_curs/curs08-summary.md`, `curs09-summary.md`, `curs10-summary.md`  
> **Crosschecked cu:** `src/com/pao/laboratory01` → `laboratory08` (toate fișierele `.java` și `Readme.md`)  
> **Fișier detaliat:** `temporarily_curs/lab09-lab10-plan.md`

---

## Principiu de lucru

> Orice noțiune deja implementată în lab-urile anterioare **nu se mai predă ca noțiune nouă**.  
> Dacă apare din nou, este doar **recapitulare / aprofundare** — nu exercițiu nou dedicat.

---

## Ce s-a acoperit deja (Labs 01–08)

| Categorie | Noțiuni acoperite | Lab principal |
|-----------|-------------------|---------------|
| I/O text | `FileReader/Writer`, `BufferedReader/Writer` | Lab08 ✅ |
| I/O binar | `FileInputStream/OutputStream` (teorie + exemplu) | Lab08 ✅ |
| Clonare | `Cloneable`, shallow/deep clone | Lab08 ✅ |
| I/O avansat | `Serializable`, `DataInput/OutputStream`, `RandomAccessFile`, `ByteBuffer`, `try-with-resources` | Lab08 🔄 *previewed only* |
| Colecții | `ArrayList`, `HashSet`, `TreeSet` | Lab03 ✅ |
| Map | `HashMap`, `TreeMap` | Lab04 ✅ |
| Sortare | `Comparable`, `Comparator` | Lab02, Lab05, Lab06 ✅ |
| OOP avansat | `equals`/`hashCode`, `record`, sealed classes, interfețe avansate | Lab03–07 ✅ |
| Enum | Simplu + avansat (state machine, metode abstracte) | Lab04, Lab07 ✅ |
| Excepții | Custom, checked/unchecked, `try-catch-finally` | Lab04 ✅ |
| Pattern-uri | Singleton, Command, undo/redo | Lab02, Lab04, Lab07 ✅ |
| **Threads** | **—** | ❌ neacoperit |
| `LinkedList`, `LinkedHashSet` | **—** | ❌ neacoperit |
| `Iterator` explicit, `itr.remove()` | **—** | ❌ neacoperit |
| `ConcurrentModificationException` | **—** | ❌ neacoperit |
| `Collections` utilitar | **—** | ❌ neacoperit |
| Stream API | Lab07 bonus Readme (zero Java implementat) | ❌ neimplementat |

---
---

# LABORATORY 09

> **Cursuri sursă:** Curs 08 (I/O avansat) + Curs 09 (Serializare + Threads)  
> **Timp estimat:** ~2h–2h30 min

## Ce este cu adevărat nou în Lab 09

### A. I/O avansat — prima implementare practică

Toate aceste noțiuni au fost *previzualizate* în `laboratory08/theory1/Readme.md` dar **nu implementate**:

| Noțiune | Ce face | Nou față de Lab08 |
|---------|---------|-------------------|
| `Serializable` + `serialVersionUID` + `transient` | Salvare/restaurare obiecte întregi în fișier binar | Prima implementare |
| `ObjectOutputStream.writeObject()` / `ObjectInputStream.readObject()` | Serializare/deserializare efectivă | Prima implementare |
| `Externalizable` (`writeExternal`/`readExternal`) | Serializare controlată de programator; poate salva statice, cripta date | Complet nou |
| `DataOutputStream.writeInt/Double/UTF()` | Scriere tipuri primitive în binar | Prima implementare |
| `DataInputStream.readInt/Double/UTF()` | Citire tipuri primitive din binar (ordine identică scrierii!) | Prima implementare |
| `RandomAccessFile("fisier","rw")` | Acces aleatoriu la orice octet din fișier | Prima implementare |
| `seek(pos)`, `getFilePointer()`, `skipBytes(n)` | Navigare cursor în fișier | Prima implementare |
| BMP header + pixeli BGR + padding + little-endian | Prelucrare imagine bitmap reală | Prima implementare |
| `Integer.reverseBytes()` / `ByteBuffer.order(LITTLE_ENDIAN)` | Conversie endianness | Prima implementare |
| `try-with-resources` (predat explicit) | Închidere automată `AutoCloseable`; resurse multiple în ordine inversă | Folosit implicit în Lab08, nepredat |

**Capcane de reținut:**
- `DataInputStream` citește **exact în ordinea** în care `DataOutputStream` a scris
- Java = big-endian; BMP/Windows = little-endian → conversie obligatorie
- Dacă o clasă serializabilă referențiază o altă clasă, **aceasta trebuie să fie și ea `Serializable`**
- `serialVersionUID` lipsă → JVM calculează automat (instabil) → risc de `InvalidClassException`
- `transient` → câmpul primește valoare implicită (`null`/`0`/`false`) la deserializare
- `Externalizable` pierde graful de dependențe automat → programatorul îl gestionează manual

---

### B. Fire de executare — Threads (complet nou)

**Sursă:** Curs 09, pag. 8–22

| Concept | Detalii cheie |
|---------|---------------|
| **Creare fir** | `extends Thread` (simplu) sau `implements Runnable` (preferată — nu blochează ierarhia) |
| **`start()` vs `run()`** | `start()` = crează stivă nouă + execută `run()` pe fir separat; `run()` direct = execuție în firul curent |
| **Ciclu de viață** | `new → runnable → running → blocked/waiting → dead` |
| **`Thread.sleep(ms)`** | Suspendă firul curent; aruncă `InterruptedException` |
| **`join()`** | Firul apelant așteaptă terminarea acestui fir |
| **`interrupt()` + `Thread.interrupted()`** | Întrerupere forțată; alternativă la `volatile boolean stop` |
| **`volatile`** | Forțează citirea variabilei din memoria principală (nu din cache-ul local al firului) |
| **`synchronized`** | Excludere reciprocă: metodă (`synchronized void add()`) sau bloc (`synchronized(this){...}`) |
| **`wait()` / `notify()` / `notifyAll()`** | Cooperare: `wait()` eliberează lock-ul și suspendă; `notify()` trezește un fir din wait |
| **Producător–Consumator** | Exemplu clasic: bandă comună de lungime fixă, `wait()` când plină/goală, `notify()` după fiecare acțiune |

**Capcane de reținut:**
- Apelul `f.run()` în loc de `f.start()` → **nu** se creează fir nou; tot codul rulează secvențial
- `synchronized` pe metodă nestatică → lock pe **obiect**; pe metodă statică → lock pe **clasă**
- `wait()` și `notify()` se apelează **numai în bloc `synchronized`**, altfel `IllegalMonitorStateException`
- Variabila de control externă unui fir **trebuie** declarată `volatile`, altfel firul poate lucra cu o copie locală depășită

---

## Structura exercițiilor — Lab 09

| Exercițiu | Concept principal | Timp | Teste |
|-----------|-------------------|------|-------|
| **Ex 1** (obligatoriu) | `Serializable` + `serialVersionUID` + `transient` + `ObjectOutputStream`/`ObjectInputStream` — domeniu Student cu Adresă | ~45 min | ✓ automat |
| **Ex 2** (obligatoriu) | `RandomAccessFile` + BMP (sepia sau complement) + endianness + `ByteBuffer` | ~45 min | ✓ automat |
| **Ex 3** (bonus) | Threads — `synchronized` + Producător-Consumator cu bandă de lungime fixă | ~30 min | manual |

> `DataInputStream`/`DataOutputStream` și `Externalizable` → integrabile în Ex 1 sau teoria de punte spre Lab10.

---

## Preview Lab 10 (de menționat la finalul Lab 09)

- `LinkedList` — când e preferabilă față de `ArrayList`
- `Iterator` explicit + `itr.remove()` — singura metodă sigură de ștergere în iterație
- `ConcurrentModificationException` — legătura cu `synchronized` din Threads
- `Collections` utilitar — `sort`, `reverse`, `min`, `max`, `shuffle`

---
---

# LABORATORY 10

> **Curs sursă:** Curs 10 — Java Collections Framework  
> **Timp estimat:** ~2h–2h30 min

## Ce este cu adevărat nou în Lab 10

> `ArrayList`, `HashSet`, `TreeSet`, `HashMap`, `TreeMap`, `Comparable`, `Comparator`, `equals`/`hashCode`, `enhanced-for` → **deja acoperite în Lab03–04 — nu se mai predă ca noțiuni noi**.

| Noțiune | Ce face | Nou față de Lab03/04 |
|---------|---------|----------------------|
| **`LinkedList`** | Listă dublu înlănțuită; metode `addFirst/Last`, `getFirst/Last`, `removeFirst/Last` | Complet nou |
| **`LinkedHashSet`** | Ca `HashSet` (fără duplicate, O(1)) dar **păstrează ordinea inserării** | Complet nou |
| **`Iterator` explicit** | `hasNext()`, `next()`, `itr.remove()` — singura ștergere sigură în iterație | Complet nou (doar `enhanced-for` a fost folosit) |
| **`ConcurrentModificationException`** | Apare la modificare colecție în timp ce e parcursă (fail-fast iterator); și în multi-thread | Complet nou; pune pod cu Lab09 Threads |
| **`Collections` utilitar** | `sort`, `reverse`, `shuffle`, `min`, `max`, `frequency`, `unmodifiableList` | Complet nou |
| **`Vector` / `Stack`** | `Vector` = ArrayList sincronizat; `Stack` = LIFO extinde Vector | Complet nou (recunoaștere, nu exercițiu principal) |
| **Stream API** (bonus) | `stream().filter().map().sorted().collect()` | Apare în Lab07 bonus Readme, zero implementat |

### Comparație `ArrayList` vs `LinkedList`

| Operație | `ArrayList` | `LinkedList` |
|----------|-------------|--------------|
| `get(i)` | **O(1)** | O(n) |
| `add()` la final | O(1) amortizat | **O(1)** |
| `add(i, e)` la poziție | O(n) | O(n) |
| `remove(i)` | O(n) | O(n) |
| **Când preferi** | Acces frecvent prin index | Inserare/ștergere la capete |

### `Iterator` vs `enhanced-for` — de ce contează

```java
// ❌ ConcurrentModificationException — enhanced-for e fail-fast
for (Integer x : lista)
    if (x % 2 == 0) lista.remove(x);

// ✅ Corect — itr.remove() e singurul mod sigur
Iterator<Integer> itr = lista.iterator();
while (itr.hasNext())
    if (itr.next() % 2 == 0) itr.remove();
```

### Clasa `Collections` — metode cheie

```java
Collections.sort(list);                    // sortare naturală (Comparable)
Collections.sort(list, comparator);        // sortare cu Comparator custom (refolosești din Lab02/05/06!)
Collections.reverse(list);                // inversare
Collections.shuffle(list);                // amestecare aleatorie
Collections.min(col); Collections.max(col); // extremele
Collections.frequency(col, elem);         // câte apariții
Collections.unmodifiableList(list);        // view read-only
```

**Capcane de reținut:**
- `HashSet` nu garantează ordinea; `LinkedHashSet` garantează ordinea inserării; `TreeSet` garantează ordinea sortată
- Dacă nu rescrii `hashCode()` consistent cu `equals()` → `HashSet`/`HashMap` inserează duplicate aparente
- `ConcurrentModificationException` apare și **single-thread** când modifici o colecție în `enhanced-for`
- Cheile `HashMap`/`TreeMap` trebuie să fie **imutabile** sau cel puțin să nu se modifice după inserare

---

## Structura exercițiilor — Lab 10

| Exercițiu | Concept principal | Timp | Teste |
|-----------|-------------------|------|-------|
| **Ex 1** (obligatoriu) | `LinkedList` + `Iterator` + `itr.remove()` — gestiune coadă/stivă de elemente | ~40 min | ✓ automat |
| **Ex 2** (obligatoriu) | `LinkedHashSet` + `ConcurrentModificationException` + `Collections` — procesare cuvinte distincte dintr-un fișier | ~40 min | ✓ automat |
| **Ex 3** (bonus) | Stream API — `filter`, `map`, `sorted`, `collect` pe colecții existente | ~30 min | manual |

---

## Conexiuni cu laboratoarele anterioare

| Noțiune Lab 10 | Se construiește pe |
|----------------|-------------------|
| `LinkedList` | Lab03: `ArrayList` — contrast structural |
| `LinkedHashSet` | Lab03: `HashSet` — contrast comportament ordine |
| `Iterator.remove()` | Lab03+: `enhanced-for` — limitare explicată |
| `ConcurrentModificationException` | Lab09: Threads + `synchronized` — cauza în multi-thread |
| `Collections.sort(list, comp)` | Lab02, Lab05, Lab06: `Comparator` — refolosire directă |
| Stream API (bonus) | Lab07: exercise3 bonus Readme — finalizare |

---

## Rezumat rapid — ce e nou în fiecare lab

### Lab 09 — prima implementare pentru:
- `Serializable` + `ObjectOutputStream`/`ObjectInputStream`
- `Externalizable`
- `DataInputStream`/`DataOutputStream`
- `RandomAccessFile` + BMP + endianness + `ByteBuffer`
- `try-with-resources` (predat explicit)
- **Threads complet** (`Thread`/`Runnable`, `synchronized`, `volatile`, `wait`/`notify`, Producător-Consumator)

### Lab 10 — complet nou față de toți labs anteriori:
- `LinkedList`
- `LinkedHashSet`
- `Iterator` explicit + `itr.remove()`
- `ConcurrentModificationException`
- `Collections` utilitar
- `Vector`/`Stack` (recunoaștere)
- Stream API (bonus)

---

## Mapare cursuri → laboratoare

| Curs PDF | Conținut | Lab |
|----------|----------|-----|
| Curs 08 | I/O streams, BMP, `RandomAccessFile`, endianness | Lab08 (text) + **Lab09** (binar/aleatoriu) |
| Curs 09 | Serializare, Externalizare, **Threads** | **Lab09** |
| Curs 10 | Collections Framework | **Lab10** (filtrat: doar ce nu s-a făcut în Lab03–04) |

---
---

# PLAN CONCRET — Exerciții Lab09 + Lab10

> **Tema unificată:** „BancaDigitala — procesare tranzacții bancare și generare extrase de cont"  
> **Generat:** 27 aprilie 2026  
> **Notă deviație:** Exercițiul BMP din planul inițial este înlocuit cu un **registru binar cu acces aleatoriu** (aceleași noțiuni: `RandomAccessFile`, `ByteBuffer`, endianness, `DataOutputStream`) — integrat tematic cu banca și auto-testabil (output text, nu binar). BMP → registru fix-record.

---

## Domeniu comun

Ambele laboratoare descriu același sistem bancar simplificat. Clasele de bază:

```
TipTranzactie { CREDIT, DEBIT }
Tranzactie { int id; double suma; String data; String contSursa; String contDestinatie; TipTranzactie tip; }
```

- **Lab09** definește aceste clase, adaugă persistență binară + procesare concurentă.
- **Lab10** redefinește `Tranzactie` local (independență — nu import cross-lab) și adaugă analiză colecții + rapoarte.

**Mesaj narativ lab→lab:** La Lab09 studenții salvează un istoric de tranzacții pe disc. La Lab10, istoricul este deja disponibil; acum trebuie analizat, deduplicat și raportat pe luni.

---

## LAB 09 — Tip: Complet (Ex1 + Ex2 + Bonus)

> **Curs:** 08 (I/O binar) + 09 (Serializare, Threads)
> **Timp estimat:** ~1h50 min (fără bonus) · ~2h20 min (cu bonus)

---

### Exercițiul 1 — Serializare istoricul tranzacțiilor

> **Pachet:** `com.pao.laboratory09.exercise1`
> **Timp:** ~45 min · **Teste automate:** `IOTest.runParts` (3 părți)
> **Noțiuni noi:** `Serializable`, `serialVersionUID`, `transient`, `ObjectOutputStream`, `ObjectInputStream`, `try-with-resources`

**Scenariu:** Banca salvează un lot de tranzacții zilnice într-un fișier binar și le restaurează ulterior. Câmpul `note` (nota internă a sistemului) este `transient` — nu se persistă, devine `null` după deserializare.

**Clase de creat:**
- `enum TipTranzactie { CREDIT, DEBIT }`
- `class Tranzactie implements Serializable`:
  - `static final long serialVersionUID = 1L`
  - `int id`, `double suma`, `String data` (yyyy-MM-dd), `String contSursa`, `String contDestinatie`, `TipTranzactie tip`
  - `transient String note` — setată la `"procesat"` înainte de serializare, devine `null` la deserializare
- `Main.java` cu protocol comenzi

**Fișier intermediar:** `output/lab09_ex1.ser` (relativ la rădăcina proiectului; suprascris la fiecare rulare)

**Protocol stdin:**
```
<N>
<id> <suma> <data(yyyy-MM-dd)> <contSursa> <contDestinatie> <tip(CREDIT|DEBIT)>
... (N linii)
<comandă>*
QUIT
```

**Comenzi disponibile:**

| Comandă | Output |
|---------|--------|
| `LIST` | Toate tranzacțiile deserializate, câte una pe linie |
| `FILTER <yyyy-MM>` | Tranzacțiile cu `data` care începe cu `yyyy-MM` |
| `NOTE <id>` | `NOTE[<id>]: <valoarea câmpului note>` (va fi `null` după deserializare) |

**Format linie tranzacție:**
```
[<id>] <data> <tip>: <suma:.2f> RON | <contSursa> -> <contDestinatie>
```

**Structura testelor:**

| Parte | Comenzi folosite | Ce verifică |
|-------|-----------------|-------------|
| partA | `LIST`, `QUIT` | Serializare + deserializare completă; ordinea tranzacțiilor păstrată |
| partB | `FILTER yyyy-MM`, `QUIT` | Filtrare lunară; fără rezultate când luna nu există |
| partC | `NOTE <id>`, `QUIT` | Câmpul `transient note` este `null` după deserializare |

Minim 2 teste per parte. partB include un test cu filtru fără niciun rezultat. partC include un test cu 2 tranzacții.

**Hint-uri Readme (concepte, nu cod):**
- `ObjectOutputStream` wrap peste `FileOutputStream`; `ObjectInputStream` wrap peste `FileInputStream`
- `try-with-resources` cu resurse multiple — se închid automat în ordine inversă declarării
- `serialVersionUID` declarat explicit — stabilitate la evoluția clasei; fără el, JVM calculează automat (instabil)
- `transient` — câmpul nu participă la serializare; la deserializare primește valoarea implicită (`null`/`0`/`false`)

**`Checker.java`:** `IOTest.runParts("src/com/pao/laboratory09/exercise1/tests", Main::main)`

---

### Exercițiul 2 — Registru binar cu acces aleatoriu

> **Pachet:** `com.pao.laboratory09.exercise2`
> **Timp:** ~40 min · **Teste automate:** `IOTest.runFlat` (3–5 teste plate)
> **Noțiuni noi:** `DataOutputStream`, `RandomAccessFile`, `ByteBuffer.order(LITTLE_ENDIAN)`, endianness, acces aleatoriu în fișier binar

**Scenariu:** Banca stochează tranzacțiile într-un fișier binar cu înregistrări de dimensiune fixă. Orice tranzacție poate fi actualizată direct (fără a rescrie întregul fișier) prin marcarea statusului ei.

**Format înregistrare binară — 32 bytes per tranzacție:**

| Offset | Lungime | Câmp | Encoding |
|--------|---------|------|----------|
| 0 | 4 bytes | `id` | `int`, little-endian (ByteBuffer) |
| 4 | 8 bytes | `suma` | `double`, little-endian (ByteBuffer) |
| 12 | 10 bytes | `data` | ASCII paddat cu spații la dreapta |
| 22 | 1 byte | `tip` | `0`=CREDIT, `1`=DEBIT |
| 23 | 1 byte | `status` | `0`=PENDING, `1`=PROCESSED, `2`=REJECTED |
| 24 | 8 bytes | padding | zerouri |

**Scriere inițială:** `DataOutputStream` wrapping `FileOutputStream` — scrie toate N înregistrări secvențial.  
**Citire/actualizare:** `RandomAccessFile("rw")` + `seek(idx * 32)` + `ByteBuffer.wrap(...).order(ByteOrder.LITTLE_ENDIAN)`.

**Fișier intermediar:** `output/lab09_ex2.bin`

**Import din exercițiul 1:** `import com.pao.laboratory09.exercise1.TipTranzactie;`

**Protocol stdin:**
```
<N>
<id> <suma> <data(yyyy-MM-dd)> <tip(CREDIT|DEBIT)>
... (N linii)
<comandă>*
QUIT
```

**Comenzi disponibile:**

| Comandă | Output |
|---------|--------|
| `READ <idx>` | Înregistrarea la index `idx` (0-based) |
| `UPDATE <idx> <STATUS>` | `Updated [<idx>]: <STATUS>` |
| `PRINT_ALL` | Toate înregistrările în starea curentă |

**Format linie output:**
```
[<idx>] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<:.2f> RON status=<PENDING|PROCESSED|REJECTED>
```

**Strategie teste (flat):** 3–5 teste; cel puțin un test verifică că UPDATE se reflectă în READ ulterior (actualizare in-place fără rescrierea fișierului).

**Hint-uri Readme (concepte, nu cod):**
- Java este big-endian; BMP/formatul Windows este little-endian — `ByteBuffer.order(ByteOrder.LITTLE_ENDIAN)` face conversia
- `RandomAccessFile.seek(position)` mută cursorul la octetul `position = idx * RECORD_SIZE`
- `DataOutputStream` pentru scriere inițială secvențială; `RandomAccessFile` pentru citire/actualizare selectivă
- Citirea se face cu `raf.read(byte[32])` + `ByteBuffer.wrap(bytes).order(LITTLE_ENDIAN).getInt()` / `.getDouble()`

**`Checker.java`:** `IOTest.runFlat("src/com/pao/laboratory09/exercise2/tests", Main::main)`

---

### Exercițiu bonus — Procesator asincron de tranzacții (Threads)

> **Pachet:** `com.pao.laboratory09.exercise3`
> **Timp:** ~30 min · **Fără teste automate** — demonstrație în `Main.java`
> **Noțiuni noi:** `Thread` / `Runnable`, `synchronized`, `volatile`, `wait()` / `notify()`, `join()`, Producător-Consumator

**Scenariu:** Trei fire tip „ATM" trimit tranzacții în paralel pe o bandă partajată de capacitate 5. Un fir „Processor" le consumă și generează confirmări de factură. Banda nu poate depăși 5 — producătorii `wait()` la plin; procesorul `wait()` la gol.

**Clase de creat:**
- `CoadaTranzactii` — `LinkedList` internă cu capacitate maximă 5; metode `add(t)` și `remove()` `synchronized`; `wait()` la full/empty; `notifyAll()` după fiecare operație
- `ATMThread extends Thread` — produce 4 tranzacții cu `Thread.sleep(50ms)` între ele; afișează `[ATM-N] trimite: Tranzactie #<id> <suma> RON`
- `ProcessorThread implements Runnable` — consumă cu `Thread.sleep(80ms)`; afișează `[Processor] Factura #<id> - <suma> RON | <data>`
- `volatile boolean activ` în `ProcessorThread` — oprire gracioasă
- `Main.java` — 3 ATM + 1 Processor; `join()` toți producătorii; setează `activ = false`; `join()` procesorul; afișează `Toate tranzactiile procesate. Total: 12`

**Cerințe minime demo (toate 7 trebuie demonstrabile în output):**
1. 12 tranzacții procesate total (3 × 4)
2. Producătorul intră în `wait()` cel puțin o dată (banda se umple) — opțional mesaj `[ATM-N] astept loc...`
3. Procesorul afișează factura fiecărei tranzacții
4. `notifyAll()` folosit (nu `notify()`) — justificat în Readme
5. Fir principal `join()` pe toți producătorii înainte de a seta `activ = false`
6. Câmpul de control este `volatile`
7. Output final: `Toate tranzactiile procesate. Total: 12`

**Libertate de implementare:** date hardcodate sau generate, formatare output la alegere, ordinea liniilor poate varia (fire concurente).

---

## LAB 10 — Tip: Complet (Ex1 + Ex2 + Bonus)

> **Curs:** 10 — Java Collections Framework
> **Timp estimat:** ~1h40 min (fără bonus) · ~2h10 min (cu bonus)
> **Legătură cu Lab09:** „Tranzacțiile salvate în Lab09 sunt acum analizate, deduplicate și raportate pe luni."

---

### Exercițiul 1 — Coadă de tranzacții cu LinkedList și Iterator

> **Pachet:** `com.pao.laboratory10.exercise1`
> **Timp:** ~40 min · **Teste automate:** `IOTest.runParts` (2 părți)
> **Noțiuni noi:** `LinkedList` (ops deque), `Iterator` explicit, `Iterator.remove()`, contrast `ArrayList` vs `LinkedList`

**Scenariu:** Sistemul bancar menține o coadă de tranzacții în așteptare. Operatorii adaugă tranzacții (FIFO sau stivă LIFO), procesează prima din coadă și elimină în bloc tranzacțiile de un anumit tip sau sub un prag. Eliminările trebuie să folosească `Iterator.remove()` — singura metodă sigură în iterație.

**Clase de creat:**
- `enum TipTranzactie { CREDIT, DEBIT }`
- `class Tranzactie { int id; double suma; String data; TipTranzactie tip; }` (POJO sau record)
- `Main.java` cu protocol comenzi

*(Redefinite local — independență față de Lab09)*

**Protocol stdin:**
```
<comandă>*
QUIT
```

**Comenzi disponibile:**

| Comandă | Operație `LinkedList` | Output |
|---------|----------------------|--------|
| `ENQUEUE <id> <suma> <data> <tip>` | `addLast` | *(niciun output)* |
| `DEQUEUE` | `removeFirst` | `Procesat: [<id>] <data> <tip>: <suma:.2f> RON` sau `Coada goala.` |
| `PUSH <id> <suma> <data> <tip>` | `addFirst` | *(niciun output)* |
| `POP` | `removeFirst` | `Extras: [<id>] <data> <tip>: <suma:.2f> RON` sau `Coada goala.` |
| `REMOVE_DEBIT` | `Iterator.remove()` pe DEBIT | `Eliminat <N> tranzactii DEBIT.` |
| `REMOVE_BELOW <threshold>` | `Iterator.remove()` pe suma < threshold | `Eliminat <N> tranzactii sub <threshold:.2f> RON.` |
| `PRINT` | iterare | Toate tranzacțiile, câte una pe linie |
| `SIZE` | `size()` | `Dimensiune coada: <N>` |

**Structura testelor:**

| Parte | Comenzi folosite | Ce verifică |
|-------|-----------------|-------------|
| partA | `ENQUEUE`, `DEQUEUE`, `PUSH`, `POP`, `PRINT`, `SIZE`, `QUIT` | Operații de bază LinkedList; coadă goală |
| partB | `ENQUEUE` (pregătire), `REMOVE_DEBIT`, `REMOVE_BELOW`, `PRINT`, `QUIT` | `Iterator.remove()` corect; coadă goală după eliminare totală |

Minim 3 teste per parte. partB include cel puțin un test în care `REMOVE_DEBIT` elimină 0 elemente (niciun DEBIT prezent).

**Hint-uri Readme (concepte, nu cod):**
- `LinkedList<E>` implementează `Deque<E>` — are `addFirst`/`addLast`/`removeFirst`/`removeLast` în O(1)
- `enhanced-for` nu permite `remove()` pe colecție în iterație → `ConcurrentModificationException`
- `Iterator<Tranzactie> itr = coada.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — singura variantă sigură
- Contrast `ArrayList.addFirst` este O(n); `LinkedList.addFirst` este O(1) — explicat în teorie

**`Checker.java`:** `IOTest.runParts("src/com/pao/laboratory10/exercise1/tests", Main::main)`

---

### Exercițiul 2 — Deduplicare și rapoarte lunare

> **Pachet:** `com.pao.laboratory10.exercise2`
> **Timp:** ~40 min · **Teste automate:** `IOTest.runFlat` (4–5 teste plate)
> **Noțiuni noi:** `LinkedHashSet` (vs `HashSet`), `ConcurrentModificationException` (prindere + fix), `Collections.sort/reverse/min/max`

**Scenariu:** Un extras de cont primit de la bancă poate conține tranzacții duplicate (același `id` apare de două ori în export). Sistemul trebuie să dedupliceze, să genereze rapoarte lunare și să sorteze tranzacțiile după diverse criterii.

**Import din exercițiul 1:** `import com.pao.laboratory10.exercise1.Tranzactie; import com.pao.laboratory10.exercise1.TipTranzactie;`

**Protocol stdin:**
```
<N>
<id> <suma> <data(yyyy-MM-dd)> <tip(CREDIT|DEBIT)>
... (N linii, pot conține ID-uri duplicate)
<comandă>*
QUIT
```

**Comenzi disponibile:**

| Comandă | Noțiune | Output |
|---------|---------|--------|
| `UNIQUE_IDS` | `LinkedHashSet<Integer>` | `IDs unice (N): [1, 2, 3, ...]` în ordinea primei apariții |
| `MONTHLY_REPORT` | `TreeMap<String, ...>` | Per lună sortată: `<yyyy-MM>: CREDIT <sum:.2f> RON, DEBIT <sum:.2f> RON` |
| `TOP <n>` | `Collections.sort` + `subList` | `Top <n>:` + n linii tranzacție (suma descrescătoare) |
| `SORT_ASC` | `Collections.sort(Comparator)` | Toate tranzacțiile sortate suma crescător |
| `SORT_DESC` | `Collections.sort(Comparator.reversed())` | Toate tranzacțiile sortate suma descrescător |
| `REVERSE` | `Collections.reverse` | Ordinea inversată față de starea curentă |
| `MIN_MAX` | `Collections.min/max` | `MIN: <linie>` și `MAX: <linie>` |
| `CME_DEMO` | try-catch `ConcurrentModificationException` | `ConcurrentModificationException prins: modificare in iteratie detectata.` |

**Notă `UNIQUE_IDS`:** Dacă două tranzacții au același `id`, se păstrează prima apariție. `LinkedHashSet` garantează ordinea inserării.

**Notă `CME_DEMO`:** Programul încearcă deliberat `for(Tranzactie t : lista) lista.remove(t);` într-un `try-catch`. Prinde excepția și o afișează. Este cerință explicită.

**Strategie teste (flat):** 4–5 teste; cel puțin unul cu duplicate, unul cu `CME_DEMO`, unul cu `MONTHLY_REPORT` pe 3 luni diferite.

**Hint-uri Readme (concepte, nu cod):**
- `LinkedHashSet` vs `HashSet`: ambele O(1) add/contains; `LinkedHashSet` garantează ordinea inserării — folosit pentru deduplicare cu ordine garantată
- `Collections.sort(list, Comparator.comparingDouble(...))` — reutilizezi Comparator din Lab02/05/06
- `ConcurrentModificationException` apare și single-thread la modificare în `enhanced-for`; fix: `Iterator.remove()` sau colectezi elementele de șters, elimini după

**`Checker.java`:** `IOTest.runFlat("src/com/pao/laboratory10/exercise2/tests", Main::main)`

---

### Exercițiu bonus — Rapoarte avansate cu Stream API

> **Pachet:** `com.pao.laboratory10.exercise3`
> **Timp:** ~30 min · **Fără teste automate** — demonstrație în `Main.java`
> **Noțiuni noi:** Stream API: `filter`, `map`, `mapToDouble`, `sorted`, `limit`, `distinct`, `collect`, `Collectors.groupingBy`, `Collectors.summingDouble`

**Scenariu:** Generarea extraselor de cont lunare cu Stream API. Pasul final al narativului: datele sunt în memorie (Lab10 Ex2), acum le analizăm elegant cu streams. **Acesta este „generate invoices grouped by month"** cerut de temă.

**Reutilizează:** clasele din `exercise1` al Lab10 (import sau redefinit local).

**Date demo:** minim 10 tranzacții hardcodate, pe cel puțin 3 luni diferite, cu ambele tipuri CREDIT și DEBIT.

**Cerințe minime în `Main.java` — 7 operații, fiecare precedată de un titlu afișat:**

| # | Operație Stream | Output |
|---|----------------|--------|
| 1 | `filter(tip == CREDIT).collect(toList())` | Lista tuturor CREDIT |
| 2 | `mapToDouble(suma).sum()` | `Total procesat: <sum:.2f> RON` |
| 3 | `Collectors.groupingBy(luna, summingDouble(suma))` | Per lună: `<yyyy-MM>: <sum:.2f> RON` |
| 4 | `sorted(comparingDouble.reversed()).limit(3)` | `Top 3 tranzactii:` + 3 linii |
| 5 | `map(contSursa).distinct().collect(toList())` | `Conturi sursa unice: [...]` |
| 6 | `mapToDouble(suma).average()` | `Suma medie: <avg:.2f> RON` |
| 7 | `Collectors.groupingBy(luna)` + formatare | `EXTRAS DE CONT - <yyyy-MM>: <N> tranzactii, total: <sum:.2f> RON` per lună |

**Libertate de implementare:** ordinea outputului, formatarea exactă și datele demo sunt alese de student. Important: toate cele 7 operații sunt demonstrate și outputul este clar etichetat.

**Hint-uri Readme (concepte, nu cod):**
- Grupare pe lună: `Collectors.groupingBy(t -> t.getData().substring(0, 7))`
- `Collectors.summingDouble(Tranzactie::getSuma)` — suma numerică per grup
- `OptionalDouble avg = stream().mapToDouble(...).average()` — poate fi empty dacă lista e goală
- Streams sunt lazy și single-use — pornește `stream()` din nou pentru fiecare operație distinctă

---

## Conexiuni inter-exerciții

```
Lab09 Ex1 ─── definește TipTranzactie, Tranzactie (Serializable) ──→ Lab09 Ex2 importă TipTranzactie
     │                                                                          │
     │  (aceleași clase, alt mecanism de persistență)                           │
     ▼                                                                          ▼
Lab09 Bonus ── procesare asincronă (Threads) ──────────────── pod spre ConcurrentModificationException (Lab10 Ex2)

Lab10 Ex1 ─── redefine Tranzactie local ────────────────────────────→ Lab10 Ex2 importă din Ex1
     │                                                                          │
     │  (Iterator.remove = fix pentru CME)                                     │
     ▼                                                                          ▼
Lab10 Bonus ─── Stream API pe colecțiile din Ex2 ──────────── EXTRAS DE CONT grupat pe luni ✓
```

---

## Deviații față de planul inițial

| Planul inițial | Decizie finală | Justificare |
|----------------|----------------|-------------|
| Lab09 Ex2: BMP sepia/complement | **Registru binar fix-record** | Aceleași noțiuni tehnice; auto-testabil (output text); integrat tematic |
| Lab09 Ex1: domeniu Student (ca Lab08) | **Domeniu Tranzacție bancară** | Continuitate tematică; evită repetarea domeniului Student din Lab08 |
| Lab10 Ex3 Stream API (menționat Lab07 bonus) | **Implementat complet în Lab10 bonus** | Finalizare naturală a arcului narativ: date salvate → analizate → raportate |
