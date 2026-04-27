# Exercițiul 2 — Registru binar cu acces aleatoriu

> **Pachet:** `com.pao.laboratory09.exercise2`
> **Timp estimat:** ~40 min · **Teste automate:** da (`Checker.java`, flat)

---

## Scop

Banca stochează tranzacțiile într-un fișier binar cu **înregistrări de dimensiune fixă** (32 bytes/tranzacție). Orice tranzacție poate fi actualizată direct — doar statusul ei, la poziția exactă din fișier — fără a rescrie tot fișierul. Vei folosi `DataOutputStream` pentru scrierea inițială și `RandomAccessFile` pentru citire și actualizare selectivă.

---

## Import din exercițiul 1

```java
import com.pao.laboratory09.exercise1.TipTranzactie;
```

---

## Format înregistrare binară — 32 bytes per tranzacție

| Offset | Lungime | Câmp | Encoding |
|--------|---------|------|----------|
| 0 | 4 bytes | `id` | `int`, **little-endian** |
| 4 | 8 bytes | `suma` | `double`, **little-endian** |
| 12 | 10 bytes | `data` | ASCII, paddat cu spații la dreapta |
| 22 | 1 byte | `tip` | `0`=CREDIT, `1`=DEBIT |
| 23 | 1 byte | `status` | `0`=PENDING, `1`=PROCESSED, `2`=REJECTED |
| 24 | 8 bytes | padding | zerouri |

**Fișier intermediar:** `output/lab09_ex2.bin`

---

## Format input

```
N
id suma data(yyyy-MM-dd) tip(CREDIT|DEBIT)
... (N linii)
comandă*
```

Comenzile se citesc până la EOF.

## Format output

**Format linie tranzacție:**
```
[idx] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<suma:.2f> RON status=<PENDING|PROCESSED|REJECTED>
```

**Comenzi disponibile:**

| Comandă | Output |
|---------|--------|
| `READ idx` | Linia tranzacției la index `idx` (0-based) |
| `UPDATE idx STATUS` | `Updated [idx]: STATUS` și modifică octetul de status în fișier |
| `PRINT_ALL` | Toate înregistrările în starea curentă (idx 0 → N-1) |

---

## Exemplu complet

```
Input:
2
1 1500.00 2024-01-15 CREDIT
2 750.50 2024-01-22 DEBIT
UPDATE 0 PROCESSED
READ 0
PRINT_ALL

Output:
Updated [0]: PROCESSED
[0] id=1 data=2024-01-15 tip=CREDIT suma=1500.00 RON status=PROCESSED
[0] id=1 data=2024-01-15 tip=CREDIT suma=1500.00 RON status=PROCESSED
[1] id=2 data=2024-01-22 tip=DEBIT suma=750.50 RON status=PENDING
```

---

## Hint-uri

- **Scriere inițială:** `DataOutputStream` wrap peste `FileOutputStream` — `writeInt`, `writeDouble` scriu **big-endian**; pentru **little-endian** pregătește un `byte[4]` / `byte[8]` cu `ByteBuffer.allocate(n).order(ByteOrder.LITTLE_ENDIAN).putInt(val).array()`
- **Data** (10 chars): scrie cu `write(data.getBytes())` + spații de padding până la 10 bytes
- **Citire / actualizare:** `RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")` → `raf.seek(idx * 32)` → `raf.read(byte[32])` → `ByteBuffer.wrap(bytes).order(LITTLE_ENDIAN)`
- **UPDATE status:** `raf.seek(idx * 32 + 23)` → `raf.write(statusByte)` — actualizezi doar 1 octet
- **Endianness:** Java = big-endian; `ByteBuffer.order(ByteOrder.LITTLE_ENDIAN)` convertește
