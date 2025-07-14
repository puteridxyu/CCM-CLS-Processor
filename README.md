# CCM-CLS-Processor

This is a Java Spring Boot application that processes a structured input file by:

- Reading headers from the first line
- Reading remaining lines as data
- Creating a `ConcurrentMap` for each line
- Logging each record as a JSON object using Jackson

---

<details>
  <summary><strong>📁 CCM-CLS-Processor Directory Structure</strong></summary>
<br>

ccm-cls-processor/<br>
├── src/<br>
│   ├── main/<br>
│   │   ├── java/com/ccm/cls/processor/<br>
│   │   │   ├── ClsProcessorApplication.java          # Main Spring Boot app<br>
│   │   │   └── service/FileProcessorService.java     # Core logic for processing input file<br>
│   │   └── resources/input.txt                       # Sample input file for local run<br>
│   └── test/<br>
│       ├── java/com/ccm/cls/processor/<br>
│       │   ├── ClsProcessorApplicationTests.java     # Spring context test<br>
│       │   └── service/FileProcessorServiceTest.java # Unit test for processor<br>
│       └── resources/test-input.txt                  # Test input file<br>
├── pom.xml                                            # Maven build configuration<br>
└── README.md                                          # Project documentation<br>

</details>

---

## ✅ Features

- Memory-efficient reading of large files using `BufferedReader`
- Field mapping with `ConcurrentHashMap`
- Pipe (`|`) delimiter parsing
- Logging JSON output per line
- Includes unit test for functional verification

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.5.3
- Maven 3.5.3
- SLF4J Logging
- Jackson (for JSON)

---

