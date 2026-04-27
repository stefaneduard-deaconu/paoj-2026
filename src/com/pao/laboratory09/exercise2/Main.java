package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip)
        // 2. Scrie toate înregistrările în OUTPUT_FILE cu DataOutputStream (format binar, RECORD_SIZE=32 bytes/înreg.)
        //    - bytes 0-3:   id (int, little-endian via ByteBuffer)
        //    - bytes 4-11:  suma (double, little-endian via ByteBuffer)
        //    - bytes 12-21: data (String, 10 chars ASCII, paddat cu spații la dreapta)
        //    - byte 22:     tip (0=CREDIT, 1=DEBIT)
        //    - byte 23:     status (0=PENDING, 1=PROCESSED, 2=REJECTED)
        //    - bytes 24-31: padding (zerouri)
        // 3. Procesează comenzile din stdin până la EOF cu RandomAccessFile:
        //    - READ idx       → seek(idx * RECORD_SIZE), citește și afișează înregistrarea
        //    - UPDATE idx ST  → seek(idx * RECORD_SIZE + 23), scrie noul status (0/1/2)
        //                       afișează "Updated [idx]: STATUS"
        //    - PRINT_ALL      → citește și afișează toate înregistrările
        //
        // Format linie output:
        //   [idx] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<suma:.2f> RON status=<STATUS>

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        File outputDir = new File("output");
        if (!outputDir.exists()) outputDir.mkdirs();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                String tipStr = scanner.next();
                TipTranzactie tip = TipTranzactie.valueOf(tipStr);

                byte[] record = new byte[RECORD_SIZE];
                ByteBuffer buffer = ByteBuffer.wrap(record).order(ByteOrder.LITTLE_ENDIAN);

                buffer.putInt(0, id);
                buffer.putDouble(4, suma);

                String paddedData = String.format("%-10s", data).substring(0, 10);
                byte[] dataBytes = paddedData.getBytes("ASCII");
                System.arraycopy(dataBytes, 0, record, 12, 10);

                record[22] = (byte) (tip == TipTranzactie.CREDIT ? 0 : 1);
                record[23] = 0;

                dos.write(record);
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (scanner.hasNext()) {
                String comanda = scanner.next();

                if (comanda.equals("READ")) {
                    int idx = scanner.nextInt();
                    displayRecord(raf, idx);
                }

                else if (comanda.equals("UPDATE")) {
                    int idx = scanner.nextInt();
                    String statusStr = scanner.next();
                    int statusValue = getStatusValue(statusStr);

                    raf.seek(idx * (long) RECORD_SIZE + 23);
                    raf.write(statusValue);
                    System.out.println("Updated [" + idx + "]: " + statusStr);
                }

                else if (comanda.equals("PRINT_ALL")) {
                    long total = raf.length() / RECORD_SIZE;

                    for (int i = 0; i < total; i++) {
                        displayRecord(raf, i);
                    }
                }
            }
        }
    }

    private static void displayRecord(RandomAccessFile raf, int idx) throws IOException {
        byte[] record = new byte[RECORD_SIZE];
        raf.seek(idx * (long) RECORD_SIZE);
        raf.readFully(record);

        ByteBuffer buffer = ByteBuffer.wrap(record).order(ByteOrder.LITTLE_ENDIAN);
        int id = buffer.getInt(0);
        double suma = buffer.getDouble(4);
        String data = new String(record, 12, 10, "ASCII").trim();
        String tip = (record[22] == 0) ? "CREDIT" : "DEBIT";
        String status = getStatusName(record[23]);

        System.out.printf(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s%n", idx, id, data, tip, suma, status);
    }

    private static int getStatusValue(String status) {
        switch (status) {
            case "PROCESSED":
                return 1;

            case "REJECTED":
                return 2;

            default:
                return 0;
        }
    }

    private static String getStatusName(int status) {
        if (status == 1) {
            return "PROCESSED";
        }

        if (status == 2) {
            return "REJECTED";
        }

        return "PENDING";
    }
}