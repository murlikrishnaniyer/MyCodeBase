package com.example.demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.*;
import java.util.UUID;

public class LargeJsonGeneratorCreate {

    private static final long FILE_SIZE_LIMIT = 1 ; // 1 kb in bytes
    private static final int TOTAL_FILES = 10000; // 1000 files * 1 MB = 1 GB total
    private static final String OUTPUT_DIR = "D:/json_input/";

    public static void main(String[] args) {
        File dir = new File(OUTPUT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        JsonFactory jsonFactory = new JsonFactory();
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= TOTAL_FILES; i++) {
            String fileName = OUTPUT_DIR + "data_chunk_" + i + ".json";
            System.out.println("Generating: " + fileName);
            try {
                generateSingleJsonFile(jsonFactory, fileName);
            } catch (IOException e) {
                System.err.println("Error generating file " + fileName + ": " + e.getMessage());
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total generation time: " + (endTime - startTime) / 1000.0 + " seconds");
    }

    private static void generateSingleJsonFile(JsonFactory factory, String filePath) throws IOException {
        File file = new File(filePath);

        // Wrap the file output stream to track exactly how many bytes have been written
        try (CountingOutputStream countingStream = new CountingOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {

            try (JsonGenerator jGenerator = factory.createGenerator(countingStream)) {
                // Optional: Improves readability but increases file size slightly faster
                jGenerator.useDefaultPrettyPrinter();

                // Start Root Array
                jGenerator.writeStartArray();

                // Keep writing records until the stream reaches the 100 MB threshold
                long recordCounter = 0;
                while (countingStream.getByteCount() < FILE_SIZE_LIMIT) {
                    writeRecord(jGenerator, recordCounter++);
                }

                // Close Root Array
                jGenerator.writeEndArray();
                jGenerator.flush();
            }
        }
        System.out.println("Finished. Final size: " + (file.length() / (1024 * 1024)) + " MB");
    }

    private static void writeRecord(JsonGenerator jGenerator, long index) throws IOException {
        jGenerator.writeStartObject();

        jGenerator.writeNumberField("id", index);
        jGenerator.writeStringField("uuid", UUID.randomUUID().toString());
        jGenerator.writeStringField("name", "User_Name_" + index);
        jGenerator.writeNumberField("timestamp", System.currentTimeMillis());
        jGenerator.writeBooleanField("isActive", index % 2 == 0);

        // Nested metadata object to simulate production-like complexity
        jGenerator.writeObjectFieldStart("metadata");
        jGenerator.writeStringField("region", "us-east-1");
        jGenerator.writeStringField("version", "1.0.4");
        jGenerator.writeNumberField("score", Math.random() * 100);
        jGenerator.writeEndObject();

        // Nested array
        jGenerator.writeArrayFieldStart("tags");
        jGenerator.writeString("system");
        jGenerator.writeString("production");
        jGenerator.writeString("java-generated");
        jGenerator.writeEndArray();

        jGenerator.writeEndObject();
    }

    /**
     * Custom filter stream to track written bytes dynamically.
     */
    private static class CountingOutputStream extends OutputStream {
        private final OutputStream out;
        private long byteCount = 0;

        public CountingOutputStream(OutputStream out) {
            this.out = out;
        }

        public long getByteCount() {
            return byteCount;
        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);
            byteCount++;
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
            byteCount += len;
        }

        @Override
        public void flush() throws IOException {
            out.flush();
        }

        @Override
        public void close() throws IOException {
            out.close();
        }
    }
}
