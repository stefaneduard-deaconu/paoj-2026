package com.pao.laboratory03.enums;

/**
 * Exercițiul 2 — Enum-uri
 *
 * Creează în acest pachet (lângă acest Main.java) un enum și apoi folosește-l aici.
 *
 * PASUL 1 — Creează enum-ul Priority.java (fișier separat în același pachet):
 *   - Constante: LOW, MEDIUM, HIGH, CRITICAL
 *   - Câmpuri private: int level, String color
 *   - Constructor privat: Priority(int level, String color)
 *   - Getteri: getLevel(), getColor()
 *   - Metodă abstractă: String getEmoji() — fiecare constantă o implementează diferit
 *     LOW → "🟢", MEDIUM → "🟡", HIGH → "🟠", CRITICAL → "🔴"
 *   - Valorile sugerate:
 *     LOW(1, "green"), MEDIUM(2, "yellow"), HIGH(3, "orange"), CRITICAL(4, "red")
 *
 * PASUL 2 — În acest Main.java:
 *   a) Parcurge toate valorile cu Priority.values() și afișează:
 *      "emoji name (level=X, color=Y)"
 *   b) Folosește switch pe un Priority și afișează un mesaj specific.
 *   c) Convertește un String în Priority cu Priority.valueOf("HIGH") — afișează rezultatul.
 *   d) Demonstrează compararea: folosește == între două enum-uri (NU .equals()).
 *   e) Afișează name() și ordinal() pentru fiecare constantă.
 *
 * Output așteptat:
 *
 * === Toate prioritățile ===
 * 🟢 LOW (level=1, color=green)
 * 🟡 MEDIUM (level=2, color=yellow)
 * 🟠 HIGH (level=3, color=orange)
 * 🔴 CRITICAL (level=4, color=red)
 *
 * === Switch pe prioritate ===
 * ⚠️ Atenție! Prioritate ridicată!
 *
 * === valueOf ===
 * Priority.valueOf("HIGH") = HIGH
 *
 * === Comparare enum ===
 * HIGH == HIGH? true
 * HIGH == LOW? false
 *
 * === name() și ordinal() ===
 * LOW: name=LOW, ordinal=0
 * MEDIUM: name=MEDIUM, ordinal=1
 * HIGH: name=HIGH, ordinal=2
 * CRITICAL: name=CRITICAL, ordinal=3
 */
public class Main {
    public static void main(String[] args) {
        // TODO: implementează pașii de mai sus
        // Hint: creează mai întâi fișierul Priority.java în acest pachet

        System.out.println("=== Toate prioritatile ===");
        for (Priority p : Priority.values()) {
            System.out.println(p.getEmoji() + " " + p.name() + " (level=" + p.getLevel() + ", color=" + p.getColor() + ")");
        }

        System.out.println("\n=== Switch pe prioritate ===");
        Priority p = Priority.HIGH;
        switch (p) {
            case LOW:
            case MEDIUM:
                System.out.println("Totul este sub control.");
                break;
            case HIGH:
            case CRITICAL:
                System.out.println("⚠️ Atentie! Prioritate ridicata!");
                break;
        }

        System.out.println("\n=== valueOf ===");
        Priority high = Priority.valueOf("HIGH");
        System.out.println("Priority.valueOf(\"HIGH\") = " + high);

        System.out.println("\n=== Comparare enum ===");
        System.out.println("HIGH == HIGH? " + (high == Priority.HIGH));
        System.out.println("HIGH == LOW? " + (high == Priority.LOW));

        System.out.println("\n=== name() si ordinal() ===");
        for (Priority val : Priority.values()) {
            System.out.println(val.name() + ": name=" + val.name() + ", ordinal=" + val.ordinal());
        }
    }
}

